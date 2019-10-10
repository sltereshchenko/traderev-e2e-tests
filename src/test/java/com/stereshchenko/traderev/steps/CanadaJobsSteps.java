package com.stereshchenko.traderev.steps;

import com.stereshchenko.traderev.models.Job;
import com.stereshchenko.traderev.pages.CanadianJobsPage;
import com.stereshchenko.traderev.setup.Environment;
import io.cucumber.java8.En;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CanadaJobsSteps implements En {
    private static final Logger log = Logger.getLogger(CanadaJobsSteps.class);
    private final Environment env;

    public CanadaJobsSteps(final Environment environment) {
        this.env = environment;

        When("^Filter the Search results by (CITY|TEAM|WORK TYPE) as \"([^\"]*)\"$",
                (final String filterName, final String filterValue) -> {
                    CanadianJobsPage canadianJobsPage = new CanadianJobsPage(env.driver);
                    canadianJobsPage.filterJobs(filterName, filterValue);
                });

        Then("^Verify all the job results belong to \"([^\"]*)\" (title|location|team|commitment)$",
                (final String expectedValue, final String key) -> {
                    CanadianJobsPage canadianJobsPage = new CanadianJobsPage(env.driver);
                    List<Job> jobs = canadianJobsPage.getJobs();
                    log.debug("TOTAL POSITIONS LISTED: " + jobs.size());
                    List<Job> misFilteredJobs = jobs.stream().filter(j -> {
                        String actualValue;
                        switch (key) {
                            case "title":
                                actualValue = j.title;
                                break;
                            case "location":
                                actualValue = j.location;
                                break;
                            case "team":
                                actualValue = j.team;
                                return !StringUtils.containsIgnoreCase(actualValue, expectedValue);
                            case "commitment":
                                actualValue = j.commitment;
                                break;
                            default:
                                throw new UnsupportedOperationException("Unsupported Job Details key:" + key);
                        }
                        return !actualValue.equalsIgnoreCase(expectedValue);
                    }).collect(Collectors.toList());
                    assertThat(misFilteredJobs.stream().map(j -> j.title).collect(Collectors.joining(",")) +
                            " jobs have unexpected values for " + key, misFilteredJobs.size(), is(0));
                });

        Then("^Log the total available positions listed$",
                () -> {
                    CanadianJobsPage canadianJobsPage = new CanadianJobsPage(env.driver);
                    List<Job> jobs = canadianJobsPage.getJobs();
                    log.info("TOTAL AVAILABLE POSITIONS LISTED: " + jobs.size());
                });

    }
}
