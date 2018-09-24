package core.owner;

import core.controller.driver.SupportedBrowsers;
import org.aeonbits.owner.Config;

/**
 * Created by Svitlana_Peleshenko on 8/1/2018.
 */
@Config.Sources("classpath:properties")
public interface Browsers extends Config {

    @Config.Key("browser")
    @Config.ConverterClass(SupportedBrowsersConverter.class)
    SupportedBrowsers getBrowser();

}
