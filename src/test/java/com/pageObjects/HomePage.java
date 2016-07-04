package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class HomePage extends AbstractPage {

    public HomePage(WebDriver driver, JavascriptExecutor jsexec) {
        super(driver, jsexec);
    }

    private By homepageMenu = By.xpath("//div[contains(@class, 'page-home')]");
    private By loginDialogButton = By.partialLinkText("Login");

    public Boolean checkSlidesPresent() {
         if (driver.findElements(homepageMenu).size() > 0) {
             return true;
         }
         else {
             return false;
         }
    }

    public LoginPage openLoginPage() throws InterruptedException {
        jsexec.executeScript("arguments[0].click();", driver.findElement(loginDialogButton));
        return new LoginPage(driver, jsexec);
    }



}
