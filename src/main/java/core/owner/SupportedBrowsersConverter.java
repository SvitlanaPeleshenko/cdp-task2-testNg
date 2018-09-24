package core.owner;

import core.controller.driver.SupportedBrowsers;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;

/**
 * Created by Svitlana_Peleshenko on 7/5/2018.
 */
public class SupportedBrowsersConverter implements Converter<SupportedBrowsers> {

    public SupportedBrowsers convert(Method method, String s) {
        return SupportedBrowsers.valueOf(s);
    }
}
