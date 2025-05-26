package com.example.tests;

import com.microsoft.playwright.*;
import org.testng.annotations.*;
import com.example.config.Config;
import com.microsoft.playwright.options.LoadState;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;

    @BeforeClass
    public void setup() {
        // Initialize Playwright and launch a Chromium browser (non-headless for visibility)
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newContext().newPage();

        // Navigate to GitHub login page
        page.navigate("https://github.com/login");

        // Perform login using credentials from the config
        page.fill("#login_field", Config.GITHUB_EMAIL);
        page.fill("#password", Config.GITHUB_PASSWORD);
        page.click("[name='commit']");
    }

    @AfterClass
    public void tearDown() {
        // Close resources after each test to ensure isolation and cleanup
        if (page != null)
            page.close();
        if (browser != null)
            browser.close();
        if (playwright != null)
            playwright.close();
    }

    protected void openUserMenu() {
        String selector = "[aria-label='Open user navigation menu']";
        page.waitForSelector(selector);
        page.locator(selector).click();
    }

    protected void openSubmenu(String submenuName) {
        page.locator(String.format("//*[contains(@class,'ItemLabel') and text()='%s']", submenuName)).click();
    }

    protected void searchAndOpenRepository(String repoName) {
        String menuLocator = "Open global navigation menu";
        String repoTitleLocator = String.format("AlexTesting01/%s Jump to", repoName);

        // Open the global navigation menu
        page.getByLabel(menuLocator).waitFor();
        page.getByLabel(menuLocator).click();
    
        page.waitForTimeout(3000); // temporary wait for UI to settle
    
        // Open the repo filter
        page.locator("#filter-button-filter-repositories svg").click();
    
        // Search for the repo by name
        page.getByPlaceholder("Filter repositories").fill(repoName);
        page.waitForTimeout(1000); // allow time for search results to load
    
        // Verify the search result contains the expected repo
        assert page.getByText(repoTitleLocator).count() == 1;
    
        // Open repo and verify the title matches
        page.getByText(repoTitleLocator).click();
        assert page.locator("#repo-title-component a").textContent().equals(repoName);
    }
}
