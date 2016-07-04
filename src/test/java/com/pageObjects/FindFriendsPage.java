package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class FindFriendsPage extends AbstractPage {

    public FindFriendsPage(WebDriver driver, JavascriptExecutor jsexec){
        super(driver, jsexec);
    }

    private By userSearchInput = By.xpath("//*[contains(@class, 'js-find-friends-input')]");
    private By sendRequestButton = By.xpath("//*[contains(@class, 'js-send-request')]");

    public void searchForFriend(String user) throws InterruptedException {
        Thread.sleep(1500);
        driver.findElement(userSearchInput).sendKeys(user);
    }

    public ProfileTimelinePage openProfileTimelinePage(String user) {
        if(driver.findElements(By.linkText(user)).size() != 0){
            driver.findElement(By.linkText(user)).click();
            return new ProfileTimelinePage(driver, jsexec);
        }
        else
        {
            return null;
        }
    }

    public void sendFriendRequest() throws InterruptedException {
        driver.findElement(sendRequestButton).click();
    }
}
