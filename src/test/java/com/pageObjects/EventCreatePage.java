package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class EventCreatePage extends AbstractPage {

    public EventCreatePage(WebDriver driver, JavascriptExecutor jsexec){
        super(driver, jsexec);
    }

    private By eventNameInput = By.xpath("//input[contains(@class, 'event-title-input')]");
    private By eventDescriptionIframe = By.xpath("//iframe[contains(@class, 'wysihtml5-sandbox')]");
    private By eventDescriptionInput = By.xpath("//*[contains(@class, 'js-description-text')]");
    private By eventCoverImagePickerButton = By.xpath("//button[contains(@class, 'js-image-choose')]");
    private By confirmNoUpdateToTimeButton = By.id("confirmBtn");
    private By privacyTypeButton = By.xpath("//*[contains(@class, 'js-privacy-type')]");
    private By eventTitleField = By.xpath("//*[contains(@class, 'js-title-input')]");
    private By planEventButton = By.xpath("//*[contains(@class, 'js-save-event')]");
    private By eventInviteFriendButton = By.cssSelector(".js-invite-friends");
    private By friendElement = By.cssSelector(".black-heading-link.js-link-profile > h4");
    private By eventAddInterestButton = By.cssSelector(".js-add-interests");
    private By interestInput = By.cssSelector(".js-interest-input");

    private By interestSuggestion = By.cssSelector(".tt-suggestion");

    private By eventAddDateButton = By.cssSelector(".js-set-date");
    private By allDayEventCheckBox = By.cssSelector(".js-all-day");

    private By eventlocationButton = By.cssSelector(".js-set-location");
    private By locationInput = By.xpath("//input[contains(@class, 'js-place-autocomplete')]");
    private By locationResult = By.cssSelector(".pac-matched");

    private By saveButton = By.xpath("//*[contains(@class, 'js-btn-save')]");

    private By eventLinkButton = By.cssSelector(".js-add-link-section");
    //By eventLinkInput = By.xpath("//*[@id=\"app\"]/div/div[3]/div[1]/div/div[2]/div/div/div/div/div/div[1]/input");
    private By eventLinkInput = By.cssSelector(".link-preview-search-container > input");

    private By startDateInputPicker = By.xpath("//input[contains(@class, 'js-datepicker-start-date')]");
    private By pickerNextMonthButton = By.xpath("//th[contains(@class, 'next')]");
    private By pickerRandomDayButton = By.xpath("//div[contains(@class, 'datepicker-days')]//tbody//tr[4]/td[1]");
    private By pickerStartTimeDropdown = By.xpath("//select[contains(@class, 'js-start-time')]");

    public String enterEventDetails(String des) throws InterruptedException {
        driver.switchTo().frame(driver.findElement(eventDescriptionIframe));
        driver.findElement(eventDescriptionInput).sendKeys(des);
        driver.switchTo().parentFrame();
        String eventName = "Event: "+ java.time.LocalDateTime.now();
        driver.findElement(eventNameInput).sendKeys(eventName);
        return eventName;
    }

    public UploadMediaPage openChooseCoverButton() {
        driver.findElement(eventCoverImagePickerButton).click();
        return new UploadMediaPage(driver, jsexec);
    }

    public String enterBasicEventTitle(String des) throws InterruptedException {
        driver.findElement(eventTitleField).sendKeys(des);
        return des;
    }

    public EventCreatePage selectPrivacyType(String privacy) {
        driver.findElement(privacyTypeButton).sendKeys(privacy);
        return this;
    }

    public EventCreatePage inviteFriend() throws InterruptedException {
        RemoteWebElement element = (RemoteWebElement) (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(eventInviteFriendButton));
        jsexec.executeScript("arguments[0].click();", element);
        //jsexec.executeScript("arguments[0].click();", driver.findElement(eventInviteFriendButton));
        jsexec.executeScript("arguments[0].click();", driver.findElement(friendElement));
        jsexec.executeScript("arguments[0].click();", driver.findElement(saveButton));
        return this;
    }

    public EventCreatePage addInterest(String interest) throws InterruptedException {
        jsexec.executeScript("arguments[0].click();", driver.findElement(eventAddInterestButton));
        driver.findElement(interestInput).sendKeys(interest);
        driver.findElement(interestSuggestion).click();
        jsexec.executeScript("arguments[0].click();", driver.findElement(saveButton));
        return this;
    }

    public EventCreatePage selectAllDayEvent() throws InterruptedException {
        jsexec.executeScript("arguments[0].click();", driver.findElement(eventAddDateButton));
        jsexec.executeScript("arguments[0].click();", driver.findElement(allDayEventCheckBox));
        jsexec.executeScript("arguments[0].click();", driver.findElement(saveButton));
        return this;
    }

    public EventCreatePage selectLocation(String location) throws InterruptedException{
        jsexec.executeScript("arguments[0].click();", driver.findElement(eventlocationButton));
        driver.findElement(locationInput).sendKeys(Keys.CLEAR);
        driver.findElement(locationInput).sendKeys(location);
        Thread.sleep(2000);
        driver.findElement(locationInput).sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        jsexec.executeScript("arguments[0].click();", driver.findElement(saveButton));
        return this;
    }

    public EventDetailsPage planEvent() throws InterruptedException {
        jsexec.executeScript("arguments[0].click();", driver.findElement(planEventButton));
        return new EventDetailsPage(driver, jsexec);
    }

    public void planEventWithoutDate(){
        if (driver.findElements(confirmNoUpdateToTimeButton).size() > 0) {
            jsexec.executeScript("arguments[0].click();", driver.findElement(confirmNoUpdateToTimeButton));
        }
    }

    public EventCreatePage selectAddlink(String link)  {
        jsexec.executeScript("arguments[0].click();", driver.findElement(eventLinkButton));
        driver.findElement(eventLinkInput).sendKeys(Keys.CLEAR);
        driver.findElement(eventLinkInput).sendKeys(link);
        return this;
    }

    public void setEventDatetime() throws Throwable{
        jsexec.executeScript("arguments[0].click();", driver.findElement(eventAddDateButton));
        //waits were inconsistent so I added in this sleep hack
        Thread.sleep(2000);
        driver.findElement(startDateInputPicker).click();
        driver.findElement(pickerNextMonthButton).click();
        driver.findElement(pickerRandomDayButton).click();
        driver.findElement(pickerStartTimeDropdown).sendKeys("22:00");
        driver.findElement(saveButton).click();
    }

    public String getEventDate() {
       // System.out.print(driver.findElement(eventAddDateButton).getText());
        return driver.findElement(eventAddDateButton).getText();
    }
}
