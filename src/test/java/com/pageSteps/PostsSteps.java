package com.pageSteps;

import com.pageObjects.*;
import com.utility.globalConstant;
import com.utility.manageSessions;
import com.utility.PageUtilities;
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

public class PostsSteps {

    HomePage homePage;
    LoginPage loginPage;
    TimelinePage timelinePage;
    ProfileTimelinePage profileTimelinePage;
    InterestOverviewPage interestOverviewPage;
    InterestCommunityPage interestCommunityPage;
    PostDetailsPage postDetailsPage;
    PostCreateEditPage postCreateEditPage;
    FriendsPage friendsPage;
    FindFriendsPage findFriendsPage;

    WebDriver driver;
    JavascriptExecutor jsexec;
    String postName = "";

    @Before("@posts")
    public void initDriver() {
        System.setProperty("webdriver.chrome.driver","chromedriver");
        //System.setProperty("webdriver.chrome.logfile", "/opt/webdriver-tests/chromedriver.log");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        this.driver = new ChromeDriver(options);
        this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        this.driver.manage().window().setSize(new Dimension(1920, 1080));
    }

    @After("@posts")
    public void afterScenarios() {
        homePage.closeBrowser();
    }

    @Given("^I want to create a post$")
    public void I_want_to_create_a_post() throws Throwable {
        homePage = new HomePage(driver, jsexec);
        homePage.navigateToHome();
        loginPage =homePage.openLoginPage();
        loginPage.completeLoginForm(globalConstant.accountOneLogin, globalConstant.accountOnePassword);
        timelinePage = loginPage.submitLogin();
    }

    @When("^I create a post$")
    public void I_create_a_post() throws Throwable {
        postCreateEditPage = timelinePage.openCreatePostDialog();
        postName = postCreateEditPage.postAddTitle();
    }

    @And("^I add \"([^\"]*)\" content$")
    public void I_add_content(String arg1) throws Throwable {
        addContent(arg1);
    }

    private void addContent(String name) throws InterruptedException {
        if (name.equals("Text")){
            postCreateEditPage.postAddText("This is a text post!!! ;-)");
        }
        else if (name.equals("Media")){
            postCreateEditPage.postAddImage();
        }
        else if (name.equals("Link")){
            postCreateEditPage.postAddURL("https://www.google.co.uk");
            Thread.sleep(2000);
        }
        else if (name.equals("Location")){
            postCreateEditPage.postAddLocation();
        }
        postCreateEditPage.addContentToPostOption();
    }

    @Then("^a new \"([^\"]*)\" post is created$")
    public void a_new_post_is_created(String arg1) throws Throwable {
        postDetailsPage = postCreateEditPage.savePost();
        timelinePage = postDetailsPage.closePostDialog();
        assert PageUtilities.verifyPostAdded(postName, driver);
    }

    @When("^I create a post tagged with an interest$")
    public void I_create_a_post_tagged_with_an_interest() throws Throwable {
        postCreateEditPage = timelinePage.openCreatePostDialog();
        postCreateEditPage.postAddTitle();
        postCreateEditPage.postAddText("Interest tagged text post!!! ;-)");
        postCreateEditPage.postAddInterest("Football");
    }

    @Then("^a new post is created$")
    public void a_new_post_is_created() throws Throwable {
        postDetailsPage = postCreateEditPage.savePost();
        timelinePage = postDetailsPage.closePostDialog();
    }

    @And("^I see the post on my vVoosh Timeline$")
    public void I_see_the_post_on_my_vVoosh_Timeline() throws Throwable {
        assert PageUtilities.verifyPostAdded(postName, driver);
    }

    @And("^I see the post on my Profile Timeline$")
    public void I_see_the_post_on_my_Profile_Timeline() throws Throwable {
        timelinePage.openBurgerMenu();
        profileTimelinePage = timelinePage.openProfilePage();
        assert PageUtilities.verifyPostAdded(postName, driver);
    }

    @And("^I see the post on my vVoosh feed$")
    public void I_see_the_post_on_my_vVoosh_feed() throws Throwable {

    }

    @And("^I see the post on the Interest Community page$")
    public void I_see_the_post_on_the_Interest_Community_page() throws Throwable {
        interestOverviewPage = timelinePage.openInterestOverviewbyLink();
        interestCommunityPage = interestOverviewPage.openInterestCommunityPage();
        assert interestCommunityPage.verifyPostAdded(postName);
    }

    @And("^I open the post$")
    public void I_open_the_post() throws Throwable {
        postDetailsPage = postCreateEditPage.savePost();
        timelinePage = postDetailsPage.closePostDialog();
        postDetailsPage = timelinePage.openPostDetailsDialog(postName);
    }

    @Then("^I see the post details page$")
    public void I_see_the_post_details_page() throws Throwable {
        assert postDetailsPage.verifyPostIsCorrect(postName);
    }

    @Given("^I create a post with \"([^\"]*)\" content$")
    public void I_create_a_post_with_content(String arg1) throws Throwable {
        homePage = new HomePage(driver, jsexec);
        homePage.navigateToHome();
        loginPage =homePage.openLoginPage();
        loginPage.completeLoginForm(globalConstant.accountOneLogin, globalConstant.accountOnePassword);
        timelinePage = loginPage.submitLogin();
        postCreateEditPage = timelinePage.openCreatePostDialog();
        postName = postCreateEditPage.postAddTitle();
        addContent(arg1);
    }

    @Then("^the post will have both \"([^\"]*)\" and \"([^\"]*)\" content$")
    public void the_post_will_have_both_and_content(String arg1, String arg2) throws Throwable {
        postDetailsPage = postCreateEditPage.savePost();
        assert postDetailsPage.verifyPostContent(arg1, true);
        assert postDetailsPage.verifyPostContent(arg2, true);
    }

    @Given("^I create a post with \"([^\"]*)\" and \"([^\"]*)\" content$")
    public void I_create_a_post_with_and_content(String arg1, String arg2) throws Throwable {
        homePage = new HomePage(driver, jsexec);
        homePage.navigateToHome();
        loginPage =homePage.openLoginPage();
        loginPage.completeLoginForm(globalConstant.accountOneLogin, globalConstant.accountOnePassword);
        timelinePage = loginPage.submitLogin();
        postCreateEditPage = timelinePage.openCreatePostDialog();
        postName = postCreateEditPage.postAddTitle();
        addContent(arg1);
        addContent(arg2);
        postDetailsPage = postCreateEditPage.savePost();
        timelinePage = postDetailsPage.closePostDialog();
    }

    @When("^I remove \"([^\"]*)\" content$")
    public void I_remove_content(String arg1) throws Throwable {
        driver.get(globalConstant.URL);
        postDetailsPage = timelinePage.openPostDetailsDialog(postName);
        postDetailsPage.openPostDetailsDropDown();
        postCreateEditPage = postDetailsPage.editPost();
        assert postCreateEditPage.removeContentItem(arg1);
    }

    @Then("^the post will have \"([^\"]*)\" and not \"([^\"]*)\"$")
    public void the_post_will_have_and_not(String arg1, String arg2) throws Throwable {
        postDetailsPage = postCreateEditPage.savePost();
        //there is a bug where after editing a post the post html is not destroyed - so there is at least one version on the deleted content
        //even after it is removed from a post. This will work again
        assert postDetailsPage.verifyPostContent(arg1, true);
        assert postDetailsPage.verifyPostContent(arg2, false);
    }

    @When("^I create a \"([^\"]*)\" post$")
    public void I_create_a_post(String arg1) throws Throwable {
        postCreateEditPage = timelinePage.openCreatePostDialog();
        postName = postCreateEditPage.postAddTitle();
        postCreateEditPage.postAddInterest("Film");
        postCreateEditPage.postSetPrivacy(arg1);
    }

    @And("^my friends see the post on their myvVoosh Timeline$")
    public void my_friends_see_the_post_on_their_myvVoosh_Timeline() throws Throwable {
        timelinePage = manageSessions.logoutAndLogin(globalConstant.accountTwoLogin, globalConstant.accountTwoPassword, timelinePage, loginPage, homePage);
        assert PageUtilities.verifyPostAdded(postName, driver);
    }

    @And("^my friends see the post on my Profile Timeline$")
    public void my_friends_see_the_post_on_my_Profile_Timeline() throws Throwable {
        friendsPage = timelinePage.openFriendsPage();
        if (friendsPage.checkUserInFriendsList(globalConstant.accountOneName)) {
            profileTimelinePage = friendsPage.openFriendProfileTimeline(globalConstant.accountOneName);
            assert  PageUtilities.verifyPostAdded(postName, driver);
        }
        else
        {
            System.out.print("Unable to find user -- Test Aborted");
        }
    }

    @And("^strangers don't see the post on their myvVoosh Timeline$")
    public void strangers_don_t_see_the_post_on_their_myvVoosh_Timeline() throws Throwable {
        timelinePage = manageSessions.logoutAndLogin(globalConstant.accountThreeLogin, globalConstant.accountThreePassword, timelinePage, loginPage, homePage);
        assert PageUtilities.verifyPostNotAdded(postName, driver);
    }

    @And("^strangers don't see the post on my Profile Timeline$")
    public void strangers_don_t_see_the_post_on_my_Profile_Timeline() throws Throwable {
        friendsPage = timelinePage.openFriendsPage();
        findFriendsPage = friendsPage.openFindFriendsPage();
        findFriendsPage.searchForFriend(globalConstant.accountOneName);
        profileTimelinePage = findFriendsPage.openProfileTimelinePage(globalConstant.accountOneName);
        assert PageUtilities.verifyPostNotAdded(postName, driver);
    }

    @And("^my friends don't see the post on their myvVoosh Timeline$")
    public void my_friends_don_t_see_the_post_on_their_myvVoosh_Timeline() throws Throwable {
        timelinePage = manageSessions.logoutAndLogin(globalConstant.accountTwoLogin, globalConstant.accountTwoPassword, timelinePage, loginPage, homePage);
        assert PageUtilities.verifyPostNotAdded(postName, driver);

    }

    @And("^my friends don't see the post on my Profile Timeline$")
    public void my_friends_don_t_see_the_post_on_my_Profile_Timeline() throws Throwable {
        friendsPage = timelinePage.openFriendsPage();
        if (friendsPage.checkUserInFriendsList(globalConstant.accountOneName)) {
            profileTimelinePage = friendsPage.openFriendProfileTimeline(globalConstant.accountOneName);
            assert PageUtilities.verifyPostNotAdded(postName, driver);
        }
        else
        {
            System.out.print("Unable to find user -- Test Aborted");
        }
    }
}
