package tests;

import core.configuration.Configuration;
import core.owner.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


public class DemoTest extends Configuration {

    @Test
    public void openUrl() {
        WebDriver driver = WebDriverFactory.newWebDriver();
        driver.get("https://www.google.com");
        driver.quit();
    }

}
