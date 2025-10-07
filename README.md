## Selenium + Java 21 TestNG Framework

A modular Selenium framework using Java 21, Maven, TestNG, POM, Extent Reports, Log4j2, and WebDriverManager. Includes parallel execution, data providers, and BrowserStack support.

### Project Structure

```
src/test/java
  └─ com/automation
     ├─ base (BaseTest, DriverFactory, ConfigReader)
     ├─ pages (Page Objects)
     ├─ tests (TestNG tests)
     ├─ utils (Wait, Actions, Screenshot, DataProviders)
     └─ listeners (TestNG listener for reporting)
src/test/resources
  ├─ config/config.properties
  ├─ log4j2.xml
  └─ testdata/*
```

### Prerequisites
- Java 21
- Maven 3.9+
- Chrome/Firefox/Edge installed

### Setup
- Update `src/test/resources/config/config.properties` (browser, baseUrl, timeouts).
- For BrowserStack, set creds and `bs.enabled=true`.

### Run Tests
- Default (uses `testng.xml`):
```bash
mvn clean test
```
- Override browser:
```bash
mvn clean test -Dbrowser=edge
```
- Specify suite:
```bash
mvn clean test -Dsurefire.suiteXmlFiles=testng.xml
```

### Test Cases Covered
- Validate home page title is non-empty.
- Click Motorcycles and validate title/URL contains "motorcycle".
- Click Locate Us (new window) and validate title/URL contains "locate".

### Parallel Execution
Configured via `testng.xml` with `parallel="tests"` and ThreadLocal WebDriver in `DriverFactory`.

### Reporting
- Extent HTML report at `reports/extent-report.html`.
- Screenshots embedded on failures.

### Data Driven
`com.automation.utils.DataProviders` demonstrates CSV/Excel/JSON providers.

### CI/CD
- Jenkins: see `Jenkinsfile`.
- GitHub Actions: see `.github/workflows/ci.yml`.

### BrowserStack
Set in `config.properties`:
```
bs.enabled=true
bs.user=<user>
bs.key=<key>
bs.browserName=Chrome
bs.browserVersion=latest
bs.os=Windows
bs.osVersion=11
```

### Git Commands
```bash
git init
git add .
git commit -m "Initial Selenium Java 21 TestNG framework with POM, reporting, CI"
git branch -M main
git remote add origin <your-repo-url>
git push -u origin main
```

### Notes
- Base URL: `https://www.royalenfield.com/in/en/home/`.
- Selectors are resilient but may need tweaks if the site changes.



