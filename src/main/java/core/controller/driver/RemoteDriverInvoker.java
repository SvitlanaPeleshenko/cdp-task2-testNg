package core.controller.driver;

import core.owner.TestProperties;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;


public class RemoteDriverInvoker implements WebDriverInvoker {

    public WebDriver invokeWebDriver() {
        TestProperties properties = TestProperties.getInstance();

        String hubURL = String.format("%s:%s%s",
                properties.selenoidHost(),
                properties.selenoidPort(),
                properties.hubContextPath());

        System.out.println("Init remote driver, Hub URL: " + hubURL);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(properties.browserName());
        capabilities.setVersion(properties.browserVersion());
        capabilities.setPlatform(Platform.ANY);
        capabilities.setCapability("enableVNC", properties.useVnc());
        capabilities.setCapability("enableVideo", false);

        try {
            return new RemoteWebDriver(new URL(hubURL), capabilities);
        } catch (Exception e) {
            throw new RuntimeException("failed to initialize selenoid driver", e);
        }
    }

}
