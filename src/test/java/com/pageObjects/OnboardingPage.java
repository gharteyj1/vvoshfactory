package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by user on 23/06/2016.
 */
public class OnboardingPage extends AbstractPage {

    public OnboardingPage(WebDriver driver, JavascriptExecutor jsexec) {
        super(driver, jsexec);
    }

    private By nextButton = By.xpath("//button[contains(@class, 'js-btn-next')]");
    private By searchInterestInput = By.xpath("//input[contains(@class, 'js-search-input')]");
    private By clearInputButton = By.xpath("//button[contains(@class, 'js-clear-filter-field')]");
    private By loveButton = By.xpath("//button[contains(@class, 'js-btn-love-it')][1]");
    private By tryButton = By.xpath("//button[contains(@class, 'js-btn-try-it')][1]");

    private By addProfileImageButton = By.xpath("//div[contains(@class, 'js-profile-image')]");
    private By uploadProfileImageButton = By.xpath("//div[contains(@class, 'js-upload')]");
    private By addCoverImageButton = By.xpath("//div[contains(@class, 'js-popover-cover')]");

    private By firstUserInList = By.xpath("//ul[contains(@class, 'vv-row')]//li[1]//button[contains(@class, 'js-send-request')]");
    private By feedButton = By.linkText("Take me to my Feed");

    public void goToNextPage(){
        RemoteWebElement element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(nextButton));
        jsexec.executeScript("arguments[0].click();", element);
    }

    public void loveInterest(String name) throws Throwable {
        driver.findElement(searchInterestInput).sendKeys(name);
        Thread.sleep(2000);//Have to put in this sleep to account for the load time of the list
        addInterest(loveButton);
        driver.findElement(clearInputButton).click();
    }

    public void tryInterest(String name) throws Throwable {
        driver.findElement(searchInterestInput).sendKeys(name);
        Thread.sleep(2000);//Have to put in this sleep to account for the load time of the list
        addInterest(tryButton);
        driver.findElement(clearInputButton).click();
    }

    private void addInterest(By elementType) throws Throwable  {
        RemoteWebElement element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(elementType));
        if (!element.getAttribute("class").contains("selected")){
            jsexec.executeScript("arguments[0].click();", element);
        }
    }

    public void addFirstUserAsFriend(){
        driver.findElement(firstUserInList).click();
    }

    public UploadMediaPage openProfileImageMediaDialog(){
        RemoteWebElement element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(addProfileImageButton));
        jsexec.executeScript("arguments[0].click();", element);
        jsexec.executeScript("arguments[0].click();", driver.findElement(uploadProfileImageButton));
        return new UploadMediaPage(driver, jsexec);
    }

    public UploadMediaPage openCoverImageMediaDialog(){
        RemoteWebElement element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(addCoverImageButton));
        jsexec.executeScript("arguments[0].click();", element);
        jsexec.executeScript("arguments[0].click();", driver.findElement(uploadProfileImageButton));
        return new UploadMediaPage(driver, jsexec);
    }

    public FeedPage openFeedPage(){
        RemoteWebElement element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(feedButton));
        jsexec.executeScript("arguments[0].click();", element);
        return new FeedPage(driver, jsexec);
    }


}
