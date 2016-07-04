package com.pageSteps;

import com.pageObjects.*;
import com.utility.EventMethods;
import com.utility.globalConstant;
import com.utility.globalElement;
import com.utility.manageSessions;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 04/05/2016.
 */
public class EventSteps {

    WebDriver driver;
    JavascriptExecutor jsexec;
    HomePage homePage;
    TimelinePage timelinePage;
    EventsPage eventsPage;
    EventCreatePage eventCreatePage;
    LoginPage loginPage;
    EventDetailsPage eventDetailsPage;
    MyEventsPage myEventsPage;
    UploadMediaPage uploadMediaPage;
    ProfileTimelinePage profileTimelinepage;
    ProfileEventsPage profileEventsPage;
    EventMethods EventMethods;
    FriendsPage friendsPage;
    FindFriendsPage findFriendsPage;

    String eventName;
    String eventDate;

    @Before("@WIP")
    public void initDriver() {
        System.setProperty("webdriver.chrome.driver","chromedriver");
        //System.setProperty("webdriver.chrome.logfile", "/opt/webdriver-tests/chromedriver.log");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        this.driver = new ChromeDriver(options);
        this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        this.driver.manage().window().setSize(new Dimension(1920, 1080));
    }

    @After("@WIP")
    public void afterScenarios() {
        homePage.closeBrowser();
    }

    @Given("^I want to create an event$")
    public void I_want_to_create_an_event() throws Throwable {
        homePage = new HomePage(driver, jsexec);
        homePage.navigateToHome();
        loginPage = homePage.openLoginPage();
        loginPage.completeLoginForm(globalConstant.accountOneLogin, globalConstant.accountOnePassword);
        timelinePage = loginPage.submitLogin();
    }

    private String getFormattedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmm");
        LocalDateTime currentime = LocalDateTime.now();
        return currentime.format(formatter);
    }

    @Then("^I should see the Event Details page$")
    public void iShouldSeeTheEventDetailsPage() throws Throwable {
        //System.out.println(eventName);
        assert eventDetailsPage.checkEventTitle(eventName);
    }

    @When("^I remove the event$")
    public void I_remove_the_event() throws Throwable {
        eventDetailsPage.openDropDownMenu();
        eventsPage = eventDetailsPage.removeEvent();
    }

    @Then("^the event does not appear in My Events$")
    public void the_event_does_not_appear_in_My_Events() throws Throwable {
        myEventsPage = eventsPage.openMyEventsPage();
        assert !EventMethods.checkEventInList(eventName, driver, globalElement.myEventList);
    }

    @When("^I create a \"([^\"]*)\" event$")
    public void I_create_a_event(String arg1) throws Throwable {
        eventsPage = timelinePage.openEventsPage();
        eventCreatePage = eventsPage.openCreateEventPage();
        eventCreatePage.selectPrivacyType(arg1);
        eventName = ("event-"+ getFormattedDateTime());
        eventCreatePage.enterBasicEventTitle(eventName);
    }
    @And("^I save the event$")
    public void I_save_the_event() throws Throwable {
        eventDetailsPage = eventCreatePage.planEvent();
        eventCreatePage.planEventWithoutDate();
    }

    @And("^I add the link \"([^\"]*)\"$")
    public void I_add_the_link(String arg1) throws Throwable {
        eventCreatePage.selectAddlink(arg1);
    }

    @And("^I add a cover image$")
    public void I_add_a_cover_image() throws Throwable {
        uploadMediaPage = eventCreatePage.openChooseCoverButton();
        uploadMediaPage.uploadDefinedImage(globalConstant.imageFolder + "EventCoverImage.jpg");
        uploadMediaPage.saveMedia();
        eventCreatePage = uploadMediaPage.cropEventMedia();
    }

    @And("^I add the interest \"([^\"]*)\"$")
    public void I_add_the_interest(String arg1) throws Throwable {
        eventCreatePage.addInterest(arg1);
    }

    @And("^I set the date$")
    public void I_set_the_date() throws Throwable {
        eventCreatePage.setEventDatetime();
        eventDate = eventCreatePage.getEventDate();
    }

    @And("^I add the location \"([^\"]*)\"$")
    public void I_add_the_location(String arg1) throws Throwable {
        eventCreatePage.selectLocation(arg1);
    }

    @And("^I set the event to occur all day$")
    public void I_set_the_event_to_occur_all_day() throws Throwable {
        eventCreatePage.selectAllDayEvent();
    }

    @And("^I invite a friend$")
    public void I_invite_a_friend() throws Throwable {
        eventCreatePage.inviteFriend();
    }

    @And("^I see the link$")
    public void I_see_the_link() throws Throwable {
        assert eventDetailsPage.checkLinkAppears();
    }

    @And("^I see the cover image$")
    public void I_see_the_cover_image() throws Throwable {
        assert eventDetailsPage.coverImageIsNotDefault();
    }

    @And("^the interest \"([^\"]*)\" has been tagged$")
    public void the_interest_has_been_tagged(String arg1) throws Throwable {
        assert eventDetailsPage.checkInterestAppears(arg1);
    }

    @And("^I see the date I specified$")
    public void I_see_the_date_I_specified() throws Throwable {
        assert eventDetailsPage.checkEventStartDateEquals(eventDate);
    }

    @And("^the event occurs all day$")
    public void the_event_occurs_all_day() throws Throwable {
        assert eventDetailsPage.checkEventIsAllDay();
    }

    @And("^the friend is invited$")
    public void the_friend_is_invited() throws Throwable {
        assert eventDetailsPage.checkInviteeCountEquals("1");
    }

    @And("^the event location is \"([^\"]*)\"$")
    public void the_event_location_is(String arg1) throws Throwable {
        assert eventDetailsPage.checkEventLocationEquals(arg1);
    }

    @And("^I see the event in My Events$")
    public void I_can_see_the_event_in_My_Events() throws Throwable {
        eventsPage = eventDetailsPage.openEventsPage();
        myEventsPage = eventsPage.openMyEventsPage();
        assert EventMethods.checkEventInList(eventName, driver, globalElement.myEventList);
    }

    @And("^I see the event in myvVoosh Timeline$")
    public void I_see_the_event_in_myvVoosh_Timeline() throws Throwable {
        timelinePage = myEventsPage.openTimelinePage();
        assert EventMethods.verifyEventAdded(eventName, driver);
    }

    @And("^I see the event in my Profile Events page$")
    public void I_see_the_event_in_my_Profile_Events_page() throws Throwable {
        timelinePage.openBurgerMenu();
        profileTimelinepage = timelinePage.openProfilePage();
        profileEventsPage = profileTimelinepage.openProfileEventsPage();
        assert EventMethods.checkEventInList(eventName, driver, globalElement.myProfileEventList);
    }

    @And("^my friends see the event in their myvVoosh Timeline$")
    public void my_friends_see_the_event_in_their_myvVoosh_Timeline() throws Throwable {
        timelinePage = manageSessions.logoutAndLogin(globalConstant.accountTwoLogin, globalConstant.accountTwoPassword, timelinePage, loginPage, homePage);
        //timelinePage = myEventsPage.openTimelinePage();
        assert EventMethods.verifyEventAdded(eventName, driver);
    }

    @And("^my friends see the event in my Profile Timeline$")
    public void my_friends_see_the_event_in_my_Profile_Timeline() throws Throwable {
        friendsPage = timelinePage.openFriendsPage();
        profileTimelinepage = friendsPage.openFriendProfileTimeline(globalConstant.accountOneName);
        assert EventMethods.verifyEventAdded(eventName, driver);
    }

    @And("^strangers see the event in my Profile Timeline$")
    public void strangers_see_the_event_in_my_Profile_Timeline() throws Throwable {
        timelinePage = manageSessions.logoutAndLogin(globalConstant.accountThreeLogin, globalConstant.accountThreePassword, timelinePage, loginPage, homePage);

        friendsPage = timelinePage.openFriendsPage();
        findFriendsPage = friendsPage.openFindFriendsPage();
        findFriendsPage.searchForFriend(globalConstant.accountOneName);
        profileTimelinepage = findFriendsPage.openProfileTimelinePage(globalConstant.accountOneName);
        assert EventMethods.verifyEventAdded(eventName, driver);
    }

    @And("^strangers see the event in my Profile Events page$")
    public void strangers_see_the_event_in_my_Profile_Events_page() throws Throwable {
        profileEventsPage = profileTimelinepage.openProfileEventsPage();
        assert EventMethods.checkEventInList(eventName, driver, globalElement.myProfileEventList);
    }

    @And("^invitees see the event in their myvVoosh Timeline$")
    public void invitees_see_the_event_in_their_myvVoosh_Timeline() throws Throwable {
        timelinePage = manageSessions.logoutAndLogin(globalConstant.accountTwoLogin, globalConstant.accountTwoPassword, timelinePage, loginPage, homePage);
        assert EventMethods.verifyEventAdded(eventName, driver);
    }

    @And("^invitees see the event in their My Events$")
    public void invitees_see_the_event_in_their_My_Events() throws Throwable {
        eventsPage = timelinePage.openEventsPage();
        myEventsPage = eventsPage.openMyEventsPage();
        assert EventMethods.checkEventInList(eventName, driver, globalElement.myEventList);
    }

    @And("^invitees see the event in my Profile Timeline$")
    public void invitees_see_the_event_in_my_Profile_Timeline() throws Throwable {
        timelinePage = myEventsPage.openTimelinePage();
        friendsPage = timelinePage.openFriendsPage();
        profileTimelinepage = friendsPage.openFriendProfileTimeline(globalConstant.accountOneName);
        assert EventMethods.verifyEventAdded(eventName, driver);
    }
}
