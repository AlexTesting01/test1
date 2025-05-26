# GitHub Sanity Tests

This project is a Java-based UI tests built with [Microsoft Playwright](https://playwright.dev/java/) and [TestNG](https://testng.org/).
It automates key UI scenarios for GitHub, such as logging in, searching repositories, editing profile settings, and verifying repository content.

## 🧪 Features

- ✅ GitHub login verification
- 🔍 Repository search and validation
- 📝 Profile update with UI checks and avatar comparison via image snapshots
- 📁 Repo content vs local folder comparison

## 📁 Project Structure
<pre><code>
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
</code></pre>

## Technologies
- Java + Maven
- Playwright for UI automation
- TestNG for test orchestration
- Parallel test execution support
- Allure report

## 🚀 Getting Started

### ✅ Prerequisites

- Java 11+
- Maven
- GitHub test account
- Google Chrome or Chromium (used by Playwright)

### 🔧 Setup

1. Clone this repository:

   <pre><code>
   git clone https://github.com/AlexTesting01/test1.git
   cd test1
   </code></pre>
2. Install dependencies:
    <pre><code>
    mvn clean install   
    </code></pre>
3. Set your GitHub credentials in Config.java or use as command line params: 

   <pre><code>
    public class Config {
        static String username = System.getProperty("github.username");

        public static final String GITHUB_EMAIL = System.getProperty("github.email");
        public static final String GITHUB_PASSWORD = System.getProperty("github.password");
        public static final String USER_NAME = (username != null && !username.isEmpty()) ? username : "AlexTesting1";
        public static final String REPO_NAME = "test1";
        public static final String BRANCH = "master";
    }
    </code></pre>
    or

    -Dgithub.email="your-email"
    -Dgithub.password="your-password"

4. Run all tests:
    <pre><code>
    mvn test
    </code></pre>
    or with params
    <pre><code>
    mvn test -Dgithub.email="your-email"  -Dgithub.password="your-password"
    </code></pre>

5. Generate Allure report
     <pre><code>
    mvn allure:report
    </code></pre>
    and
    <pre><code>
    mvn allure:serve
    </code></pre>