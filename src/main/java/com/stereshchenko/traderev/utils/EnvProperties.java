package com.stereshchenko.traderev.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.MissingResourceException;
import java.util.Properties;

public class EnvProperties {
    private static final String ENV_PROPERTIES_FILE = System.getProperty("envConfigFile", "production.properties");
    private static final Properties PROPERTIES = loadProperties(ENV_PROPERTIES_FILE);

    public static final String BROWSER = PROPERTIES.getProperty("browser");
    public static final String HOME_URL = PROPERTIES.getProperty("home.url");
    public static final String CAREERS_URL = PROPERTIES.getProperty("careers.url");
    public static final String CANADA_JOBS_URL = PROPERTIES.getProperty("canada.jobs.url");


    private EnvProperties() {
    }

    private static Properties loadProperties(String file) {
        Properties properties = new Properties();
        final InputStream inputStream = EnvProperties.class.getResourceAsStream("/envconfigs/" + file);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new MissingResourceException("Exception when try read property file:" + e.getMessage(), file, file);
        }
        return properties;
    }
}
