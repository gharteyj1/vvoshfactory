package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDateTime;

/**
 * Created by user on 12/11/2015.
 */
public class PostCreateEditPage extends AbstractPage {

    public PostCreateEditPage(WebDriver driver, JavascriptExecutor jsexec){
        super(driver, jsexec);
    }

    private By postTitleInput = By.xpath("//*[contains(@class, 'post-title')]");
    private By postPrivacyDpDn = By.xpath("//div[contains(@class, 'js-post-privacy')]");
    private By privacyPublicOption = By.xpath("//div[contains(@class, 'js-post-privacy')]//ul/li[1]");
    private By privacyFriendsOption = By.xpath("//div[contains(@class, 'js-post-privacy')]//ul/li[2]");
    private By privacyPrivateOption = By.xpath("//div[contains(@class, 'js-post-privacy')]//ul/li[3]");

    private By postAddTextButton = By.xpath("//*[contains(@class, 'js-add-text-section')]");
    private By postTextInputIframe = By.xpath("//*[contains(@class, 'wysihtml5-sandbox')]");
    private By postTextInput = By.xpath("//*[contains(@class, 'post-text')]");
    private By savePostButton = By.xpath("//*[contains(@class, 'js-save-post')]");

    private By postAddContentButton = By.xpath("//*[contains(@class, 'js-post-controls-show')]");
    private By postAddMediaButton = By.xpath("//*[contains(@class, 'js-upload-image-link')]");
    private By postSelectMediaButton = By.xpath("//*[contains(@class, 'js-select-media-from-all-media')]");
    private By postFirstMediaSelector = By.cssSelector("img.js-media-image.gallery-image");
    private By postConfirmMediaButton = By.xpath("//*[contains(@class, 'js-confirm-upload')]");

    private By openPostModalButton = By.xpath("//a[contains(@class, 'js-link-post')][1]");
    private By postOptionsCaret = By.xpath("//*[contains(@class, 'js-open-popover-controls')]");
    private By editPostButton = By.xpath("//*[contains(@class, 'js-edit-post')]");

    private By postCommentInitiateButton = By.xpath("//*[contains(@class, 'comment-entry')]");
    private By postCommentInput = By.xpath("//*[contains(@class, 'js-post-comment-text')]");
    private By postSaveCommentButton = By.xpath("//*[contains(@class, 'js-post-comment-btn')]");
    private By postAddLocationButton = By.xpath("//*[contains(@class, 'js-add-place-section')]");
    private By postAddLinkButton = By.xpath("//*[contains(@class, 'js-add-link-section')]");
    private By postLinkInput = By.xpath("//*[contains(@class, 'js-url')]");
    private By postInterestInput = By.xpath("//input[contains(@class, 'js-search-activity-input')]");

    public String postAddTitle() {
        String postName = "Post: "+ LocalDateTime.now();
        driver.findElement(postTitleInput).sendKeys(postName);
        return postName;
    }

    public void postSetPrivacy(String privacy) throws InterruptedException {
        driver.findElement(postPrivacyDpDn).click();

        if (privacy.equals(driver.findElement(privacyPublicOption).getText())){
            driver.findElement(privacyPublicOption).click();
        }

        if (privacy.equals(driver.findElement(privacyFriendsOption).getText())){
            driver.findElement(privacyFriendsOption).click();
        }

        if (privacy.equals(driver.findElement(privacyPrivateOption).getText())){
            driver.findElement(privacyPrivateOption).click();
        }
    }

    public void postAddText(String text) {
        driver.findElement(postAddTextButton).click();
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(postTextInputIframe));
        actions.click();
        actions.sendKeys(text);
        actions.build().perform();
    }

    public void postAddImage() {
        RemoteWebElement element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(postAddMediaButton));
        jsexec.executeScript("arguments[0].click();", element);

        element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(postSelectMediaButton));
        jsexec.executeScript("arguments[0].click();", element);

        element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(postFirstMediaSelector));
        jsexec.executeScript("arguments[0].click();", element);

        element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(postConfirmMediaButton));
        jsexec.executeScript("arguments[0].click();", element);
    }

    public UploadMediaPage postAddImageViaUpload() {
        driver.findElement(postAddMediaButton).click();
        return new UploadMediaPage(driver, jsexec);
    }

    public PostDetailsPage savePost() {
        RemoteWebElement element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(savePostButton));
        jsexec.executeScript("arguments[0].click();", element);
        return new PostDetailsPage(driver, jsexec);
    }

    public void openPostDialog() {
        driver.findElement(openPostModalButton).click();
    }

    public void openEditPostDialog() {
        driver.findElement(postOptionsCaret).click();
        driver.findElement(editPostButton).click();
    }

    public void addContentToPostOption() throws InterruptedException{
        Thread.sleep(500);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(postAddContentButton)).perform();
        jsexec.executeScript("arguments[0].click();", driver.findElement(postAddContentButton));
    }

    public void postAddComment(String comment) throws InterruptedException {
        driver.findElement(postCommentInitiateButton).click();
        Thread.sleep(500);
        driver.findElement(postCommentInput).sendKeys(comment);
        driver.findElement(postSaveCommentButton).click();
    }

    public void postAddLocation() {
        driver.findElement(postAddLocationButton).click();
    }

    public void postAddURL(String url) {
        driver.findElement(postAddLinkButton).click();
        driver.findElement(postLinkInput).sendKeys(url);
    }

    public void postAddInterest(String name) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(postInterestInput));
        driver.findElement(postInterestInput).sendKeys(name);
        Thread.sleep(1000);
        driver.findElement(postInterestInput).sendKeys(Keys.ENTER);
    }

    public Boolean removeContentItem(String type) {
        RemoteWebElement element;

        if (type.equals("Text")){
            By content = By.xpath("//*[contains(@class, 'modal-create-post') and contains(@aria-hidden, 'false')]//*[contains(@class, 'post-text-container')]//..//..//*[contains(@class, 'js-link-section-delete')]");
            element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(content));
            jsexec.executeScript("arguments[0].click();", element);
        }
        else if (type.equals("Location")){
            By content = By.xpath("//*[contains(@class, 'modal-create-post') and contains(@aria-hidden, 'false')]//*[contains(@class, 'place-container')]//..//..//*[contains(@class, 'js-link-section-delete')]");
            element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(content));
            jsexec.executeScript("arguments[0].click();", element);
        }
        else if (type.equals("Link")){
            By content = By.xpath("//*[contains(@class, 'modal-create-post') and contains(@aria-hidden, 'false')]//*[contains(@class, 'link-preview-search-container')]//..//..//*[contains(@class, 'js-link-section-delete')]");
            element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(content));
            jsexec.executeScript("arguments[0].click();", element);
        }
        else if (type.equals("Media")){
            By content = By.xpath("//*[contains(@class, 'modal-create-post') and contains(@aria-hidden, 'false')]//*[contains(@class, 'post-media-section')]//..//..//*[contains(@class, 'js-link-section-delete')]");
            element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(content));
            jsexec.executeScript("arguments[0].click();", element);
        }
        else {
            By content = By.xpath("//*[contains(@class, 'modal-create-post') and contains(@aria-hidden, 'false')]//button[contains(@data-dismiss, 'modal') and contains(@class, 'vv-btn') and contains(@class, 'vv-btn--default')]");
            element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(content));
            jsexec.executeScript("arguments[0].click();", element);
            return false;
        }

        //element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(savePostButton));
        //jsexec.executeScript("arguments[0].click();", element);
        return true;

    }


}
