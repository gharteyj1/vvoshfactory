package com.pageObjects;

import com.utility.globalElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Created by user on 07/10/2015.
 */
public class FriendRequestsPage extends AbstractPage {

    private By acceptRequestButton = By.xpath("//button[contains(@class, 'js-accept-request')]");
    private By ignoreRequestButton = By.xpath("//button[contains(@class, 'js-ignore-request')]");

    public FriendRequestsPage (WebDriver driver, JavascriptExecutor jsexec){
        super(driver, jsexec);
    }

    public void acceptFriendRequest(){
        driver.findElement(acceptRequestButton).click();
    }

    public void ignoreFriendRequest(){
        driver.findElement(ignoreRequestButton).click();
    }

    public FriendsPage openFriendsPage() {
        driver.findElement(globalElement.friendsPageButton).click();
        return new FriendsPage(driver, jsexec);
    }





}
