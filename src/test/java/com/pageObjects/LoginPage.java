package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Created by user on 22/04/2016.
 */
public class LoginPage extends AbstractPage{

    public LoginPage(WebDriver driver, JavascriptExecutor jsexec)
    {
        super(driver, jsexec);
    }

    private By loginEmailInput = By.id("loginEmail");
    private By loginPasswordInput = By.id("loginPassword");
    private By loginSubmitButton = By.xpath("//*[contains(@class, 'js-btn-login')]");
    private By loginFailedContainer = By.xpath("//div[contains(@class, 'js-error-top')]");
    private By facebookButton = By.xpath("//button[contains(@class, 'vv-btn--facebook')]");
    private By googleButton = By.xpath("//button[contains(@class, 'vv-btn--google')]");

    public void completeLoginForm(String email, String password) {
        driver.findElement(loginEmailInput).sendKeys(email);
        driver.findElement(loginPasswordInput).sendKeys(password);
    }

    public TimelinePage submitLogin() {
        driver.findElement(loginSubmitButton).click();
        return new TimelinePage(driver, jsexec);
    }

    public Boolean checkLoginFailed() {
        driver.findElement(loginSubmitButton).click();
        return driver.findElements(loginFailedContainer).size() !=0;
    }

    public FacebookLoginPage openFacebookModal() {
        driver.findElement(facebookButton).click();
        return new FacebookLoginPage(driver, jsexec);
    }

    public GoogleLoginPage openGoogleModal() {
        driver.findElement(googleButton).click();
        return new GoogleLoginPage(driver, jsexec);
    }

}
