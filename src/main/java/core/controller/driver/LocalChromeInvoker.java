package core.controller.driver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Svitlana_Peleshenko on 7/31/2018.
 */
public class LocalChromeInvoker implements WebDriverInvoker {

    public WebDriver invokeWebDriver() {
        ChromeDriverManager.getInstance().setup();
        return new ChromeDriver();
    }
}
