package com.pageObjects;

import com.utility.globalConstant;
import com.utility.globalElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class TimelinePage extends AbstractPage {

    public TimelinePage(WebDriver driver, JavascriptExecutor jsexec) {
        super(driver, jsexec);
    }

    private By pageConfirmation = By.xpath("//div[contains(@id, 'timeline')]");

    public Boolean onPage() {
        return driver.findElements(pageConfirmation).size() !=0;
    }

    public Boolean verifyPostAdded(String postName) {
        int count = driver.findElements(By.linkText(postName)).size();
        return count != 0;
    }

    public void openBurgerMenu(){
        jsexec.executeScript("arguments[0].click();", driver.findElement(globalElement.burgerMenuButton));
    }

    public HomePage logOut(){
        driver.findElement(globalElement.logoutButton).click();
        return new HomePage(driver, jsexec);
    }


    public ProfileTimelinePage openProfilePage() throws InterruptedException{
        // Just waiting for the element to be clickable doesn't seem enough so we're forcing a longer wait to ensure it can be found
        RemoteWebElement element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(globalElement.profilePageButton));
        Thread.sleep(1000);
        jsexec.executeScript("arguments[0].click();", element);
        return new ProfileTimelinePage(driver, jsexec);
    }

    public FriendsPage openFriendsPage() {
        driver.findElement(globalElement.friendsPageButton).click();
        return new FriendsPage(driver, jsexec);
    }

    public EventsPage openEventsPage() {
        jsexec.executeScript("arguments[0].click();", driver.findElement(globalElement.eventsPageButton));
        return new EventsPage(driver, jsexec);
    }

    public RememberPage openRememberPage() {
        RemoteWebElement element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(globalElement.rememberPageButton));
        jsexec.executeScript("arguments[0].click();", element);
        return new RememberPage(driver, jsexec);
    }

    public PostCreateEditPage openCreatePostDialog() {
        driver.findElement(globalElement.createPostButton).click();
        return new PostCreateEditPage(driver, jsexec);
    }

    public PostDetailsPage openPostDetailsDialog(String name) {
        driver.findElement(By.linkText(name)).click();
        return new PostDetailsPage(driver, jsexec);
    }

    public SettingsPage openSettingsPage(){
        ArrayList<RemoteWebElement> webElements = (ArrayList<RemoteWebElement>) jsexec.executeScript("return document.getElementsByTagName('a')");
        if(webElements != null && !webElements.isEmpty()) {
                for (RemoteWebElement webElement: webElements) {
                    if(webElement != null) {
                        if (webElement.getAttribute("href").toLowerCase().indexOf("/myvvoosh/settings/profile") != -1 ) {
                           jsexec.executeScript("arguments[0].click();", webElement);
                           break;
                        }
                    }
                }
        }
        return new SettingsPage(driver, jsexec);
    }

    public InterestOverviewPage searchForInterest(String query) throws InterruptedException {
        driver.findElement(globalElement.searchButton).click();
        driver.findElement(globalElement.searchInput).sendKeys(query);
        Thread.sleep(1000);
        driver.findElement(globalElement.searchInput).sendKeys(Keys.ENTER);
        return new InterestOverviewPage(driver, jsexec);
    }


    public InterestOverviewPage openInterestOverviewbyLink() throws InterruptedException {
        driver.get(globalConstant.footballInterestPage);
        return new InterestOverviewPage(driver, jsexec);
    }

    public void openNotificationsMenu(){
        driver.findElement(globalElement.notificationsButton).click();
    }

    public Boolean checkNotificationExists(String query){
        return driver.findElements(By.xpath("//*[contains(text(), '"+query+"')]")).size() !=0;
    }

    public void notificationAcceptFriendRequest(){
        driver.findElement(globalElement.acceptFriendRequestButton).click();
    }

    public void notificationIgnoreFriendRequest(){
        driver.findElement(globalElement.ignoreFriendRequestButton).click();
    }

    public Boolean checkFriendRequestReceived(String name){
        String text = driver.findElement(globalElement.notificationFriendRequestText).getText();
        return text.equals("from "+name);
    }

    public OnboardingPage openOnboardingPage(){
        driver.get(globalConstant.onboardingURL);
        return new OnboardingPage(driver, jsexec);
    }

}
