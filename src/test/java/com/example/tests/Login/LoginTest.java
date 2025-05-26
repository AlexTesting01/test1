package com.example.tests;

import org.testng.annotations.Test;
import com.example.config.Config;
import com.microsoft.playwright.options.*;
import com.microsoft.playwright.*;

public class LoginTest extends BaseTest {

    @Test
    public void testOpenUserMenu() {
        // Opens the user navigation menu (assumes login is already successful)
        openUserMenu();
    }

    @Test(dependsOnMethods = "testOpenUserMenu")
    public void testLoginSuccess() {

        // Assert that the logged-in user's name is visible in the top bar
        assert page.isVisible(String.format("[title='%s']", Config.USER_NAME))
                : "User profile title not visible - login might have failed";
    }

    @Test(dependsOnMethods = "testLoginSuccess")
    public void testLogout() {

        openSubmenu("Sign out");
        page.getByText("Sign out from all accounts").click();
        // Ensure the user is no longer visible in the UI (logout was successful)
        assert !page.isVisible(String.format("[title='%s']", Config.USER_NAME))
                : "User still visible after logout - logout may have failed";
    }

    @Test(dependsOnMethods = "testLogout")
    public void failedLogin() {
        // Attempt login with invalid credentials
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign in")).click();
        page.getByLabel("Username or email address").fill("123@123.com");
        page.getByLabel("Password").fill("123");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign in").setExact(true)).click();
        // Assert that the login fails with the expected error message
        assert page.getByText("Incorrect username").isVisible()
                : "Expected error message not visible for failed login attempt";
    }

}