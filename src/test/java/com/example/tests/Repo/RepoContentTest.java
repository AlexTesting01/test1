package com.example.tests;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.annotations.Test;
import com.microsoft.playwright.options.*;

import com.example.utils.FileUtils;
import com.example.utils.DOMUtils;
import com.microsoft.playwright.*;

public class RepoContentTest extends BaseTest {

    @Test
    public void testRepoSearch() {
        String repoName = "test1";
        String branchName = "master";
        String path = "src/test/java/com/example/tests/Common";
        String file = "BaseTest.java";

        searchAndOpenRepository(repoName);

        page.getByTestId("anchor-button").click();
        page.getByLabel(branchName).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("src/test/java/com/example, (")).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("tests, (Directory)")).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Common, (Directory)")).click();
        page.waitForTimeout(3000);

        List<String> localContent = FileUtils.getFileNamesFromFolder(path).stream()
                .map(String::trim)
                .distinct()
                .sorted()
                .toList();
        List<String> gitContent = DOMUtils.getAllText(page, ".react-directory-filename-cell a").stream()
                .map(String::trim)
                .distinct()
                .sorted()
                .toList();
        assert localContent.equals(gitContent) : "\nMismatch:\nLocal: " + localContent + "\nGitHub: " + gitContent;

        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(file + ", (File)")).click();

        String localFileContent = FileUtils.readFileContent(path + "/" + file);

        String gitFileContent = page.locator("[aria-label='file content']").textContent();
        assert gitFileContent.trim().equalsIgnoreCase(localFileContent.trim()) : String
                .format("Strings do not match! git file: '%s', local file: '%s'", gitFileContent, localFileContent);

    }
}