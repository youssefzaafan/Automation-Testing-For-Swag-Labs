package com.swaglabs.utils;

import io.qameta.allure.Allure;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.google.common.base.StandardSystemProperty.USER_HOME;

public class AllureUtils {

    public static final String ALLURE_RESULTS_PATH= "test-outputs/allure-results";

    private AllureUtils(){
        super();
    }
    public static void attachLogsToAllureReport(){
        try {
            File logFile = FilesUtils.getLatestFile(LogsUtil.LOGS_PATH);
            if (!logFile.exists()){
                LogsUtil.warn("Log file doesn't exist: "+LogsUtil.LOGS_PATH);
                return;
            }
            Allure.addAttachment("logs.log", Files.readString(Path.of(logFile.getPath())));
            LogsUtil.info("Logs attached to Allure report");
        } catch (Exception e) {
            LogsUtil.error("Failed to attach logs to Allure report: "+ e.getMessage());
        }

    }

    public static void attachScreenshotToAllure(String screenshotName, String screenshotPath){
        try {
            Allure.addAttachment(screenshotName,Files.newInputStream(Path.of(screenshotPath)));

        } catch (Exception e) {
            LogsUtil.error("Failed to attach screenshot to Allure report: " + e.getMessage());
        }
    }

}
