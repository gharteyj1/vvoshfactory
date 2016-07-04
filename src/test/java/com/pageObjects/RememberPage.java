package com.pageObjects;

import com.utility.globalElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by user on 07/12/2015.
 */
public class RememberPage extends AbstractPage {

    public RememberPage(WebDriver driver, JavascriptExecutor jsexec){
        super(driver, jsexec);
    }

    private By mediaDialogButton = By.xpath("//button[contains(@class, 'js-upload-to-all-media')]");

    private By mediaDropdown = By.xpath("//button[contains(@class, 'js-popover-dropdown')]");
    private By editMediaButton = By.xpath("//button[contains(@class, 'js-edit-media')]");
    //By mediaTitleInput = By.xpath("//input[contains(@class, 'js-title')]");

    private By mediaTitleInput = By.id("title");


    public UploadMediaPage openMediaDialog(){
        driver.findElement(mediaDialogButton).click();
        return new UploadMediaPage(driver, jsexec);
    }

    public Boolean verifyMediaUploadSuccess(String name){
        RemoteWebElement element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(globalElement.uploadedMedia));
        return name.equals(element.getAttribute("data-alt-text"));
    }

    public void openEditImageDialog() {
        jsexec.executeScript("arguments[0].click();", driver.findElement(mediaDropdown));
        jsexec.executeScript("arguments[0].click();", driver.findElement(editMediaButton));
    }

    public Boolean verifyImageIsCorrect(String name){
        return name.equals(driver.findElement(mediaTitleInput).getAttribute("value"));
    }

}
