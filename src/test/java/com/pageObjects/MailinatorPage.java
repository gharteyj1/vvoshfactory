package com.pageObjects;

import com.utility.globalConstant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class MailinatorPage extends AbstractPage {

    public MailinatorPage (WebDriver driver, JavascriptExecutor jsexec) {
        super(driver, jsexec);
    }

    private By emailLink = By.xpath("//div[contains(@class, 'col-xs-11')]");
    private By confirmEmailLink = By.linkText("Take me to vVoosh!");

    public void openEmail(){
        driver.findElement(emailLink).click();
    }

    public RegistrationPage beginRegistration(){
        driver.switchTo().frame("publicshowmaildivcontent");
        String link = driver.findElement(confirmEmailLink).getAttribute("href");
        //driver.get(globalConstant.URL);
        driver.get(link);
        return new RegistrationPage(driver, jsexec);
    }

    public TimelinePage confirmEmailAddress(){
        driver.switchTo().frame("publicshowmaildivcontent");
        String link = driver.findElement(confirmEmailLink).getAttribute("href");
        driver.get(globalConstant.URL);
        driver.get(link);
        return new TimelinePage(driver, jsexec);
    }


}
