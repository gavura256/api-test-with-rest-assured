package org.gavura.utility;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ReadApplicationProperties {
    @SneakyThrows
    private static void readProperties() {
        System.getProperties()
                .load(ClassLoader.getSystemResourceAsStream("application.properties"));
    }

    @SneakyThrows
    public static String readBaseUrl() {
        readProperties();

        return System.getProperty("url");
    }

    @SneakyThrows
    public static String readApiKey(){
        readProperties();

        return System.getProperty("api_key");
    }

}
