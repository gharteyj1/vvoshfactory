package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class FriendsPage extends AbstractPage {

    private By findFriendsButton = By.linkText("Find Friends");
    private By friendRequestsButton = By.linkText("Requests");
    private By friendDropdown = By.xpath("//button[contains(@class, 'js-open-popover-controls')]");
    private By removeFriendButton = By.xpath("//button[contains(@class, 'js-remove-friend')]");
    private By confirmRemoveFriendButton = By.id("confirmBtn");

    public FriendsPage (WebDriver driver, JavascriptExecutor jsexec){
        super(driver, jsexec);
    }

    public Boolean checkUserInFriendsList(String user){
        return driver.findElements(By.linkText(user)).size() != 0;
    }

    public Boolean checkUserNotInFriendsList(String user){
        return driver.findElements(By.linkText(user)).size() == 0;
    }

    public FindFriendsPage openFindFriendsPage() {
        driver.findElement(findFriendsButton).click();
        return new FindFriendsPage(driver, jsexec);
    }

    public FriendRequestsPage openFriendRequestsPage() {
        driver.findElement(friendRequestsButton).click();
        return new FriendRequestsPage(driver, jsexec);
    }

    public ProfileTimelinePage openFriendProfileTimeline(String user) throws InterruptedException {
        if(driver.findElements(By.linkText(user)).size() != 0){
            driver.findElement(By.linkText(user)).click();
            return new ProfileTimelinePage(driver, jsexec);
        }
        else
        {
            return null;
        }
    }

    public void removeFriend() throws InterruptedException {
        driver.findElement(friendDropdown).click();
        Thread.sleep(1000);
        driver.findElement(removeFriendButton).click();
        driver.findElement(confirmRemoveFriendButton).click();
    }

}
