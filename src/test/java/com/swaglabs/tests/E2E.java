package com.swaglabs.tests;

import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.listeners.TestNGListeners;
import com.swaglabs.pages.CartPage;
import com.swaglabs.pages.HomePage;
import com.swaglabs.pages.InformationPage;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.utils.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;


@Listeners(TestNGListeners.class)
public class E2E {
    //Variables
    private GUIDriver driver;
    JsonUtils testData;
    //Tests
    @Test
    public void validLoginTest() {
        // Test case for valid login Application using the Fluent pattern & anonymous object
        new LoginPage(driver).entereUserName(testData.getJsonData("login-credentials.username"))
                .enterPassword(testData.getJsonData("login-credentials.password"))
                .clickLoginButton()
               // .validateLoginSuccessSoft();
                .validateLoginSuccess();
     }
     @Test(dependsOnMethods = "validLoginTest")
     public void addingProductToCart(){
        new HomePage(driver).clickOnProduct(testData.getJsonData("product-names.item2.name"))
                .validateProductAddedToCart(testData.getJsonData("product-names.item2.name"));
     }

     @Test(dependsOnMethods = "addingProductToCart")
     public void checkOutProduct(){
        new HomePage(driver).clickOnCartIcon()
                .validateProductDetails(testData.getJsonData("product-names.item2.name"), testData.getJsonData("product-names.item2.price"));
     }

     @Test(dependsOnMethods = "checkOutProduct")
     public void fillInformationForm() {
        new CartPage(driver).clickCheckoutButton().
                enterInformationForm(testData.getJsonData("user-information.firstName"),
                testData.getJsonData("user-information.lastName"),
                testData.getJsonData("user-information.postalCode"))
                .validateInformationFormFilled(testData.getJsonData("user-information.firstName"),
                        testData.getJsonData("user-information.lastName"),
                        testData.getJsonData("user-information.postalCode"));

    }
    @Test(dependsOnMethods = "fillInformationForm")
    public void finishOrder(){
        new InformationPage(driver).clickContinueButton()
                .clickFinishButton()
                .validateConfirmationMessage(testData.getJsonData("confirmation-message"));
    }

    //Configurations
    @BeforeClass
    public void setUp(){
        String browserName = PropertiesUtils.getProperty("browserType");
        driver = new GUIDriver(browserName);
        //anonymous object
        new LoginPage(driver).navigateToLoginPage(); // Assuming LoginPage has a method to navigate to the login page
        testData = new JsonUtils("test-data");
    }



    //TearDown
    @AfterClass
        public void tearDown() {
        // Close the WebDriver instance after each test method
        driver.browserActions().closeBrowser();
        //CustomSoftAssertion.customAssertAll(); // Assert all custom soft assertions
    }
}
