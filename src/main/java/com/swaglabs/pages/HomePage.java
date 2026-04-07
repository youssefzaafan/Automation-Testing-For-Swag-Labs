package com.swaglabs.pages;

import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.utils.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

public class HomePage {
    //Variables
    private GUIDriver driver;
    //Locators
    By cartIconLocator = By.className("shopping_cart_link");
    //Constructor
    public HomePage(GUIDriver driver) {
        this.driver = driver;
    }
    //Actions
    @Step("Navigating to Home Page")
    public HomePage navigateToHomePage(){
        driver.browserActions().navigateToUrl(PropertiesUtils.getProperty("homeURL"));
        return this;
    }

    //Relative + Dynamic locator
    @Step("Clicking on product: {productName}")
    public HomePage clickOnProduct(String productName) {
        LogsUtil.info("Clicking on product:{} " , productName);
       // By addToCartButtonLocator = RelativeLocator.with(By.tagName("button")).below(By.xpath("//div[text()='" + productName + "']"));
        By addToCartButtonLocator =
                By.xpath("//div[text()='" + productName + "']/following::button[1]");
        System.out.println("**************************************************");
        System.out.println("addToCartButtonLocator: " + addToCartButtonLocator);
        driver.elementActions().clickElement(addToCartButtonLocator);
        return this;
    }


    @Step("Validating product added to cart: {productName}")
    public HomePage validateProductAddedToCart(String productName) {
        LogsUtil.info("Clicking on product:{} " , productName);
      //  By addToCartButtonLocator = RelativeLocator.with(By.tagName("button")).below(By.xpath("//div[text()='" + productName + "']"));
        By addToCartButtonLocator =
                By.xpath("//div[text()='" + productName + "']/following::button[1]");
        String actualValue = driver.elementActions().getElementText(addToCartButtonLocator);
        LogsUtil.info("Actual value:{} " , actualValue);
        driver.validations().validateEqual(actualValue, "Remove", "Product should be added to cart");
        LogsUtil.info("Product added to cart successfully:{} " , productName);
        return this;
    }
    @Step("Clicking on cart icon")
    public CartPage clickOnCartIcon() {
        driver.elementActions().clickElement(cartIconLocator);
        return new CartPage(driver);
    }
}
