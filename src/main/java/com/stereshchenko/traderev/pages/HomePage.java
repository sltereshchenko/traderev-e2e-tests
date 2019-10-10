package com.stereshchenko.traderev.pages;

import com.stereshchenko.traderev.utils.EnvProperties;
import com.stereshchenko.traderev.utils.WebDriverHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends AbstractPage {
    protected static final String URL = EnvProperties.HOME_URL;
    private static final Logger log = Logger.getLogger(HomePage.class);
    private static final String HEADER_XPATH = "//*[contains(@class,'site-header')]";
    private static final String HEADER_NAVIGATION_XPATH = HEADER_XPATH + "//nav[contains(@class,'nav-site')]";
    private static final String HEADER_TAB_XPATH = HEADER_NAVIGATION_XPATH
            + "//a[contains(@class,'nav-site__item')][contains(text(),'%s')]";
    @FindBy(xpath = HEADER_XPATH + "//a[contains(@class,'site-header__logo')]")
    protected WebElement headerLogo;
    @FindBy(xpath = HEADER_NAVIGATION_XPATH)
    protected WebElement headerMainNavigation;

    public HomePage(final WebDriver driver) {
        super(driver);
    }

    public HomePage navigateTo() {
        log.debug("Navigating to Home page:" + URL);
        driver.get(URL);
        waitForPage();
        return this;
    }

    public void waitForPage() {
        WebDriverHelper.waitForElementVisibility(driver, headerLogo, ELEMENT_APPEAR_TIMEOUT);
        WebDriverHelper.waitForElementVisibility(driver, headerMainNavigation, ELEMENT_APPEAR_TIMEOUT);
    }

    public IPage clickTab(String tab) {
        WebDriverHelper.findElementByXpath(driver, String.format(HEADER_TAB_XPATH, tab), ELEMENT_APPEAR_TIMEOUT)
                .click();
        IPage page = HomeTabsFactory.getTabPage(tab, driver);
        page.waitForPage();
        return page;
    }
}
