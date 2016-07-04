package com.pageObjects;

import com.utility.globalElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by user on 07/11/2015.
 */
public class EventsPage extends AbstractPage {

    public EventsPage(WebDriver driver, JavascriptExecutor jsexec){
        super(driver, jsexec);
    }

    private By createEventButton = By.xpath("//button[contains(@class, 'js-btn-add')]");
    private By myEventsButton = By.xpath("//a[contains(@data-on-shown, 'onMyEventsShow')]");
    private By myEventPanel = By.id("my-events");

    public EventCreatePage openCreateEventDialog(){
        driver.findElement(createEventButton).click();
        return new EventCreatePage(driver, jsexec);
    }

    public EventCreatePage openCreateEventPage(){
        driver.findElement(createEventButton).click();
        return new EventCreatePage(driver, jsexec);
    }

    public MyEventsPage openMyEventsPage(){
        jsexec.executeScript("arguments[0].click();", driver.findElement(globalElement.eventsPageButton));
        RemoteWebElement element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(myEventsButton));
        jsexec.executeScript("arguments[0].click();", element);
        return new MyEventsPage(driver, jsexec);
    }



}
