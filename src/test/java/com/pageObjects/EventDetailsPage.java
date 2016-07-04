package com.pageObjects;

import com.utility.globalElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EventDetailsPage extends AbstractPage {

    public EventDetailsPage(WebDriver driver, JavascriptExecutor jsexec){
        super(driver, jsexec);
    }

    private By mediaItems = By.xpath("//a[contains(@class, 'js-media-item-link')]");
    private By eventCoverImage = By.xpath("//div[contains(@class, 'hero-large-image')]");
    private By mediaSectionButton = By.linkText("Media");
    private By mediaDialogButton = By.xpath("//button[contains(@class, 'js-select-upload-to-album')]");
    private By mediaDropDownButton = By.xpath("//button[contains(@class, 'js-popover-dropdown')]");
    private By deleteMediaButton = By.xpath("//button[contains(@class, 'js-delete-media')]");
    private By confirmDeleteButton = By.id("confirmBtn");
    private By eventDropdown = By.xpath("//button[contains(@class, 'js-event-dropdown')]");
    private By removeEventBtn = By.xpath("//button[contains(@class, 'js-btn-delete')]");
    private By myvVooshButton = By.cssSelector(".brand.brand-container");
    private By externalLink = By.xpath("//a[contains(@class, 'js-external')]");
    private By coverImageAvatar = By.xpath("//div[contains(@class, 'vv-feature')]");
    private By eventDateLabel = By.xpath("//h3[contains(@class, 'vv-media-object__body')]");
    private By eventLocationLabel = By.xpath("//div[contains(@class, 'event-details')]//div[2]//h3[contains(@class, 'vv-media-object__body')]");
    private By inviteeCountLabel = By.xpath("//strong[contains(@class, 'js-invited-count')]");


    public EventsPage openEventsPage() {
        RemoteWebElement element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(globalElement.eventsPageButton));
        jsexec.executeScript("arguments[0].click();", element);
        return new EventsPage(driver, jsexec);
    }

    public void openDropDownMenu(){
        jsexec.executeScript("arguments[0].click();", driver.findElement(eventDropdown));
    }

    public EventsPage removeEvent(){
        jsexec.executeScript("arguments[0].click();", driver.findElement(removeEventBtn));
        jsexec.executeScript("arguments[0].click();", driver.findElement(confirmDeleteButton));
        return new EventsPage(driver, jsexec);
    }

    public void openEventMediaSection(){
        driver.findElement(mediaSectionButton).click();
    }

    public UploadMediaPage openMediaDialog(){
        driver.findElement(mediaDialogButton).click();
        return new UploadMediaPage(driver, jsexec);
    }

    public Boolean verifyMediaUploadSuccess(String name){
        return name.equals(driver.findElement(globalElement.uploadedMedia).getAttribute("data-alt-text"));
    }

    public Boolean verifyNoMediaItemsPresent() {
        driver.navigate().refresh();
        return driver.findElements(mediaItems).size() == 0;
    }

    public void removeImageFromEvent() throws InterruptedException {
        driver.navigate().refresh();
        RemoteWebElement element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(mediaDropDownButton));
        jsexec.executeScript("arguments[0].click();", element);
        element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(deleteMediaButton));
        jsexec.executeScript("arguments[0].click();", element);
        element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(confirmDeleteButton));
        jsexec.executeScript("arguments[0].click();", element);
    }

    public Boolean checkEventTitle(String name) {
        return name.equals(driver.findElement(globalElement.eventHeroTitle).getText());
    }

    public Boolean checkLinkAppears() {
        return driver.findElements(externalLink).size() > 0;
    }

    public Boolean coverImageIsNotDefault(){
        String genericSrc = "public/assets/img/hero-external-event-mask.png";
        String currentSrc = driver.findElement(coverImageAvatar).getAttribute("style").split("\"|\'", 3)[1].split("/",4)[3];
        return !genericSrc.equals(currentSrc);
    }

    public Boolean checkInterestAppears(String interest) {
        return driver.findElements(By.linkText("#"+interest)).size() > 0;
    }

    public Boolean checkEventStartDateEquals(String datetime) {
        String Date1 = fixDatetime(datetime);
        String Date2 = driver.findElement(eventDateLabel).getText().replace(",","");
        return Date1.equalsIgnoreCase(Date2);
    }

    private String fixDatetime(String date) {
        String newDate = date.replace("\n", " ");

        if (newDate.contains("Jan")) {newDate=newDate.replace("Jan","January");}
        else if (newDate.contains("Feb")) {newDate=newDate.replace("Feb","February");}
        else if (newDate.contains("Mar")) {newDate=newDate.replace("Mar","March");}
        else if (newDate.contains("Apr")) {newDate=newDate.replace("Apr","April");}
        else if (newDate.contains("May")) {newDate=newDate.replace("May","May");}
        else if (newDate.contains("Jun")) {newDate=newDate.replace("Jun","June");}
        else if (newDate.contains("Jul")) {newDate=newDate.replace("Jul","July");}
        else if (newDate.contains("Aug")) {newDate=newDate.replace("Aug","August");}
        else if (newDate.contains("Sep")) {newDate=newDate.replace("Sep","September");}
        else if (newDate.contains("Oct")) {newDate=newDate.replace("Oct","October");}
        else if (newDate.contains("Nov")) {newDate=newDate.replace("Nov","November");}
        else if (newDate.contains("Dec")) {newDate=newDate.replace("Dec","December");}

        return newDate;
    }

    public Boolean checkEventIsAllDay() {
        return driver.findElement(eventDateLabel).getText().contains("All day");
    }

    public Boolean checkInviteeCountEquals(String expected) {
        return driver.findElement(inviteeCountLabel).getText().equals(expected);
    }

    public Boolean checkEventLocationEquals(String loc) {
        return driver.findElement(eventLocationLabel).getText().equals(loc);
    }
 }

