package com.swaglabs.tests;

import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.listeners.TestNGListeners;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.utils.BrowserActions;
import com.swaglabs.utils.JsonUtils;
import com.swaglabs.utils.PropertiesUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

@Listeners(TestNGListeners.class)
public class UserFlowTC {
    //Variables
    private GUIDriver driver;
    JsonUtils testData;

    @Test
    public void sanityTS(){
        new LoginPage(driver).entereUserName(testData.getJsonData("login-credentials.username"))
                .enterPassword(testData.getJsonData("login-credentials.password"))
                .clickLoginButton()
                .validateLoginSuccess()
                .clickOnProduct(testData.getJsonData("product-names.item1.name"))
                .validateProductAddedToCart(testData.getJsonData("product-names.item1.name"))
                .clickOnCartIcon()
                .validateProductDetails(testData.getJsonData("product-names.item1.name"), testData.getJsonData("product-names.item1.price"))
                .clickCheckoutButton().
                enterInformationForm(testData.getJsonData("user-information.firstName"),
                        testData.getJsonData("user-information.lastName"),
                        testData.getJsonData("user-information.postalCode"))
                .validateInformationFormFilled(testData.getJsonData("user-information.firstName"),
                        testData.getJsonData("user-information.lastName"),
                        testData.getJsonData("user-information.postalCode"))
                .clickContinueButton()
                .clickFinishButton()
                .validateConfirmationMessage(testData.getJsonData("confirmation-message"));
    }




    //Configurations
    @BeforeClass
    public void beforeClass() {
        // Initialize test data from JSON file
        testData = new JsonUtils("test-data");
    }

    @BeforeMethod
    public void setUp(){
        String browserName = PropertiesUtils.getProperty("browserType");
        driver = new GUIDriver(browserName);
        //anonymous object
        new LoginPage(driver).navigateToLoginPage(); // Assuming LoginPage has a method to navigate to the login page
    }



    //TearDown
    @AfterMethod
    public void tearDown() {
        // Close the WebDriver instance after each test method
        driver.browserActions().closeBrowser();
        //CustomSoftAssertion.customAssertAll(); // Assert all custom soft assertions
    }
}
