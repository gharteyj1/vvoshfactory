package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Created by user on 21/06/2016.
 */
public class GoogleLoginPage extends AbstractPage {

    public GoogleLoginPage(WebDriver driver, JavascriptExecutor jsexec)
    {
        super(driver, jsexec);
    }

    private By loginEmailInput = By.id("Email");
    private By loginPasswordInput = By.id("Passwd");
    private By nextButton = By.id("next");
    private By loginSubmitButton = By.id("signIn");

    public void completeLoginForm(String email, String password) {
        driver.findElement(loginEmailInput).sendKeys(email);
        driver.findElement(nextButton).click();
        driver.findElement(loginPasswordInput).sendKeys(password);
    }

    public TimelinePage submitLogin() {
        driver.findElement(loginSubmitButton).click();
        return new TimelinePage(driver, jsexec);
    }
}
