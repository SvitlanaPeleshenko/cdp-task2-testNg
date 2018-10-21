package core.owner;

import core.controller.driver.SupportedBrowsers;
import org.openqa.selenium.WebDriver;

/**
 * Created by Svitlana_Peleshenko on 8/13/2018.
 */
public class WebDriverFactory {

    public static WebDriver newWebDriver() {
        TestProperties properties = TestProperties.getInstance();
        SupportedBrowsers browser = SupportedBrowsers.valueOf(properties.browserType());
        return browser.getWebDriver();
    }

}
