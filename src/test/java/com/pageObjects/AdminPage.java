package com.pageObjects;

import com.utility.globalConstant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class AdminPage extends AbstractPage {

    public AdminPage(WebDriver driver, JavascriptExecutor jsexec) {
        super(driver, jsexec);
    }

    private By environmentPicker = By.xpath("//select[@id='envSelect']");
    private By inviteEmailInput = By.id("email");
    private By inviteSubmitBtn = By.id("submit");

    public void setEnvironmentPicker(){
        String envName = "";
        if ( "uat-vpc" == globalConstant.environ ) {
            envName = "UAT";
        }
        else if ( "test-vpc" == globalConstant.environ ) {
            envName = "Test";
        }
        else if ( "dev-vpc" == globalConstant.environ ) {
            envName = "Development";
        }
        Select mySelect= new Select(driver.findElement(environmentPicker));
        mySelect.selectByValue(envName);
    }

    public String generateRandomEmail(){
        String alphabet= "abcdefghijklmnopqrstuvwxyz";
        String s = "";
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            char c = alphabet.charAt(random.nextInt(26));
            s+=c;
        }

        return "automation" + s;
    }

    public String setInviteEmailInput(String email){
        driver.findElement(inviteEmailInput).sendKeys(email + "@mailinator.com");
        RemoteWebElement element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(inviteSubmitBtn));
        jsexec.executeScript("arguments[0].click();", element);
        return email;
    }

    public MailinatorPage submitInviteButton() throws InterruptedException {
        Thread.sleep(10000);
        return new MailinatorPage(driver, jsexec);
    }

}
