package com.swaglabs.utils;

import com.swaglabs.drivers.GUIDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

import static com.swaglabs.utils.TimestampUtils.getCurrentTimestamp;

public class ScreenshotsUtils {
    public  static final  String SCREENSHOTS_PATH= "test-outputs/screenshots/";

    private ScreenshotsUtils(){
        super();
    }

    public static void takeScreenshot(WebDriver driver,String screenshotName){

       try {
           File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
           File screenshotFile = new File(SCREENSHOTS_PATH+ screenshotName +"_" + getCurrentTimestamp() + ".png");
           FileUtils.copyFile(screenshot,screenshotFile);
           AllureUtils.attachScreenshotToAllure(screenshotName,screenshotFile.getPath());

       } catch (Exception e) {
           LogsUtil.error("Failed to take screenshot: "+ e.getMessage());
       }


    }
}
