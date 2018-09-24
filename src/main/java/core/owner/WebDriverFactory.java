package core.owner;

import core.controller.driver.SupportedBrowsers;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;

/**
 * Created by Svitlana_Peleshenko on 8/13/2018.
 */
public class WebDriverFactory {
    public static WebDriver newWebDriver(SupportedBrowsers browserName){
        Browsers browser= ConfigFactory.create(Browsers.class);
        if(browserName.name().equals("LOCAL_CHROME")){
           return browser.getBrowser().getWebDriver();
        } else if(browserName.name().equals("REMOTE_CHROME")){
            return browser.getBrowser().getWebDriver();
        } else{
            return browser.getBrowser().getWebDriver();
        }


    }
}
