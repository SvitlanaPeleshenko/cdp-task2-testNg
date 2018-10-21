package core.controller.driver;

import core.owner.TestProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;


public class RemoteChromeInvoker implements WebDriverInvoker {

    public WebDriver invokeWebDriver() {
        TestProperties properties = TestProperties.getInstance();

        String hubURL = String.format("%s:%s/wd/hub", properties.selenoidHost(),
                properties.selenoidPort());

        System.out.println("Init remote driver, Hub URL: " + hubURL);

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setVersion("70.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);

        try {
            return new RemoteWebDriver(new URL(hubURL), capabilities);
        } catch (Exception e) {
            throw new RuntimeException("failed to initialize selenoid chrome driver", e);
        }
    }

}
