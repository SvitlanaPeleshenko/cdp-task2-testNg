package core.controller.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Svitlana_Peleshenko on 7/31/2018.
 */
public class RemoteChromeInvoker implements WebDriverInvoker {
    public WebDriver invokeWebDriver() {
        String hubURL = "http://10.23.10.98:4444/wd/hub";
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(hubURL), capability);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }
}
