package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class WelcomePage extends AbstractPage {

    private By welcomeNextButton = By.xpath("//*[contains(@class, 'slide-welcome')]/button[contains(@class, 'js-next')]");
    private By interest1 = By.xpath("//*[contains(@class, 'slide-add-interests')]//ul[contains(@class, 'summary-list')]/li[position()=1]//*[contains(@class, 'js-btn-love-it')]");
    private By interest2 = By.xpath("//*[contains(@class, 'slide-add-interests')]//ul[contains(@class, 'summary-list')]/li[position()=2]//*[contains(@class, 'js-btn-try-it')]");
    private By interest3 = By.xpath("//*[contains(@class, 'slide-add-interests')]//ul[contains(@class, 'summary-list')]/li[position()=3]//*[contains(@class, 'js-btn-love-it')]");
    private By interest4 = By.xpath("//*[contains(@class, 'slide-add-interests')]//ul[contains(@class, 'summary-list')]/li[position()=4]//*[contains(@class, 'js-btn-try-it')]");
    private By interest5 = By.xpath("//*[contains(@class, 'slide-add-interests')]//ul[contains(@class, 'summary-list')]/li[position()=5]//*[contains(@class, 'js-btn-try-it')]");
    private By interestNextButton = By.xpath("//*[contains(@class, 'slide-add-interests')]//button[contains(@class, 'js-next')]");
    private By welcomeCompleteButton = By.xpath("//*[contains(@class, 'slide-thankyou')]//button[contains(@class, 'js-complete')]");

    public WelcomePage (WebDriver driver, JavascriptExecutor jsexec){
        super(driver, jsexec);
    }

    public void beginOnBoarding(){
        driver.findElement(welcomeNextButton).click();
    }

    public void likeAndLoveInterests(){
        driver.findElement(interest1).click();
        driver.findElement(interest2).click();
        driver.findElement(interest3).click();
        driver.findElement(interest4).click();
        driver.findElement(interest5).click();

        driver.findElement(interestNextButton).click();
    }

    public InterestsActivitiesPage completeOnBoarding(){
        driver.findElement(welcomeCompleteButton).click();
        return new InterestsActivitiesPage(driver, jsexec);
    }


}
