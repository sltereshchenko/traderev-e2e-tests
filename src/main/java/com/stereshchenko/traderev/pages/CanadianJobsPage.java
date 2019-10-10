package com.stereshchenko.traderev.pages;

import com.stereshchenko.traderev.models.Job;
import com.stereshchenko.traderev.utils.EnvProperties;
import com.stereshchenko.traderev.utils.WebDriverHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CanadianJobsPage extends AbstractPage {
    protected static final String URL = EnvProperties.CANADA_JOBS_URL;
    private static final Logger log = Logger.getLogger(CanadianJobsPage.class);
    private static final int FILTERS_COUNT = 3;
    private static final String FILTER_VALUE_XPATH = "..//div[contains(@class,'filter-popup')]//a[text()='%s']";
    private static final String JOB_TITLE_XPATH = "//a[contains(@class,'posting-title')]";
    private static final String JOB_TITLE_DETAILS_XPATH = "//span[contains(@class,'%s')]";
    private static final String JOB_TITLE_NAME_XPATH = "//*[@data-qa='posting-name']";
    @FindBy(xpath = "//a[contains(@class,'main-header-logo')]//img")
    protected WebElement headerLogo;

    @FindBy(xpath = "//div[contains(@class,'filter-bar')]//div[@class='filter-button']")
    protected List<WebElement> filterButtons;

    @FindBy(xpath = "//div[@class ='posting']")
    protected List<WebElement> postedJobs;


    public CanadianJobsPage(final WebDriver driver) {
        super(driver);
    }

    public CanadianJobsPage navigateTo() {
        log.debug("Navigating to Canadian Jobs page:" + URL);
        driver.get(URL);
        waitForPage();
        return this;
    }


    public void waitForPage() {
        WebDriverHelper.waitForElementVisibility(driver, headerLogo, ELEMENT_APPEAR_TIMEOUT);
    }

    public boolean isFilterDisplayed() {
        filterButtons.forEach(filterButton -> WebDriverHelper.waitForElementVisibility(driver, filterButton, ELEMENT_APPEAR_TIMEOUT));
        return filterButtons.size() == FILTERS_COUNT;
    }

    public void filterJobs(final String filterName, final String filterValue) {
        Optional<WebElement> filteredButton = filterButtons.stream().filter(
                b -> b.getText().equals(filterName)).findFirst();
        if (filteredButton.isPresent()) {
            WebElement filterButton = filteredButton.get();
            filterButton.click();
            filterButton.findElement(By.xpath(String.format(FILTER_VALUE_XPATH, filterValue))).click();
        } else {
            throw new UnsupportedOperationException("Filter is not present on UI:" + filterName);
        }
    }

    public List<Job> getJobs() {
        return postedJobs.stream().map(j -> {
            WebElement jobTitle = j.findElement(By.xpath(JOB_TITLE_XPATH));
            String title = j.findElement(By.xpath(JOB_TITLE_NAME_XPATH)).getText();
            String location = jobTitle.findElement(By.xpath(String.format(JOB_TITLE_DETAILS_XPATH, "sort-by-location"))).getText();
            String team = jobTitle.findElement(By.xpath(String.format(JOB_TITLE_DETAILS_XPATH, "sort-by-team"))).getText();
            String commitment = jobTitle.findElement(By.xpath(String.format(JOB_TITLE_DETAILS_XPATH, "sort-by-commitment"))).getText();
            return new Job(title, location, team, commitment);
        }).collect(Collectors.toList());
    }

}
