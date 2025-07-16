package com.example.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.example.config.Config;
import com.example.tests.Common.BaseTest;

public class LoginTest extends BaseTest {

    /** Tests opening the user menu. */
    @Test
    public void testOpenUserMenu() {
        openUserMenu();
    }

    /** Verifies successful login. */
    @Test(dependsOnMethods = "testOpenUserMenu")
    public void testLoginSuccess() {
        boolean isUserTitleVisible = getWebDriverWait(15).until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(String.format("[title='%s']", Config.USER_NAME))))
                .isDisplayed();

        assert isUserTitleVisible
                : "User profile title not visible - login might have failed";
    }

    /** Tests user logout. */
    @Test(dependsOnMethods = "testLoginSuccess")
    public void testLogout() {
        openSubmenu("Sign out", 2);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Consider removing implicit wait
        driver.findElement(By.xpath("//*[@value='Sign out from all accounts']")).click();

        boolean isUserTitleInvisible = getWebDriverWait(10).until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector(String.format("[title='%s']", Config.USER_NAME))));

        assert isUserTitleInvisible
                : "User still visible after logout - logout may have failed";
    }

    /** Tests login with invalid credentials. */
    @Test(dependsOnMethods = "testLogout")
    public void failedLogin() {
        driver.navigate().to(Config.URL);

        driver.findElement(By.id("login_field")).clear();
        driver.findElement(By.id("password")).clear();

        driver.findElement(By.id("login_field")).sendKeys("123@123.com");
        driver.findElement(By.id("password")).sendKeys("123");

        getWebDriverWait(5).until(ExpectedConditions.elementToBeClickable(By.name("commit"))).click();

        boolean isErrorMessageVisible = getWebDriverWait(7).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(),'Incorrect username or password.')] | //div[contains(text(),'Incorrect username')]"))).isDisplayed();

        assert isErrorMessageVisible
                : "Expected error message not visible for failed login attempt";
    }
}