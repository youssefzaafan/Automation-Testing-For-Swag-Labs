package com.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Validations {
    private final WebDriver driver;
    //Browser bot
    private final BrowserActions browserActions;
    public Validations(WebDriver driver) {
        this.driver = driver;
        browserActions = new BrowserActions(driver);
    }
    @Step("Validating true condition")
    public void validateTrue(boolean condition, String message){
        Assert.assertTrue(condition, message);
    }
    @Step("Validating false condition")
    public void validateFalse(boolean condition, String message){
        Assert.assertFalse(condition, message);
    }
    @Step("Validating equality")
    public void validateEqual(String expected, String actual, String message){
        Assert.assertEquals(actual, expected, message);
    }
    @Step("Validating non-equality")
    public void validateNotEqual(String expected, String actual, String message){
        Assert.assertNotEquals(actual, expected, message);
    }
    @Step("Validating page URL: {expectedUrl}")
    public void validatePageUrl( String expectedUrl){
        Assert.assertEquals(browserActions.getCurrentUrl(), expectedUrl);
    }
    @Step("Validating page title: {expectedTitle}")
    public void validatePageTitle( String expectedTitle){
        Assert.assertEquals(browserActions.getPageTitle(), expectedTitle);
    }
}
