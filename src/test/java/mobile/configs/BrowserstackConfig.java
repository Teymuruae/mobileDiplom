package mobile.configs;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:browserstack.properties",
})
public interface BrowserstackConfig extends Config {

    @Key("user")
    String getUsername();

    @Key("password")
    String getPassword();
    @Key("url")
    String getUrl();
}

