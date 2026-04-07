package com.swaglabs.utils;

import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

public class CustomSoftAssertion extends SoftAssert {

    public static CustomSoftAssertion softAssertion= new CustomSoftAssertion();

    public static void customAssertAll(ITestResult result) {
        try {
            softAssertion.assertAll("Custom Soft Assertions");
        } catch (AssertionError e) {
            System.out.println("Soft assertions failed "+ e.getMessage());
            result.setStatus(ITestResult.FAILURE);
            result.setThrowable(e);

        }
        finally {
            reInitializeSoftAssert();
        }
    }
    private static void reInitializeSoftAssert(){
        softAssertion = new CustomSoftAssertion();
    }


}
