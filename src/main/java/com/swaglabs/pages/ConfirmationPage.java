package com.swaglabs.pages;

import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.utils.ElementActions;
import com.swaglabs.utils.Validations;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {
    //Variables
    private GUIDriver driver;
    //Constructor
    public ConfirmationPage(GUIDriver driver) {
        this.driver = driver;}
    //Locators
    private final By confirmationMessageLocator = By.className("complete-header");

    //Actions
    @Step("Getting Confirmation Message")
    public String getConfirmationMessage(){
        return driver.elementActions().getElementText(confirmationMessageLocator);}

    //Validations
    @Step("Validating Confirmation Message")
    public void validateConfirmationMessage(String expectedMessage){
        String actualMessage = getConfirmationMessage();
        driver.validations().validateEqual(actualMessage, expectedMessage, "Confirmation message does not match expected value.");
    }
}
