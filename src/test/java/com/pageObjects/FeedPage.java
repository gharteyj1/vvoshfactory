package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Created by user on 23/06/2016.
 */
public class FeedPage extends AbstractPage {

    public FeedPage (WebDriver driver, JavascriptExecutor jsexec){
        super (driver, jsexec);
    }

    private By pageConfirmation = By.xpath("//div[contains(@id, 'content-feed')]");

    public Boolean onFeedPage() {
        return driver.findElements(pageConfirmation).size() !=0;
    }
}
