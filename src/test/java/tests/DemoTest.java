package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import core.configuration.Configuration;
import core.controller.driver.SupportedBrowsers;
import core.owner.WebDriverFactory;


public class DemoTest extends Configuration {

    @Test(dataProvider = "dataWithConfig")
    public void openUrl(String url){
        // Browsers browser= ConfigFactory.create(Browsers.class);
        // browser.getBrowser().getWebDriver();
        WebDriver driver= WebDriverFactory.newWebDriver(SupportedBrowsers.LOCAL_CHROME);
        driver.get(url);
        driver.quit();
    }
}
