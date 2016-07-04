package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Created by user on 08/02/2016.
 */
public class ProfilePostsPage extends AbstractPage {
    public ProfilePostsPage (WebDriver driver, JavascriptExecutor jsexec){
        super(driver, jsexec);
    }

    public PostDetailsPage openPostDetailsDialog(String name) {
        driver.findElement(By.linkText(name)).click();
        return new PostDetailsPage(driver, jsexec);
    }


}
