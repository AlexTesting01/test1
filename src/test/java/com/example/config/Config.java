package com.example.config;

public class Config {
    static String username = System.getProperty("github.username");

    public static final String GITHUB_EMAIL = System.getProperty("github.email");
    public static final String GITHUB_PASSWORD = System.getProperty("github.password");
    public static final String USER_NAME = (username != null && !username.isEmpty()) ? username : "AlexTesting1";
    public static final String REPO_NAME = "test1";
    public static final String BRANCH = "master";
}