package com.pageObjects;

import com.utility.globalElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by user on 11/05/2016.
 */
public class SettingsPage extends AbstractPage {

    public SettingsPage(WebDriver driver, JavascriptExecutor jsexec){
        super(driver, jsexec);
    }

    private By removeProfileImageButton = By.xpath("//button[contains(@class, 'js-btn-remove-profile-img')]");
    private By removeCoverImageButton = By.xpath("//button[contains(@class, 'js-btn-remove-cover-img')]");
    private By selectProfileImageButton = By.xpath("//button[contains(@class, 'js-upload-profile-image')]");
    private By selectCoverImageButton = By.xpath("//button[contains(@class, 'js-upload-cover-image')]");
    private By saveSettingsButton = By.xpath("id('js-profile')//button[contains(@class, 'js-btn-update-details')]");
    private By profileImage = By.id("profile-image");

    public UploadMediaPage openProfileImageMediaDialog(){
        jsexec.executeScript("arguments[0].click();", driver.findElement(selectProfileImageButton));
        return new UploadMediaPage(driver, jsexec);
    }

    public UploadMediaPage openCoverImageMediaDialog(){
        jsexec.executeScript("arguments[0].click();", driver.findElement(selectCoverImageButton));
        return new UploadMediaPage(driver, jsexec);
    }

    public void removeProfileImage(){
        jsexec.executeScript("arguments[0].click();", driver.findElement(removeProfileImageButton));
    }

    public void removeCoverImage(){
        driver.findElement(removeCoverImageButton).click();
    }

    public void saveSettings() throws InterruptedException{ 
        RemoteWebElement element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(saveSettingsButton));
        jsexec.executeScript("arguments[0].click();", driver.findElement(saveSettingsButton));
    }

    public TimelinePage openTimelinePage() {
        jsexec.executeScript("arguments[0].click();", driver.findElement(globalElement.timelinePageButton));
        return new TimelinePage(driver, jsexec);
    }

}
