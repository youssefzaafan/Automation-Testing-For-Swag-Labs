package com.swaglabs.drivers;

import com.swaglabs.utils.BrowserActions;
import com.swaglabs.utils.ElementActions;
import com.swaglabs.utils.LogsUtil;
import com.swaglabs.utils.Validations;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.fail;

public class GUIDriver {
    //Intiliaze
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public GUIDriver(String browserName) {
        WebDriver driver = getWebDriver(browserName).createDriver();
        setWebDriver(driver);
    }

    private void setWebDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    private static AbstractDriver getWebDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                return new ChromeFactory();
            case "edge":
                return new EdgeFactory();
            case "firefox":
                return new FirefoxFactory();
            default:
                LogsUtil.error("Invalid browser name: " + browserName);
                fail("Invalid browser name: " + browserName);
                return null;
        }
    }

    public static WebDriver get() {
        if (driverThreadLocal.get() == null) {
            LogsUtil.error("WebDriver instance is not initialized for the current thread.");
            fail("WebDriver instance is not initialized for the current thread.");
            return null;
        }
        return driverThreadLocal.get();
    }

    public ElementActions elementActions() {
        return new ElementActions(get());
    }
    public BrowserActions browserActions() {
        return new BrowserActions(get());
    }
    public Validations validations() {
        return new Validations(get());
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

}
