package com.stereshchenko.traderev.pages;

import com.stereshchenko.traderev.utils.WebDriverHelper;
import org.openqa.selenium.WebDriver;


public class HomeTabsFactory {
    private HomeTabsFactory() {
    }

    public static IPage getTabPage(String tabName, WebDriver driver) {
        IPage page;
        switch (tabName) {
            case "Careers":
                WebDriverHelper.closeAndSwitchToNextTab(driver);
                page = new CareersPage(driver);
                break;
            case "Plans":
            case "Promos":
            default:
                throw new UnsupportedOperationException("HomeTabsFactory does not support:" + tabName);
        }
        return page;
    }
}
