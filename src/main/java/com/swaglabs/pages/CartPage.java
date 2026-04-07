package com.swaglabs.pages;

import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.utils.CustomSoftAssertion;
import com.swaglabs.utils.ElementActions;
import com.swaglabs.utils.LogsUtil;
import com.swaglabs.utils.Validations;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    //Variables
    private GUIDriver driver;

    //Constructor
    public CartPage(GUIDriver driver) {
        this.driver = driver;
    }

    //Locators
    private final By productName = By.className("inventory_item_name");
    private final By checkoutButton = By.id("checkout");
    private final By continueShoppingButton = By.id("continue-shopping");
    private final By removeButton = By.xpath("//button[text()='Remove']");
    private final By priceLocator = By.className("inventory_item_price");
    //Actions
    @Step("Get product name from cart")
    private String getProductName(){
        String productNameText = driver.elementActions().getElementText(productName);
        LogsUtil.info("Product name retrieved: " ,productNameText);
        return productNameText;
    }
    @Step("Get product price from cart")
    private String getProductPrice(){
        String productPriceText = driver.elementActions().getElementText(priceLocator);
        LogsUtil.info("Product price retrieved: " , productPriceText);
        return productPriceText;
    }
    @Step("Click checkout button")
    public InformationPage clickCheckoutButton(){
        driver.elementActions().clickElement(checkoutButton);
        LogsUtil.info("Clicked checkout button");
        return new InformationPage(driver);
    }
    @Step("Click continue shopping button")
    public CartPage clickContinueShoppingButton(){
        driver.elementActions().clickElement(continueShoppingButton);
        LogsUtil.info("Clicked continue shopping button");
        return this;
    }
    @Step("Click remove button")
    public CartPage clickRemoveButton(){
        driver.elementActions().clickElement(removeButton);
        LogsUtil.info("Clicked remove button");
        return this;
    }
    //Validations
    @Step("Validate product details - Name: {productName}, Price: {productPrice}")
    public CartPage validateProductDetails(String productName ,String productPrice){
        String actualProductName = getProductName();
        String actualProductPrice = getProductPrice();
        LogsUtil.info("Validating product details - Expected Name: " + productName + ", Expected Price: " + productPrice);
        LogsUtil.info("Actual Name: " + actualProductName + ", Actual Price: " + actualProductPrice);
        //Make it with soft assertion to didn't block with one fail
        CustomSoftAssertion.softAssertion.assertEquals(actualProductName, productName, "Product name should match");
        CustomSoftAssertion.softAssertion.assertEquals(actualProductPrice, productPrice, "Product price should match");
        LogsUtil.info("Product details validation completed");
        return this;
    }

}
