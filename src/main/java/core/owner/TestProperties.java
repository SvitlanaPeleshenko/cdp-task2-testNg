package core.owner;


import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

public interface TestProperties extends Config {

    static TestProperties getInstance() {
        return ConfigFactory.create(TestProperties.class,
                        System.getProperties(),
                        System.getenv());
    }

    @DefaultValue("localhost")
    @Key("selenoid.host")
    String selenoidHost();

    @DefaultValue("4444")
    @Key("selenoid.port")
    String selenoidPort();

    @DefaultValue("/wd/hub")
    @Key("selenoid.context.path")
    String hubContextPath();

    @DefaultValue("")
    @Key("selenoid.browser.version")
    String browserVersion();

    @DefaultValue("chrome")
    @Key("selenoid.browser.name")
    String browserName();

    @DefaultValue("false")
    @Key("selenoid.use.vnc")
    boolean useVnc();

}
