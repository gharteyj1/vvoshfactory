package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Created by user on 12/11/2015.
 */
public class InterestOverviewPage extends AbstractPage{

    public InterestOverviewPage(WebDriver driver, JavascriptExecutor jsexec){
        super(driver, jsexec);
    }

    private By InterestCommunityPageButton = By.xpath("//a[contains(@data-title, 'Community')]");

    public InterestCommunityPage openInterestCommunityPage(){
        driver.findElement(InterestCommunityPageButton).click();
        return new InterestCommunityPage(driver, jsexec);
    }
}
