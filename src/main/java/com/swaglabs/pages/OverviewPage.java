package com.swaglabs.pages;

import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.utils.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OverviewPage {
    //Variables
    private GUIDriver driver;
    //Constructor
    public OverviewPage(GUIDriver driver) {
        this.driver = driver;
    }
    //Locators
    private final By finishButton =By.id("finish");

    //Actions
    @Step("Clicking on Finish Button")
    public ConfirmationPage clickFinishButton(){
        driver.elementActions().clickElement(finishButton);
        return new ConfirmationPage(driver);
    }
}
