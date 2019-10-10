@browser
Feature: Verify careers opportunities

  # Scenario 1: check whether Canada TradeRev career page is displayed properly
  Scenario: Check Canada TradeRev "Careers" and "Canadian job" page is displayed properly
    Given Visit "Home" page
    When Navigate to "Careers" tab
    Then Verify "Careers" page is displayed properly
    When Click on "Canadian Opportunities" button
    Then Verify "Canadian job" page is displayed properly

  # Scenario 2: check whether job filter (city) is working properly
  Scenario: Check CITY job filter on "Canadian job" page is working properly
    Given Visit "Canadian job" page
    When Filter the Search results by CITY as "Toronto, Ontario, Canada"
    Then Verify all the job results belong to "Toronto, Ontario, Canada" location

  # Scenario 3: check whether job filters (city and team) are working properly
  Scenario: Check CITY and TEAM job filters on "Canadian job" page are working properly
    Given Visit "Canadian job" page
    When Filter the Search results by CITY as "Toronto, Ontario, Canada"
    And Filter the Search results by TEAM as "Engineering"
    Then Verify all the job results belong to "Toronto, Ontario, Canada" location
    And Verify all the job results belong to "Engineering" team
    And Log the total available positions listed