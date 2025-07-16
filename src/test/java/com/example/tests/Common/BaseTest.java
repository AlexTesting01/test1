package com.example.tests.Common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

import org.testng.annotations.*;

import com.example.config.Config;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.navigate().to(Config.URL);
        driver.findElement(By.id("login_field")).sendKeys(Config.EMAIL);
        driver.findElement(By.id("password")).sendKeys(Config.PASSWORD);
        driver.findElement(By.name("commit")).click();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /** Clicks the user navigation menu. */
    protected void openUserMenu() {
        String selector = "[aria-label='Open user navigation menu']";
        getWebDriverWait(10).until(ExpectedConditions.elementToBeClickable(By.cssSelector(selector))).click();
    }

    /**
     * Clicks a submenu item by text, with a custom timeout.
     * @param submenuName Text of the submenu item.
     * @param timeoutInSeconds Max wait time for item to be clickable.
     */
    protected void openSubmenu(String submenuName, long timeoutInSeconds) {
        By submenuLocator = By.xpath(String.format("//*[contains(@class,'ItemLabel') and text()='%s']", submenuName));
        getWebDriverWait(timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(submenuLocator)).click();
    }

    /**
     * Returns WebDriverWait instance with specified timeout.
     * @param seconds Timeout duration.
     */
    protected WebDriverWait getWebDriverWait(long seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }
}