package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.Random;

/**
 * Created by user on 22/04/2016.
 */
public class VerificationPage extends AbstractPage {

    public VerificationPage(WebDriver driver, JavascriptExecutor jsexec){
        super(driver, jsexec);
    }

    private By inviteEmailInput = By.id("verifyEmail");
    private By submitEmailButton = By.xpath("//button[contains(@class, 'js-btn-verify')]");

    public String setInviteEmailInput(String email){
        driver.findElement(inviteEmailInput).sendKeys(email + "@mailinator.com");
        return email;
    }

    public String generateRandomEmail(){
        String alphabet= "abcdefghijklmnopqrstuvwxyz";
        String s = "";
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            char c = alphabet.charAt(random.nextInt(26));
            s+=c;
        }

        return "vvoosh" + s;
    }

    public MailinatorPage submitInviteButton() throws InterruptedException {
        driver.findElement(submitEmailButton).click();
        Thread.sleep(2500);
        return new MailinatorPage(driver, jsexec);
    }
}
