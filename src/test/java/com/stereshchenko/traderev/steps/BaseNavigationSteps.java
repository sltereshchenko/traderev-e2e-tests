package com.stereshchenko.traderev.steps;

import com.stereshchenko.traderev.pages.CanadianJobsPage;
import com.stereshchenko.traderev.pages.CareersPage;
import com.stereshchenko.traderev.pages.HomePage;
import com.stereshchenko.traderev.setup.Environment;
import io.cucumber.java8.En;
import org.openqa.selenium.WebDriver;

public class BaseNavigationSteps implements En {
    private final Environment env;

    public BaseNavigationSteps(final Environment environment) {
        this.env = environment;

        Given("^Visit \"([^\"]*)\" page$", (final String pageName) -> {
            final WebDriver driver = env.driver;
            switch (pageName) {
                case "Home":
                    new HomePage(driver).navigateTo();
                    break;
                case "Canadian job":
                    new CanadianJobsPage(driver).navigateTo();
                    break;
                default:
                    throw new UnsupportedOperationException("'Visit page step' is not implemented for the page :" + pageName);
            }
        });

        When("^Navigate to \"([^\"]*)\" tab$", (final String pageName) ->
                new HomePage(env.driver).clickTab(pageName)
        );

        When("^Click on \"([^\"]*)\" button$", (final String buttonName) -> {
            CareersPage careersPage = new CareersPage(env.driver);
            careersPage.clickOpportunitiesButton(buttonName);
        });

    }
}
