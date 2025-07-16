package com.example.config;

public class Config {
    static String username = System.getProperty("github.username");
    public static final String USER_NAME = (username != null && !username.isEmpty()) ? username : "AlexTesting1";   
    public static final String EMAIL = System.getProperty("email");
    public static final String PASSWORD = System.getProperty("password");
    public static final String URL = "https://github.com/login";
}