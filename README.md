# GitHub Sanity Tests

This project is a Java-based UI tests built with [Microsoft Playwright](https://playwright.dev/java/) and [TestNG](https://testng.org/).
It automates key UI scenarios for GitHub, such as logging in, searching repositories, editing profile settings, and verifying repository content.

## 🧪 Features

- ✅ GitHub login verification
- 🔍 Repository search and validation
- 📝 Profile update with UI checks and avatar comparison via image snapshots
- 📁 Repo content vs local folder comparison

## 📁 Project Structure
test1/
├── src/
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   ├── config/               # Credentials & config
│                   ├── listeners/            # Suite logging
│                   ├── utils/                # File, DOM & image utilities
│                   └── tests/                # Main test cases
│                       ├── BaseTest.java
│                       ├── LoginTest.java
│                       ├── RepoSearchTest.java
│                       ├── RepoContentTest.java
│                       └── ProfileSettingsTest.java
└── testng.xml                                # TestNG suite configuration


## Technologies
- Java + Maven
- Playwright for UI automation
- TestNG for test orchestration
- Parallel test execution support

## 🚀 Getting Started

### ✅ Prerequisites

- Java 11+
- Maven
- GitHub test account
- Google Chrome or Chromium (used by Playwright)

### 🔧 Setup

1. Clone this repository:

   ```bash
   git clone https://github.com/AlexTesting01/test1.git
   cd test1

2. Install dependencies:

    mvn clean install   

3. Set your GitHub credentials in Config.java or use as command line params: 

    public class Config {
        public static final String GITHUB_EMAIL = "your-email";
        public static final String GITHUB_PASSWORD = "your-password";
        ....
    }

    or

    -Dgithub.email="your-email"
    -Dgithub.password="your-password"

4. Run all tests:

    mvn test

    or with params

    mvn test -Dgithub.email="your-email"  -Dgithub.password="your-password"