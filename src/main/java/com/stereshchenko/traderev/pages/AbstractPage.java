package com.stereshchenko.traderev.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage implements IPage {
    public static final long ELEMENT_APPEAR_TIMEOUT = 5;
    protected final WebDriver driver;

    protected AbstractPage(final WebDriver driver) {
        this.driver = driver;
        init();
    }

    protected void init() {
        PageFactory.initElements(driver, this);
    }
}
