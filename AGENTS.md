# AGENTS.md - Swaglabs Test Automation Guide

## Project Architecture

This is a **Selenium WebDriver-based UI test automation framework** for Swaglabs (saucedemo.com). Built with:
- **Language:** Java 17
- **Testing Framework:** TestNG 7.11.0
- **Browser Automation:** Selenium WebDriver 4.40.0
- **Reporting:** Allure 2.24.0
- **Logging:** Log4j2 2.25.3

### Key Components

1. **Driver Management** (`drivers/`)
   - `DriverManager`: ThreadLocal WebDriver manager for thread-safe parallel test execution
   - `BrowserFactory`: Creates WebDriver instances (Chrome, Firefox, Edge) with optimized options
   - Uses PageLoadStrategy.NORMAL and disables notifications/autofill

2. **Page Object Model** (`pages/`)
   - `LoginPage`: Encapsulates login page locators and actions with fluent interface
   - Uses `@Step` annotations for Allure reporting
   - Combines actions and validations on same class

3. **Utility Layers** (`utils/`)
   - **ElementActions**: Private constructor pattern, wraps element interactions (sendData, clickElement, getElementText)
   - **BrowserActions**: Browser-level operations (navigateToUrl, getCurrentUrl, getPageTitle, closeBrowser)
   - **Waits**: Implicit waiting before element operations
   - **Scrolling**: Auto-scroll to element before interaction
   - **Validations**: Hard assertions via TestNG Assert
   - **CustomSoftAssertion**: Static SoftAssert instance for soft assertions without assertAll
   - **LogsUtil**: Caller-aware logging via SLF4j (auto-detects calling class)
   - **ScreenshotsUtils**: Captures screenshots to `test-outputs/screenshots/`, attaches to Allure
   - **AllureUtils**: Attaches logs and screenshots to Allure reports
   - **FilesUtils**: File utilities (getLatestFile, deleteFiles)

## Build & Execution

```bash
# Maven build (Java 17, UTF-8 encoding)
mvn clean compile

# Run tests via TestNG
mvn test

# Generate Allure report (after test execution)
allure serve test-outputs/allure-results
```

**Build Configuration:**
- Custom output directory: `test-outputs/target` (not default `target/`)
- AspectJ weaver enabled for Allure integration (required for `@Step` annotations)
- Surefire plugin 3.1.2 with AspectJ agent for step tracking

## Design Patterns & Conventions

### 1. **Fluent Interface (Method Chaining)**
All page object methods return `this` to enable fluent patterns:
```java
new LoginPage(driver)
    .entereUserName("standard_user")
    .enterPassword("secret_sauce")
    .clickLoginButton()
    .validateLoginSuccess();
```

### 2. **Utility Class Pattern**
Utilities use private constructors + static methods to prevent instantiation:
```java
private ElementActions() {
    throw new UnsupportedOperationException("This class cannot be instantiated");
}
```

### 3. **ThreadLocal WebDriver**
`DriverManager` uses `ThreadLocal<WebDriver>` for thread-safe driver management across parallel tests.

### 4. **Test Structure (LoginTest.java)**
- `@BeforeSuite`: Cleans allure-results directory
- `@BeforeMethod`: Creates driver instance, navigates to login page
- `@Test`: Contains test logic using page objects
- `@AfterMethod`: Closes browser
- `@AfterClass`: Attaches logs to Allure report

### 5. **Allure Integration**
- All significant actions annotated with `@Step("description")`
- Log files automatically attached via `AllureUtils.attachLogsToAllureReport()`
- Screenshots attached with `ScreenshotsUtils.takeScreenshot(name)`
- Results stored in `test-outputs/allure-results/` as JSON + attachments

## Test Data & Configuration

- **Test URL:** `https://www.saucedemo.com/`
- **Test Credentials:** 
  - Username: `standard_user`
  - Password: `secret_sauce`
- **Log Directory:** `test-outputs/logs/` (timestamped per run)
- **Screenshot Directory:** `test-outputs/screenshots/`
- **Expected Inventory URL:** `https://www.saucedemo.com/inventory.html`

## Assertion Strategy

1. **Hard Assertions** (stop test on failure):
   - Use `Validations` class methods (e.g., `validatePageUrl`, `validateEqual`)
   - Used for critical validations where test continuation is invalid

2. **Soft Assertions** (collect failures, continue test):
   - Use `CustomSoftAssertion.softAssertion.assertEquals(...)`
   - Call `CustomSoftAssertion.customAssertAll()` to throw collected assertions (wrapped in try-catch)

## Logging & Debugging

- **Log Files:** Auto-generated in `test-outputs/logs/` with timestamp pattern `log_yyyy-MM-dd_HH-mm-ss.log`
- **Log Levels:** DEBUG (file and console), with colored console output
- **Auto-Caller Detection:** `LogsUtil` auto-detects caller class via stack trace - use generically: `LogsUtil.info("message")`
- **Logs Attached:** All logs attached to Allure report after `@AfterClass`

## Common Tasks for AI Agents

### Adding a New Test
1. Create page class in `pages/` with locators (By), constructor(driver), action methods with `@Step`
2. Create test class in `tests/` with `@BeforeMethod` setup, `@Test` test logic, `@AfterMethod` teardown
3. Use fluent pattern: `new PageName(driver).action1().action2().validate()`

### Adding Element Interactions
- Use `ElementActions.sendData()`, `ElementActions.clickElement()`, `ElementActions.getElementText()`
- These automatically wait (Waits.waitForElementVisible/Clickable) and scroll (Scrolling.scrollToElement)
- Don't manually call wait/scroll - they're built in

### Validating Page State
- For hard fails: `Validations.validatePageUrl()`, `Validations.validateEqual()`
- For soft fails: `CustomSoftAssertion.softAssertion.assertEquals()` + `customAssertAll()`
- All validations auto-annotate with `@Step` for Allure

### Debugging Failures
1. Check `test-outputs/logs/` for timestamped log file
2. Check `test-outputs/screenshots/` for failure screenshots
3. View Allure report: `allure serve test-outputs/allure-results/`
4. Enable headless mode temporarily by uncommenting `//chromeOptions.addArguments("--headless");` in BrowserFactory

## Key Files to Modify When

| Task | Files |
|------|-------|
| Add new test | Create `tests/NewTest.java` + `pages/NewPage.java` |
| Add browser option | `drivers/BrowserFactory.java` |
| Add utility method | `utils/*.java` (appropriate class) |
| Change log output | `src/main/resources/log4j2.properties` |
| Add dependency | `pom.xml` under `<dependencies>` |

## Known Behaviors & Gotchas

1. **Element locators are instance fields** in page classes - they don't adapt per test, so ensure locators are stable
2. **CustomSoftAssertion is static & shared** - works across tests but calling `customAssertAll()` affects all collected assertions
3. **Screenshots taken manually** - add `ScreenshotsUtils.takeScreenshot(name)` explicitly in tests, not auto-captured on failure
4. **Driver cleanup required** - always close driver in `@AfterMethod` to prevent resource leaks
5. **Allure step hierarchy** - nested `@Step` methods appear as child steps in Allure; name your methods descriptively

