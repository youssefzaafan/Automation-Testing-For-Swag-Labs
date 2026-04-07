package com.swaglabs.pages;

import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.utils.ElementActions;
import com.swaglabs.utils.Validations;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InformationPage {
    //Variables
    private GUIDriver driver;

    //Constructor
    public InformationPage(GUIDriver driver) {
        this.driver = driver;
    }

    //Locators
    private final By firstNameLocator = By.id("first-name");
    private final By lastNameLocator = By.id("last-name");
    private final By postalCodeLocator = By.id("postal-code");
    private final By continueButtonLocator = By.id("continue");

    //Actions
    @Step("Entering information form with First Name: {firstName}, Last Name: {lastName}, Postal Code: {postalCode}")
    public InformationPage enterInformationForm(String firstName, String lastName, String postalCode) {
        driver.elementActions().sendData( firstNameLocator, firstName);
        driver.elementActions().sendData( lastNameLocator, lastName);
        driver.elementActions().sendData( postalCodeLocator, postalCode);
        return this;
    }

    @Step("Clicking continue button on information page")
    public OverviewPage clickContinueButton() {
        driver.elementActions().clickElement(continueButtonLocator);
        return new OverviewPage(driver);
    }

    //Validation
    @Step("Validating information form is filled with First Name: {firstName}, Last Name: {lastName}, Postal Code: {postalCode}")
    public InformationPage validateInformationFormFilled(String firstName, String lastName, String postalCode) {
        String actualFirstName =  driver.elementActions().getTextFromInput( firstNameLocator);
        String actualLastName =  driver.elementActions().getTextFromInput( lastNameLocator);
        String actualPostalCode =  driver.elementActions().getTextFromInput( postalCodeLocator);
        driver.validations().validateEqual(actualFirstName, firstName, "First name should be filled correctly");
        driver.validations().validateEqual(actualLastName, lastName, "Last name should be filled correctly");
        driver.validations().validateEqual(actualPostalCode, postalCode, "Postal code should be filled correctly");
        return this;
    }
}
