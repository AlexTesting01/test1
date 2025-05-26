# GitHub Sanity Tests

## Overview
This is a basic sanity test suite covering 4 core GitHub.com flows using Playwright and TestNG.
1. Login Flow
Go to GitHub login page

Enter credentials

Verify successful login (e.g., avatar or dashboard check)

2. Search for a Repository
Use the top nav search bar

Search a well-known repo (e.g., "playwright")

Verify results are shown and contain expected text

3. Update Profile Settings
Navigate to profile settings

Change bio or website

Verify it's updated and persists after reload



## Technologies
- Java + Maven
- Playwright for UI automation
- TestNG for test orchestration
- Parallel test execution support

## Run Instructions

### Install Dependencies
```bash
mvn clean install