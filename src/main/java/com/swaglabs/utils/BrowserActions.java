package com.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class BrowserActions {
    //Applying Bot Pattern

    private WebDriver driver;
    public BrowserActions(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Navigating to URL: {url}")
    public void navigateToUrl( String url) {

        LogsUtil.info("Navigating to URL: " + url);
        driver.get(url);
    }
    //Get current URL

    @Step("Getting current URL}")
    public String getCurrentUrl() {
        LogsUtil.info("Getting current URL" + driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }

    @Step("Getting page title")
    public String getPageTitle() {
        LogsUtil.info("Getting page title: " + driver.getTitle());
        return driver.getTitle();
    }

    //Close the browser
    @Step("Closing the browser")
    public void closeBrowser() {
        LogsUtil.info("Closing the browser");
        driver.quit();
    }

    //Refresh the page
    @Step("Refreshing the page")
    public void refreshPage() {
        LogsUtil.info("Refreshing the page");
        driver.navigate().refresh();
    }
}
