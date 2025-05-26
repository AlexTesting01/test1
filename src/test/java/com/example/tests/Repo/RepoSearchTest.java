package com.example.tests;

import org.testng.annotations.Test;

public class RepoSearchTest extends BaseTest {

    @Test
    public void testRepoSearch() {
        String repoName = "test1";
        searchAndOpenRepository(repoName);
    }
}