package com.swaglabs.pages;

import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.utils.BrowserActions;
import com.swaglabs.utils.CustomSoftAssertion;
import com.swaglabs.utils.ElementActions;
import com.swaglabs.utils.Validations;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.swaglabs.utils.PropertiesUtils.getProperty;

public class LoginPage {
    //Variables
    private final GUIDriver driver;

    //Locators
    private By userNameLocator = By.id("user-name");
    private By passwordLocator = By.id("password");
    private By loginButtonLocator = By.id("login-button");
    private By errorMessageLocator =  By.className("error-message-container");

    //Constructor
    public LoginPage(GUIDriver driver) {
        this.driver = driver;
    }

    //Navigate to the login page
    @Step("Navigate to Login Page")
    public void navigateToLoginPage() {
        driver.browserActions().navigateToUrl(getProperty("baseURL"));
    }

    //Actions on Locators
    @Step("Enter username")
    public LoginPage entereUserName(String userName) {
        driver.elementActions().sendData(this.userNameLocator, userName);
        return this;
    }
    @Step("Enter password")
    public LoginPage enterPassword(String password) {
        driver.elementActions().sendData(this.passwordLocator, password);
        return this;
    }

    @Step("Click Login Button")
    public LoginPage clickLoginButton() {
        driver.elementActions().clickElement( this.loginButtonLocator);
        return this;
    }
    @Step("Get error message")
    public String getErrorMessage() {
        return driver.elementActions().getElementText( this.errorMessageLocator);
    }


    //Validations Tests
    @Step("Validate login page")
    public LoginPage validateLoginPage() {
        CustomSoftAssertion.softAssertion.assertEquals(driver.browserActions().getCurrentUrl(), getProperty("homeURL"), "User should be on login page");
         return this;
    }
    @Step("Validate login page title")
    public LoginPage  validateLoginPageTitle() {
        CustomSoftAssertion.softAssertion.assertEquals(driver.browserActions().getPageTitle(), getProperty("appTitle"), "Page title should be 'Swag Labs'");
        return this;
    }

    //Login success Soft

    @Step("Validate login success soft")
    public LoginPage validateLoginSuccessSoft() {
        validateLoginPage().validateLoginPageTitle();
        return this;
    }

    //Login success
    @Step("Validate login success")
    public HomePage validateLoginSuccess() {
        driver.validations().validatePageUrl( getProperty("homeURL"));
       return new HomePage(this.driver);
    }
    //Login failure
    @Step("Validate login failure")
    public HomePage validateLoginFailure() {
        driver.validations().validateEqual(getProperty("errorMSG"), this.getErrorMessage(), "Error message should match expected value");
        return new HomePage(this.driver);
    }
}
