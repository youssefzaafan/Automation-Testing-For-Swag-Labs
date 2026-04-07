package com.swaglabs.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {
    //Explict wait using Lambda expressions rather than expected condtion
    // present - visible - clickable

    private WebDriver driver;
    public Waits(WebDriver driver){
        this.driver= driver;
    }


    //wait for the element to be present, made it static to be callable without needing for object creation
    public WebElement waitForElementPresent( By locator){
        LogsUtil.info("Waiting for the element to be present:{}",locator);
        return new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(driver1 -> driver1.findElement(locator));
    }
    //wait for the element to be visible
    public WebElement waitForElementVisible(By locator){
        LogsUtil.info("Waiting for the element to be visible: {} ",locator);
        return new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(driver1 -> {
                    WebElement element = waitForElementPresent(locator);
                    return element.isDisplayed() ? element : null;
                });
    }
    //wait for the element to be clickable
    public WebElement waitForElementClickable( By locator){
        LogsUtil.info("Waiting for the element to be clickable: {}",locator);
        return new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(driver1 -> {
                    WebElement element = waitForElementVisible(locator);
                    return element.isDisplayed() && element.isEnabled() ? element : null;
                });
    }


}
