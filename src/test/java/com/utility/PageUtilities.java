package com.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by user on 23/06/2016.
 */
public class PageUtilities {

    public static Boolean verifyPostAdded(String postName, WebDriver driver) {
        int count = driver.findElements(By.linkText(postName)).size();
        return count != 0;
    }

    public static Boolean verifyPostNotAdded(String postName, WebDriver driver) {
        int count = driver.findElements(By.linkText(postName)).size();
        return count == 0;
    }
}
