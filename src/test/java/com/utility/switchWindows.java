package com.utility;

import org.openqa.selenium.WebDriver;

/**
 * Created by user on 22/06/2016.
 */
public class switchWindows {

    public String getSocialWindow(WebDriver driver) {
        String parentHandle = driver.getWindowHandle();

        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        return parentHandle;
    }

    public void getParentWindow(WebDriver driver, String parentHandle) {
        driver.switchTo().window(parentHandle);
    }

}
