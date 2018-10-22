package tests;

import core.configuration.Configuration;
import core.controller.driver.RemoteDriverInvoker;
import core.owner.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


public class DemoTest extends Configuration {

    @Test
    public void openUrl() {
        WebDriver driver = new RemoteDriverInvoker().invokeWebDriver();
        driver.get("https://www.google.com");
        driver.quit();
    }

}
