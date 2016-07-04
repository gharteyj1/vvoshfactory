package com.pageObjects;

import com.utility.globalElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by OLEMI on 06/05/2016.
 */

public class MyEventsPage extends AbstractPage {

    public MyEventsPage(WebDriver driver, JavascriptExecutor jsexec) {
        super(driver, jsexec);
    }

    private By myEventDropdown = By.xpath("//button[contains(@class, 'js-event-dropdown')]");
    private By removeMyEvent = By.xpath("//button[contains(@class, 'js-btn-delete')]");
    private By confirmDeleteMediaButton = By.id("confirmBtn");

    public void openEventDropDown(WebElement event){
        jsexec.executeScript("arguments[0].click();", event.findElement(myEventDropdown));
    }

    public EventDetailsPage openEvent(WebElement event){
        jsexec.executeScript("arguments[0].click();", event);
        return new EventDetailsPage(driver, jsexec);
    }

    public MyEventsPage removeEvent(){
        jsexec.executeScript("arguments[0].click();", driver.findElement(removeMyEvent));
        jsexec.executeScript("arguments[0].click();", driver.findElement(confirmDeleteMediaButton));
        return new MyEventsPage(driver, jsexec);
    }

    public TimelinePage openTimelinePage(){
        jsexec.executeScript("arguments[0].click();", driver.findElement(globalElement.timelinePageButton));
        return new TimelinePage(driver, jsexec);
    }

}
