package com.stereshchenko.traderev.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

import static java.util.concurrent.TimeUnit.SECONDS;

public class WebDriverHelper {

    private WebDriverHelper() {
    }

    /**
     * In browser close current tab and switch to next tab
     *
     * @param webDriver
     */
    public static void closeAndSwitchToNextTab(final WebDriver webDriver) {
        webDriver.close();
        Set<String> tabs = webDriver.getWindowHandles();
        webDriver.switchTo().window(tabs.iterator().next());
    }

    public static void waitForElementVisibility(final WebDriver webDriver, final WebElement webElement, final long timeOutSeconds) {
        new WebDriverWait(webDriver, timeOutSeconds).until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitForElementInVisibility(final WebDriver webDriver, final WebElement webElement, final long timeOutSeconds) {
        new WebDriverWait(webDriver, timeOutSeconds).until(ExpectedConditions.invisibilityOf(webElement));
    }

    public static void waitForURL(final WebDriver webDriver, final String url, final long timeOutSeconds) {
        new WebDriverWait(webDriver, timeOutSeconds).until(ExpectedConditions.urlToBe(url));
    }

    public static void waitNextAction(final WebDriver webDriver, final long timeOutSeconds) {
        webDriver.manage().timeouts().implicitlyWait(timeOutSeconds, SECONDS);
    }

    public static WebElement findElementByXpath(final WebDriver webDriver, final String xpath, final long timeOutSeconds) {
        waitNextAction(webDriver, timeOutSeconds);
        return webDriver.findElement(By.xpath(xpath));
    }


    public static void moveToElement(final WebDriver webDriver, final WebElement webElement) {
        (new Actions(webDriver)).moveToElement(webElement).build().perform();
    }

}
