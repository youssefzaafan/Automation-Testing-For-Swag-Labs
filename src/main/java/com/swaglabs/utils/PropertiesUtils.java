package com.swaglabs.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

public class PropertiesUtils {
    private PropertiesUtils() {
        // Private constructor to prevent instantiation
    }
    private static final String PROPERTIES_FILE_PATH = "src/main/resources/";

    public static Properties loadProperties() {
        try  {
            Properties properties = new Properties();
            Collection<File> propertiesFileList;
            propertiesFileList = FileUtils.listFiles(new File(PROPERTIES_FILE_PATH), new String[]{"properties"}, true);
            propertiesFileList.forEach(propertyFile -> {
                try {
                    properties.load(new FileInputStream(propertyFile));
                } catch (IOException ioe) {
                    LogsUtil.error(ioe.getMessage());
                }
                properties.putAll(System.getProperties());
                System.getProperties().putAll(properties);
            });
                    LogsUtil.info("Loading Properites File Data");
            return properties;
        } catch (Exception e) {
            LogsUtil.error("Error loading properties file: " + e.getMessage());
        }
        return null;
    }

    //Get the value of the property by key
    public static String getProperty(String key) {
        try {
            return System.getProperty(key);
        } catch (Exception e) {
            LogsUtil.error(e.getMessage());
            return "";
        }
    }
}
