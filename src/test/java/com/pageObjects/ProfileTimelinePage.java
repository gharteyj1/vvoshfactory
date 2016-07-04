package com.pageObjects;

import com.utility.globalElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfileTimelinePage extends AbstractPage {

    public ProfileTimelinePage(WebDriver driver, JavascriptExecutor jsexec){
        super(driver, jsexec);
    }

    private By vVooshTimelineButton = By.linkText("My vVoosh");
    private By profileEventsButton = By.xpath("//div[contains(@class, 'js-events-nav-item')]");


    private By profilePostsLink = By.xpath("//a[contains(@data-target, 'posts')]");
    private By postCommentInput = By.xpath("//textarea[contains(@class, 'js-entry-comment')]");
    private By postCommentButton = By.xpath("//button[contains(text(), 'Comment')]");

    private By profileImage = By.xpath("//a[contains(@class, 'fluid-hero-user-avatar')]");
    private By profileImageAvatar = By.xpath("//a[contains(@class, 'fluid-hero-user-avatar')]/img[1]");
    private By coverImageAvatar = By.xpath("//div[contains(@class, 'fluid-hero-container')]");
    //private By profileUsername = By.xpath("//a[contains(@class, 'fluid-hero-container')]");


    public Boolean profileImageIsNotDefault(){
        String genericSrc = "public/assets/img/placeholder/default-male-avatar.png";
        String currentSrc = driver.findElement(profileImageAvatar).getAttribute("src").split("/", 4)[3];
        return !genericSrc.equals(currentSrc);
    }

    public Boolean coverImageIsNotDefault(){
        String genericSrc = "public/assets/img/placeholder/profile-cover-lg.jpg";
        String currentSrc = driver.findElement(coverImageAvatar).getAttribute("style").split("\"|\'", 3)[1].split("/", 4)[3];
        return !genericSrc.equals(currentSrc);
    }

    public TimelinePage openTimelinePage(){
        driver.findElement(vVooshTimelineButton).click();
        return new TimelinePage(driver, jsexec);
    }

    public ProfileEventsPage openProfileEventsPage() throws Throwable {
        //For some reason waits don't seem effective here. So I've had to put in this dirty sleep.
        Thread.sleep(1000);
        driver.findElement(profileEventsButton).click();
        return new ProfileEventsPage(driver, jsexec);
    }

    public FriendsPage openFriendsPage() {
        driver.findElement(globalElement.friendsPageButton).click();
        return new FriendsPage(driver, jsexec);
    }

    public Boolean verifyPostAdded(String postName) {
        int count = driver.findElements(By.linkText(postName)).size();
        return count != 0;
    }

    public ProfilePostsPage openProfilePostsPage(){
        driver.findElement(profilePostsLink).click();
        return new ProfilePostsPage(driver, jsexec);
    }

    public void addCommentToPost(String postName, String com) {
        WebElement post = driver.findElement(By.linkText(postName));
        post.findElement(postCommentInput).sendKeys(com);
        post.findElement(postCommentButton).click();
    }

    public Boolean checkUsernameEquals(String username) {
        return driver.findElement(By.linkText(username)).getText().equals(username);
    }
}
