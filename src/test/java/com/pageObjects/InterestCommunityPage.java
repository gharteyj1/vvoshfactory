package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Created by user on 12/11/2015.
 */
public class InterestCommunityPage extends AbstractPage {

    public InterestCommunityPage(WebDriver driver, JavascriptExecutor jsexec){
        super(driver, jsexec);
    }

    public Boolean verifyPostAdded(String postName) {
        int count = driver.findElements(By.linkText(postName)).size();
        return count != 0;
    }
}
