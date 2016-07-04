package com.pageSteps;

import com.pageObjects.*;
import com.utility.globalConstant;
import cucumber.api.java.After;
import cucumber.api.java.Before;
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
 * Created by user on 11/05/2016.
 */
public class SettingsSteps {

    WebDriver driver;
    JavascriptExecutor jsexec;
    HomePage homePage;
    LoginPage loginPage;
    TimelinePage timelinePage;
    SettingsPage settingsPage;
    UploadMediaPage uploadMediaPage;
    ProfileTimelinePage profileTimelinePage;

    @Before("@settings")
    public void initDriver() {
        System.setProperty("webdriver.chrome.driver","chromedriver");
        //System.setProperty("webdriver.chrome.logfile", "/opt/webdriver-tests/chromedriver.log");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        this.driver = new ChromeDriver(options);
        this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        this.driver.manage().window().setSize(new Dimension(1920,1080));
    }

    @After("@settings")
    public void afterScenarios() {
        //homePage.closeBrowser();
        this.driver.quit();
    }

    @Given("^I want to upload a new profile photo$")
    public void I_want_to_upload_a_new_profile_photo() throws Throwable {
        homePage = new HomePage(driver, jsexec);
        homePage.navigateToHome();
        loginPage = homePage.openLoginPage();
        loginPage.completeLoginForm(globalConstant.accountOneLogin, globalConstant.accountOnePassword);
        timelinePage = loginPage.submitLogin();
    }

    @When("^I add a new profile photo$")
    public void I_add_a_new_profile_photo() throws Throwable {
        timelinePage.openBurgerMenu();
        settingsPage = timelinePage.openSettingsPage();
        settingsPage.removeProfileImage();
        settingsPage.saveSettings();
        uploadMediaPage =settingsPage.openProfileImageMediaDialog();
        uploadMediaPage.uploadDefinedImage(globalConstant.imageFolder+globalConstant.basicTestImageName);
        uploadMediaPage.saveMedia();
        settingsPage = uploadMediaPage.cropSettingsMedia();
        settingsPage.saveSettings();
    }

    @Then("^I see the new profile photo in my profile page$")
    public void I_see_the_new_profile_photo_in_my_profile_page() throws Throwable {
        timelinePage = settingsPage.openTimelinePage();
        timelinePage.openBurgerMenu();
        profileTimelinePage = timelinePage.openProfilePage();
        assert profileTimelinePage.profileImageIsNotDefault();
    }

    @Given("^I want to upload a new cover photo$")
    public void I_want_to_upload_a_new_cover_photo() throws Throwable {
        homePage = new HomePage(driver, jsexec);
        homePage.navigateToHome();
        loginPage = homePage.openLoginPage();
        loginPage.completeLoginForm(globalConstant.accountOneLogin, globalConstant.accountOnePassword);
        timelinePage = loginPage.submitLogin();
    }

    @When("^I add a new cover photo$")
    public void I_add_a_new_cover_photo() throws Throwable {
        timelinePage.openBurgerMenu();
        settingsPage = timelinePage.openSettingsPage();

        settingsPage.removeCoverImage();
        settingsPage.saveSettings();

        uploadMediaPage =settingsPage.openCoverImageMediaDialog();
        uploadMediaPage.uploadDefinedImage(globalConstant.imageFolder+globalConstant.basicTestImageName);
        uploadMediaPage.saveMedia();
        settingsPage = uploadMediaPage.cropSettingsMedia();
        settingsPage.saveSettings();
    }

    @Then("^I see the new cover photo in my profile page$")
    public void I_see_the_new_cover_photo_in_my_profile_page() throws Throwable {
        timelinePage = settingsPage.openTimelinePage();
        timelinePage.openBurgerMenu();
        profileTimelinePage = timelinePage.openProfilePage();
        assert profileTimelinePage.coverImageIsNotDefault();
    }

}
