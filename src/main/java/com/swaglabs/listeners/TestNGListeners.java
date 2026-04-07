package com.swaglabs.listeners;

import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.utils.*;
import org.apache.commons.io.FileUtils;
import org.testng.IExecutionListener;
import org.testng.IInvokedMethodListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

import static com.swaglabs.utils.PropertiesUtils.loadProperties;

public class TestNGListeners implements IExecutionListener,ITestListener, IInvokedMethodListener {
    File allure_results = new File("test-outputs/allure-results");
    File logs = new File("test-outputs/logs");
    File screenshots = new File("test-outputs/screenshots");

    @Override
    public void onExecutionStart() {
        LogsUtil.info("Test Execution started");
        loadProperties();
        FilesUtils.deleteFiles(allure_results);
        FilesUtils.cleanDirectory(logs);
        FilesUtils.cleanDirectory(screenshots);

    }

    @Override
    public void onExecutionFinish() {
        LogsUtil.info("Test Execution finished");

    }

    @Override
    public void onTestSuccess(org.testng.ITestResult result) {
        LogsUtil.info("Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(org.testng.ITestResult result) {
        LogsUtil.info("Test failed: " + result.getName());
    }



    @Override
    public void onTestSkipped(org.testng.ITestResult result) {
        LogsUtil.info("Test skipped: " + result.getName());
    }

    @Override
    public void afterInvocation(org.testng.IInvokedMethod method, org.testng.ITestResult testResult) {
        //Not configuration methods
        if (method.isTestMethod()) {
            CustomSoftAssertion.customAssertAll(testResult);

            switch (testResult.getStatus()){
                case ITestResult.SUCCESS -> ScreenshotsUtils.takeScreenshot(GUIDriver.getDriver(),"passed-"+testResult.getName());
                case ITestResult.FAILURE -> ScreenshotsUtils.takeScreenshot(GUIDriver.getDriver(),"passed-"+testResult.getName());
                case ITestResult.SKIP -> ScreenshotsUtils.takeScreenshot(GUIDriver.getDriver(),"passed-"+testResult.getName());

            }
            AllureUtils.attachLogsToAllureReport();
        }
    }

}
