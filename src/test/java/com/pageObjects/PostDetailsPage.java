package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by user on 12/11/2015.
 */
public class PostDetailsPage extends AbstractPage {

    public PostDetailsPage(WebDriver driver, JavascriptExecutor jsexec){
        super(driver, jsexec);
    }

    private By closePostModalButton = By.xpath("//*[contains(@class, 'js-modal-close')]");
    private By postTitleText = By.xpath("//div[contains(@class, 'modal-dialog')]//h1[contains(@class, 'content-title')]");
    private By postTextContent = By.xpath("//*[contains(@class, 'modal-post') and contains(@aria-hidden, 'false')]//div[contains(@class, 'post-text-container')]");
    private By postLinkContent = By.xpath("//*[contains(@class, 'modal-post') and contains(@aria-hidden, 'false')]//div[contains(@class, 'js-preview-container')]");
    private By postMediaContent = By.xpath("//*[contains(@class, 'modal-post') and contains(@aria-hidden, 'false')]//div[contains(@class, 'post-media-section')]");
    private By postLocationContent = By.xpath("//*[contains(@class, 'modal-post') and contains(@aria-hidden, 'false')]//div[contains(@class, 'js-map-container')]");

    private By postDetailsDropDown = By.xpath("//button[contains(@class, 'js-open-popover-controls')]");
    private By postEditButton = By.xpath("//div[contains(@class, 'popover-vvoosh')]//button[contains(@class, 'js-edit-post')]");

    private By postCommentInput = By.xpath("//textarea[contains(@class, 'js-post-comment-text')]");
    private By postCommentButton = By.xpath("//button[contains(@class, 'js-post-comment-btn')]");

    public TimelinePage closePostDialog() {
        RemoteWebElement element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(closePostModalButton));
        jsexec.executeScript("arguments[0].click();", element);
        return new TimelinePage(driver, jsexec);
    }

    public Boolean verifyPostIsCorrect(String name) {
        return name.equals(driver.findElement(postTitleText).getText());
    }

    public Boolean verifyPostContent(String type, Boolean checkContentExists) {
        int count=0;
        if (type.equals("Text")){
            count = driver.findElements(postTextContent).size();
        }
        if (type.equals("Location")){
            count = driver.findElements(postLocationContent).size();
        }
        if (type.equals("Link")){
            count = driver.findElements(postLinkContent).size();
        }
        if (type.equals("Media")){
            count = driver.findElements(postMediaContent).size();
        }
        if (checkContentExists) {
            return count>=1;
        }
        else {
            return count==0;
        }
    }

    public void openPostDetailsDropDown(){
        RemoteWebElement element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(postDetailsDropDown));
        jsexec.executeScript("arguments[0].click();", element);
    }

    public PostCreateEditPage editPost(){
        driver.findElement(postEditButton).click();
        return new PostCreateEditPage(driver, jsexec);
    }

    public void addCommentToPost(String comText) throws Throwable {
        driver.findElement(postCommentInput).sendKeys(comText);
        Thread.sleep(500);
        driver.findElement(postCommentButton).click();
    }
}
