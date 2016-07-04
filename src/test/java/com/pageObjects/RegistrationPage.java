package com.pageObjects;

import com.utility.globalConstant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends AbstractPage {
    public RegistrationPage(WebDriver driver, JavascriptExecutor jsexec){
        super(driver, jsexec);
    }

    private By registerFirstnameInput = By.id("registerFirstName");
    private By registerSurnameInput = By.id("registerLastName");
    private By registerPasswordInput = By.id("registerPassword");
    private By registerGender = By.id("registerGender");
    private By registerMonth = By.id("registerGender");
    private By registerDay = By.id("registerGender");
    private By registerYear = By.id("registerGender");
    private By registerLocationInput = By.id("registerLocation");
    private By registerCaptcha = By.id("recaptcha-anchor");
    private By captchaiFrame = By.cssSelector("iframe[title='recaptcha widget']");
    private By registerTermsCheckbox = By.xpath("//span[contains(@class, 'vv-control-label__checkbox')]");
    private By registerSubmitButton = By.xpath("//*[contains(@class, 'js-btn-register')]");

    public void completeRegistrationForm(String firstName) throws InterruptedException {
        driver.findElement(registerFirstnameInput).sendKeys(firstName);
        driver.findElement(registerSurnameInput).sendKeys(globalConstant.registerSurname);
        driver.findElement(registerPasswordInput).sendKeys(globalConstant.registerPassword);
        driver.findElement(registerGender).sendKeys("m");
        driver.findElement(registerMonth).sendKeys("j");
        driver.findElement(registerDay).sendKeys("01");
        driver.findElement(registerYear).sendKeys("1980");
        driver.findElement(registerTermsCheckbox).click();
        driver.findElement(registerLocationInput).sendKeys(globalConstant.registerLocation);
        Thread.sleep(1500);
        driver.findElement(registerLocationInput).sendKeys(Keys.ENTER);
        driver.switchTo().frame(driver.findElement(captchaiFrame));
        driver.findElement(registerCaptcha).click();
        driver.switchTo().defaultContent();
    }

    public WelcomePage submitRegistration() throws InterruptedException {
        Thread.sleep(10000);
        driver.findElement(registerSubmitButton).click();
        return new WelcomePage(driver, jsexec);
    }
}
