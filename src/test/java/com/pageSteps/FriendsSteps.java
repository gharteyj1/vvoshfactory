package com.pageSteps;

import com.pageObjects.*;
import com.utility.globalConstant;
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

import java.util.concurrent.TimeUnit;

/**
 * TODO
 * These tests need to be refactored to work with ChromeDriver
 * they also need to be able to be executed after test failures, i.e. we need to ensure the friendships being added
 * aren't there when the tests start
 */

public class FriendsSteps {

    WebDriver driver;
    JavascriptExecutor jsexec;
    HomePage homePage;
    LoginPage loginPage;
    TimelinePage timelinePage;
    FriendsPage friendsPage;
    FindFriendsPage findFriendsPage;
    FriendRequestsPage friendRequestsPage;
    ProfileTimelinePage profileTimelinePage;

    @Before("@friends")
    public void initDriver() {
        System.setProperty("webdriver.chrome.driver","chromedriver");
        //System.setProperty("webdriver.chrome.logfile", "/opt/webdriver-tests/chromedriver.log");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        this.driver = new ChromeDriver(options);
        this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        this.driver.manage().window().setSize(new Dimension(1920, 1080));
    }

    @After("@friends")
    public void afterScenarios() throws Throwable{
        //tear down
        homePage.closeBrowser();
    }

    @Given("^I am not friends with User A$")
    public void I_am_not_friends_with_User_A() throws Throwable {
        homePage = new HomePage(driver, jsexec);
        homePage.navigateToHome();
        loginPage=homePage.openLoginPage();
        loginPage.completeLoginForm(globalConstant.accountOneLogin, globalConstant.accountOnePassword);
        timelinePage = loginPage.submitLogin();
        friendsPage = timelinePage.openFriendsPage();
    }

    @And("^I send a friend request to User A$")
    public void I_send_a_friend_request_to_User_A() throws Throwable {
        findFriendsPage = friendsPage.openFindFriendsPage();
        findFriendsPage.searchForFriend(globalConstant.accountTwoName);
        Thread.sleep(2000);
        findFriendsPage.sendFriendRequest();
    }

    @When("^the friend request is accepted$")
    public void the_friend_request_is_accepted() throws Throwable {
        timelinePage = manageSessions.logoutAndLogin(globalConstant.accountTwoLogin, globalConstant.accountTwoPassword, timelinePage, loginPage, homePage);

        friendsPage = timelinePage.openFriendsPage();
        friendRequestsPage = friendsPage.openFriendRequestsPage();
        friendRequestsPage.acceptFriendRequest();
    }

    @When("^the friend request is rejected$")
    public void the_friend_request_is_rejected() throws Throwable {
        timelinePage = manageSessions.logoutAndLogin(globalConstant.accountTwoLogin, globalConstant.accountTwoPassword, timelinePage, loginPage, homePage);

        friendsPage = timelinePage.openFriendsPage();
        friendRequestsPage = friendsPage.openFriendRequestsPage();
        friendRequestsPage.ignoreFriendRequest();
    }

    @Then("^User A and I become friends$")
    public void User_A_and_I_become_friends() throws Throwable {
        timelinePage = manageSessions.logoutAndLogin(globalConstant.accountOneLogin, globalConstant.accountOnePassword, timelinePage, loginPage, homePage);

        friendsPage = timelinePage.openFriendsPage();
        assert friendsPage.checkUserInFriendsList(globalConstant.accountTwoName);

        friendsPage.removeFriend();
    }

    @Then("^User A and I do not become friends$")
    public void User_A_and_I_do_not_become_friends() throws Throwable {
        timelinePage = manageSessions.logoutAndLogin(globalConstant.accountOneLogin, globalConstant.accountOnePassword, timelinePage, loginPage, homePage);

        friendsPage = timelinePage.openFriendsPage();
        assert friendsPage.checkUserNotInFriendsList(globalConstant.accountTwoName);
    }

    @When("^I un-friend User A$")
    public void I_un_friend_User_A() throws Throwable {
        timelinePage = manageSessions.logoutAndLogin(globalConstant.accountTwoLogin, globalConstant.accountTwoPassword, timelinePage, loginPage, homePage);

        friendsPage = timelinePage.openFriendsPage();
        friendsPage.removeFriend();
    }

    @Then("^User A and I are no longer friends$")
    public void User_A_and_I_are_no_longer_friends() throws Throwable {
        Thread.sleep(2000);
        assert friendsPage.checkUserNotInFriendsList(globalConstant.accountTwoName);
    }

    @When("^the friend request is rejected via the notification tab$")
    public void the_friend_request_is_rejected_via_the_notification_tab() throws Throwable {
        timelinePage = manageSessions.logoutAndLogin(globalConstant.accountTwoLogin, globalConstant.accountTwoPassword, timelinePage, loginPage, homePage);

        timelinePage.openNotificationsMenu();
        timelinePage.notificationIgnoreFriendRequest();
    }

    @When("^the friend request is accepted via the notification tab$")
    public void the_friend_request_is_accepted_via_the_notification_tab() throws Throwable {
        timelinePage = manageSessions.logoutAndLogin(globalConstant.accountTwoLogin, globalConstant.accountTwoPassword, timelinePage, loginPage, homePage);

        timelinePage.openNotificationsMenu();
        timelinePage.notificationAcceptFriendRequest();
    }

    @When("^I search for User A in Find Friends$")
    public void I_search_for_User_A_in_Find_Friends() throws Throwable {
        findFriendsPage = friendsPage.openFindFriendsPage();
        findFriendsPage.searchForFriend(globalConstant.accountThreeName);
        Thread.sleep(2000);
    }

    @Then("^I open their profile$")
    public void I_open_their_profile() throws Throwable {
        profileTimelinePage = findFriendsPage.openProfileTimelinePage(globalConstant.accountThreeName);
        assert profileTimelinePage.checkUsernameEquals(globalConstant.accountThreeName);
    }
}
