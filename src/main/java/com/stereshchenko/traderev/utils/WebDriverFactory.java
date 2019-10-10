package com.stereshchenko.traderev.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
    private WebDriverFactory() {
    }

    public static WebDriver getDriverInstance(String browserName) {
        WebDriver driver;
        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new UnsupportedOperationException("WebDriverFactory does not support:" + browserName);
        }
        driver.manage().window().maximize();
        return driver;
    }
}
