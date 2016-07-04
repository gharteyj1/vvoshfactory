package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class InterestsActivitiesPage extends AbstractPage {

    private By pageHeader = By.xpath("//*[contains(@class, 'js-category-name')]");

    public InterestsActivitiesPage(WebDriver driver, JavascriptExecutor jsexec) {
        super(driver, jsexec);
    }

    public String checkPageHeader(){
        return driver.findElement(pageHeader).getText();
    }
}
