package core.owner;


import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

public interface TestProperties extends Config {

    static TestProperties getInstance() {
        return ConfigFactory.create(TestProperties.class,
                        System.getProperties(),
                        System.getenv());
    }

    @DefaultValue("http://localhost")
    @Key("selenoid.host")
    String selenoidHost();

    @DefaultValue("4444")
    @Key("selenoid.port")
    String selenoidPort();

    @DefaultValue("LOCAL_CHROME")
    @Key("selenoid.browser.type")
    String browserType();

}
