package com.swaglabs.drivers;

import com.swaglabs.utils.PropertiesUtils;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.Map;

public class EdgeFactory extends AbstractDriver implements WbDriverOptionsAbstract<EdgeOptions> {
    @Override
    public EdgeOptions getOptions() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");
        edgeOptions.addArguments("--disable-extensions");
        edgeOptions.addArguments("--disable-infobars");
        edgeOptions.addArguments("--disable-notifications");
        edgeOptions.addArguments("--remote-allow-orginis=*");
        edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        Map<String, Object> edgePrefs = Map.of("profile.default_content_setting_values.notifications", 2,
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "autofill.profile_enabled",false);

        edgeOptions.setExperimentalOption("prefs", edgePrefs);
        if (!PropertiesUtils.getProperty("executionType").equalsIgnoreCase("local")) {
            edgeOptions.addArguments("--headless");}
        return  edgeOptions;
    }

    @Override
    public WebDriver createDriver() {
        return new EdgeDriver(getOptions());
    }
}
