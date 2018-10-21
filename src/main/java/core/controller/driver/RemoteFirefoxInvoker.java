package core.controller.driver;

import core.owner.TestProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Svitlana_Peleshenko on 7/31/2018.
 */
public class RemoteFirefoxInvoker implements WebDriverInvoker {

    public WebDriver invokeWebDriver() {
        TestProperties properties = TestProperties.getInstance();

        String hubURL = String.format("%s:%s/firefox", properties.selenoidHost(),
                properties.selenoidPort());

        DesiredCapabilities capability = DesiredCapabilities.firefox();
        try {
            return new RemoteWebDriver(new URL(hubURL), capability);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize firefox driver", e);
        }
    }

}