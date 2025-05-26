package com.example.tests;

import org.testng.annotations.Test;
import com.example.config.Config;

public class RepoSearchTest extends BaseTest {

    @Test
    public void testRepoSearch() {
        searchAndOpenRepository(Config.REPO_NAME);
    }
}