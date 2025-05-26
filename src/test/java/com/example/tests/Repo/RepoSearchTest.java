package com.example.tests;

import org.testng.annotations.Test;

public class RepoSearchTest extends BaseTest {

    @Test
    public void testRepoSearch() {
        String repoName = "test1";

        // Open the global navigation menu
        page.getByLabel("Open global navigation menu").waitFor();
        page.getByLabel("Open global navigation menu").click();

        page.waitForTimeout(3000); // temporary wait for UI to settle

        // Open the repo filter
        page.locator("#filter-button-filter-repositories svg").click();

        // Search for the repo by name
        page.getByPlaceholder("Filter repositories").fill(repoName);
        page.waitForTimeout(1000); // allow time for search results to load

        // Verify the search result contains the expected repo
        assert page.getByText(String.format("AlexTesting01/%s Jump to", repoName)).count() == 1;

        // Open repo and verify the title matches
        page.getByText(String.format("AlexTesting01/%s Jump to", repoName)).click();
        assert page.locator("#repo-title-component a").textContent().equals(repoName);
    }
}