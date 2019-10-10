package com.stereshchenko.traderev.steps;

import com.stereshchenko.traderev.pages.CanadianJobsPage;
import com.stereshchenko.traderev.pages.CareersPage;
import com.stereshchenko.traderev.setup.Environment;
import io.cucumber.java8.En;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class BaseVerificationSteps implements En {
    private final Environment env;

    public BaseVerificationSteps(final Environment environment) {
        this.env = environment;
        Then("^Verify \"([^\"]*)\" page is displayed properly$", (final String pageName) -> {
            WebDriver driver = env.driver;
            boolean isDisplayedProperly;
            switch (pageName) {
                case "Careers":
                    CareersPage careersPage = new CareersPage(driver);
                    isDisplayedProperly = careersPage.isAllJobLinksDisplayed();
                    break;
                case "Canadian job":
                    CanadianJobsPage canadianJobsPage = new CanadianJobsPage(driver);
                    isDisplayedProperly = canadianJobsPage.isFilterDisplayed();
                    break;
                default:
                    throw new UnsupportedOperationException("Verification is not implemented for the page :" + pageName);
            }
            assertThat(pageName + " page is not displayed properly", isDisplayedProperly, is(true));
        });

    }
}
