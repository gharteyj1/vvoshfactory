package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Created by user on 21/06/2016.
 */
public class FacebookLoginPage extends AbstractPage {

    public FacebookLoginPage(WebDriver driver, JavascriptExecutor jsexec)
    {
        super(driver, jsexec);
    }

    private By loginEmailInput = By.id("email");
    private By loginPasswordInput = By.id("pass");
    private By loginSubmitButton = By.id("loginbutton");

    public void completeLoginForm(String email, String password) {

        driver.findElement(loginEmailInput).sendKeys(email);
        driver.findElement(loginPasswordInput).sendKeys(password);
    }

    public TimelinePage submitLogin() {
        driver.findElement(loginSubmitButton).click();
        return new TimelinePage(driver, jsexec);
    }
}
