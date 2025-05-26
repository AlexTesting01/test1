package com.example.tests;

import org.testng.annotations.*;

import com.microsoft.playwright.options.*;
import com.microsoft.playwright.*;
import com.example.utils.ImageComparator;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ProfileSettingsTest extends BaseTest {

    @Test
    public void testOpenUserMenu() {
        openUserMenu();
    }

    @Test(dependsOnMethods = "testOpenUserMenu")
    public void testOpenSubmenu() {
        // Locate and click submenu by its name
        openSubmenu("Your profile");
    }

    @Test(dependsOnMethods = "testOpenSubmenu")
    public void testUpdateProfile() {
        // Fill in new Company and Location values
        page.getByText("Edit profile").click();
        page.getByPlaceholder("Company").fill("my company");
        page.getByPlaceholder("Location").fill("Tel Aviv");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save")).click();
        page.waitForTimeout(3000);
        // Verify the updated values are displayed
        assert page.getByLabel("Organization: my company").isVisible();
        assert page.getByLabel("Home location: Tel Aviv").isVisible();
        // Clear updates
        page.getByText("Edit profile").click();
        page.getByPlaceholder("Company").clear();
        page.getByPlaceholder("Location").clear();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save")).click();
        page.waitForTimeout(3000);
        // Verify the values are no longer visible
        assert !page.getByLabel("Organization: my company").isVisible();
        assert !page.getByLabel("Home location: Tel Aviv").isVisible();
    }

    @Test(dependsOnMethods = "testUpdateProfile")
    public void testCompareProfileAvatarImage() throws Exception {
        // Locate the element you want to compare visually
        Locator profileSection = page.locator("//*[contains(@class, 'avatar-user')]");

        // Save screenshot
        byte[] screenshot = profileSection.screenshot();
        String screenshotDir = "src/test/java/com/example/test_data/screenshots";
        Files.createDirectories(Paths.get(screenshotDir));

        String currentPath = screenshotDir + "/current-profile.png";
        String baselinePath = screenshotDir + "/baseline-profile.png";

        Files.write(Paths.get(currentPath), screenshot);
        // Compare using ImageComparator with tolerance
        int tolerance = 100;
        boolean result = ImageComparator.compareImages(baselinePath, currentPath, tolerance);

        assert result : "Profile section visual mismatch beyond allowed tolerance.";
    }
}
