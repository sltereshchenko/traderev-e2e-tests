package com.stereshchenko.traderev.steps.hooks;

import com.stereshchenko.traderev.setup.Environment;
import com.stereshchenko.traderev.utils.EnvProperties;
import com.stereshchenko.traderev.utils.WebDriverFactory;
import io.cucumber.java8.En;


public class CucumberSteps implements En {
    private final Environment env;

    public CucumberSteps(Environment environment) {
        env = environment;
        Before("@browser", () -> env.driver = WebDriverFactory.getDriverInstance(EnvProperties.BROWSER));

        After("@browser", () -> env.driver.quit());
    }

}
