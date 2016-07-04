package com.pageObjects;

import com.utility.globalConstant;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class AbstractPage {

    protected WebDriver driver;
    protected JavascriptExecutor jsexec;

    public AbstractPage(WebDriver driver, JavascriptExecutor jsexec){
        this.driver = driver;
        this.jsexec = (JavascriptExecutor) driver;
    }

    public HomePage navigateToHome(){
        driver.get(globalConstant.URL);
        return new HomePage(driver, jsexec);
    }

    public AdminPage navigateToAdmin(){
        driver.get(globalConstant.adminURL);
        return new AdminPage(driver, jsexec);
    }

    public MailinatorPage navigateToMailinator(String inviteEmail){
        driver.get(globalConstant.emailURL + inviteEmail);
        return new MailinatorPage(driver, jsexec);
    }

    public VerificationPage navigateToVerification(){
        driver.get(globalConstant.URL + globalConstant.registrationCode);
        return new VerificationPage(driver, jsexec);
    }

    public void closeBrowser(){
        driver.close();
        driver.quit();
    }

}
