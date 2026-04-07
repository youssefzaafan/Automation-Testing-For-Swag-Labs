package com.swaglabs.drivers;

import com.swaglabs.utils.PropertiesUtils;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Map;

public class ChromeFactory extends AbstractDriver implements WbDriverOptionsAbstract<ChromeOptions> {
    @Override
    public ChromeOptions getOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--remote-allow-orginis=*");
        //**************************************************//
        //important Ways to close change password pop up
        chromeOptions.addArguments("--guest");
        //chromeOptions.addArguments("--disable-save-password-bubble");
        //chromeOptions.addArguments("--incognito");
        //**************************************************//
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        //hash map is better than map.of immutable map
        Map<String, Object> prefs = Map.of("profile.default_content_setting_values.notifications", 2,
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "autofill.profile_enabled",false);
//                Map<String, Object> prefs = new HashMap<>();
//                prefs.put("profile.default_content_setting_values.notifications", 2);
//                prefs.put("credentials_enable_service", false);
//                prefs.put("profile.password_manager_enabled", false);
//                prefs.put("autofill.profile_enabled", false);

        chromeOptions.setExperimentalOption("prefs", prefs);

        if (!PropertiesUtils.getProperty("executionType").equalsIgnoreCase("local")) {
            chromeOptions.addArguments("--headless");
        }

        return chromeOptions;    }

    @Override
    public WebDriver createDriver() {
        return new ChromeDriver(getOptions());

    }
}
