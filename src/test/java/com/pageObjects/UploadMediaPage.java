package com.pageObjects;

import com.utility.globalConstant;
import com.utility.globalElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UploadMediaPage extends AbstractPage {

    public UploadMediaPage(WebDriver driver, JavascriptExecutor jsexec) {
        super(driver, jsexec);
    }

    private By saveButton = By.xpath("//button[contains(@class, 'js-confirm-upload')]");
    private By saveCropSettingsButton = By.xpath("//button[contains(@class, 'js-save-crop')]");
    private By errorAlert = By.xpath("//p[contains(@class, 'media-error')]");
    private By mediaLoadingIndicator = By.xpath("//div[contains(@class, 'loader')]");

    public void uploadGenericImage() {
       driver.findElement(globalElement.chooseMediaButton).sendKeys(globalConstant.imageFolder+globalConstant.basicTestImageName);
    }

    public void uploadDefinedImage(String filepath) {
        driver.findElement(globalElement.chooseMediaButton).sendKeys(filepath);
    }

    public EventCreatePage cropEventMedia(){
        RemoteWebElement element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(saveCropSettingsButton));
        //driver.findElement(saveCropSettingsButton).click();
        return new EventCreatePage(driver, jsexec);
    }

    public SettingsPage cropSettingsMedia() throws InterruptedException {
        // Element is never clickable because of another layer and we need to wait longer than just visible, so the sleep is just a hack for that
        RemoteWebElement element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(saveCropSettingsButton));
        Thread.sleep(1000);
        jsexec.executeScript("arguments[0].click();", element);
        return new SettingsPage(driver, jsexec);
    }

    public OnboardingPage cropOnboardingMedia() throws InterruptedException {
        // Element is never clickable because of another layer and we need to wait longer than just visible, so the sleep is just a hack for that
        RemoteWebElement element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(saveCropSettingsButton));
        Thread.sleep(1000);
        jsexec.executeScript("arguments[0].click();", element);
        return new OnboardingPage(driver, jsexec);
    }

    public void saveMedia() {
        RemoteWebElement element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(saveButton));
        jsexec.executeScript("arguments[0].click();", element);
    }

    public Boolean verifyErrorAlert(){
        // if the element is not found an exception is thrown, otherwise true is returned
        RemoteWebElement element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(errorAlert));
        return true;
    }

}
