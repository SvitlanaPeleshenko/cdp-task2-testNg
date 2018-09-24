package core.controller.driver;


import org.openqa.selenium.WebDriver;

/**
 * Created by Svitlana_Peleshenko on 7/31/2018.
 */
public enum SupportedBrowsers {
    LOCAL_CHROME(new LocalChromeInvoker()),
    REMOTE_CHROME(new RemoteChromeInvoker()),
    REMOTE_FIREFOX(new RemoteFirefoxInvoker());


    private WebDriverInvoker driver;
    SupportedBrowsers(WebDriverInvoker webDriver) {
        this.driver=webDriver;
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + this.name());
    }

   public WebDriver getWebDriver(){
       return driver.invokeWebDriver();
   }

}
