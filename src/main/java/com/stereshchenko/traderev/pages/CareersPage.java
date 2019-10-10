package com.stereshchenko.traderev.pages;

import com.stereshchenko.traderev.utils.EnvProperties;
import com.stereshchenko.traderev.utils.WebDriverHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CareersPage extends AbstractPage {
    protected static final String URL = EnvProperties.CAREERS_URL;
    private static final int JOB_LINKS_COUNT = 2;
    private static final String JOB_BUTTONS_XPATH = "//div[contains(@class,'job-links')]//a[contains(@class,'job-links__button')]";
    private static final String JOB_BUTTON_XPATH = JOB_BUTTONS_XPATH + "[contains(text(),'%s')]";
    @FindBy(xpath = "//*[contains(@class,'site-title')]//img")
    protected WebElement headerLogo;

    @FindBy(xpath = JOB_BUTTONS_XPATH)
    protected List<WebElement> jobButtons;

    public CareersPage(WebDriver driver) {
        super(driver);
    }

    public void waitForPage() {
        WebDriverHelper.waitForElementVisibility(driver, headerLogo, ELEMENT_APPEAR_TIMEOUT);
    }

    public boolean isAllJobLinksDisplayed() {
        jobButtons.forEach(jobButton -> WebDriverHelper.waitForElementVisibility(driver, jobButton, ELEMENT_APPEAR_TIMEOUT));
        return jobButtons.size() == JOB_LINKS_COUNT;
    }

    public IPage clickOpportunitiesButton(String buttonName) {
        WebDriverHelper.findElementByXpath(driver, String.format(JOB_BUTTON_XPATH, buttonName), ELEMENT_APPEAR_TIMEOUT)
                .click();
        WebDriverHelper.closeAndSwitchToNextTab(driver);
        CanadianJobsPage canadianJobsPage = new CanadianJobsPage(driver);
        canadianJobsPage.waitForPage();
        return canadianJobsPage;
    }
}
