package core.controller.driver;

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
            String hubURL = "http://10.23.10.98:4445/wd/hub";
            DesiredCapabilities capability = DesiredCapabilities.firefox();
            capability.setCapability("binary", "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
            WebDriver driver = null;
            try {
                driver = new RemoteWebDriver(new URL(hubURL), capability);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return driver;
        }

}