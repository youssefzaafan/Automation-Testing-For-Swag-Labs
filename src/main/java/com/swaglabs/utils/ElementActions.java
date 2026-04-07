package com.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementActions {
    //Applying Bot Pattern
    private WebDriver driver;
    //Wait bot
    private Waits waits;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
    }
    //send keys
    @Step("Sending data: {data} to element: {locator}")
    public void sendData(By locator, String data){
        waits.waitForElementVisible(locator);
        scrollToElement(locator);
        findElement(locator).sendKeys(data);
        LogsUtil.info("Sent data: " + data + " to element with locator:{} ",locator);
    }

    //click
    @Step("Clicking on element: {locator}")
    public void clickElement (By locator){
        waits.waitForElementClickable(locator);
        scrollToElement(locator);
        findElement(locator).click();
        LogsUtil.info("Clicked on element with locator: {}", locator);
    }

    //get text
    @Step("Getting text from element: {locator}")
    public String getElementText(By locator){
        waits.waitForElementVisible(locator);
        scrollToElement(locator);
        return findElement(locator).getText();
    }
    //Find an element
    public WebElement findElement(By locator){
        return driver.findElement(locator);
    }

    public String getTextFromInput(By locator){
        waits.waitForElementVisible(locator);
        scrollToElement(locator);
        LogsUtil.info("Getting text from input element with locator:{} ", locator.toString()," Text: {} ", findElement(locator).getDomAttribute("value"));
        return findElement(locator).getDomAttribute("value");


    }
    @Step("Scrolling to the element with locator: {locator}")
    public void scrollToElement( By locator) {
        LogsUtil.info("Scrolling to the element: " + locator.toString());
        ((JavascriptExecutor) driver).
                executeScript("arguments[0].scrollIntoView(true);", findElement(locator));
    }


}
