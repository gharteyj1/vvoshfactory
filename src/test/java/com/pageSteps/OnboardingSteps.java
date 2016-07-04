package com.pageSteps;

import com.pageObjects.*;
import com.utility.globalConstant;
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
 * Created by user on 23/06/2016.
 */
public class OnboardingSteps {

    WebDriver driver;
    JavascriptExecutor jsexec;
    HomePage homePage;
    LoginPage loginPage;
    TimelinePage timelinePage;
    OnboardingPage onboardingPage;
    UploadMediaPage uploadMediaPage;
    FeedPage feedPage;

    @Before("@onboarding")
    public void initDriver() {
        System.setProperty("webdriver.chrome.driver","chromedriver");
        //System.setProperty("webdriver.chrome.logfile", "/opt/webdriver-tests/chromedriver.log");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        this.driver = new ChromeDriver(options);
        this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        this.driver.manage().window().setSize(new Dimension(1920,1080));
    }

    @After("@onboarding")
    public void afterScenarios() {
        homePage.closeBrowser();
    }

    @Given("^I begin the onboarding process$")
    public void I_begin_the_onboarding_process() throws Throwable {
        homePage = new HomePage(driver, jsexec);
        homePage.navigateToHome();
        loginPage = homePage.openLoginPage();
        loginPage.completeLoginForm(globalConstant.accountOneLogin, globalConstant.accountOnePassword);
        timelinePage = loginPage.submitLogin();
        onboardingPage = timelinePage.openOnboardingPage();
    }

    @When("^I confirm I understand the love/try feature$")
    public void I_confirm_I_understand_the_love_try_feature() throws Throwable {
        onboardingPage.goToNextPage();
    }

    @And("^I add interests$")
    public void I_add_interests() throws Throwable {
        onboardingPage.loveInterest(globalConstant.interestFilm);
        onboardingPage.loveInterest(globalConstant.interestCooking);
        onboardingPage.loveInterest(globalConstant.interestFitness);
        onboardingPage.tryInterest(globalConstant.interestIndieMusic);
        onboardingPage.tryInterest(globalConstant.interestElectronicMusic);
    }

    @And("^I connect with friends$")
    public void I_connect_with_friends() throws Throwable {
        onboardingPage.goToNextPage();
        onboardingPage.addFirstUserAsFriend();
    }

    @And("^I add my personal details$")
    public void I_add_my_personal_details() throws Throwable {
        onboardingPage.goToNextPage();

        uploadMediaPage = onboardingPage.openProfileImageMediaDialog();
        uploadMediaPage.uploadDefinedImage(globalConstant.imageFolder+globalConstant.basicTestImageName);
        uploadMediaPage.saveMedia();
        onboardingPage = uploadMediaPage.cropOnboardingMedia();

        uploadMediaPage =onboardingPage.openCoverImageMediaDialog();
        uploadMediaPage.uploadDefinedImage(globalConstant.imageFolder+globalConstant.basicTestImageName);
        uploadMediaPage.saveMedia();
        onboardingPage = uploadMediaPage.cropOnboardingMedia();
    }

    @And("^I choose to proceed to the feed$")
    public void I_choose_to_proceed_to_the_feed() throws Throwable {
        onboardingPage.goToNextPage();
        feedPage = onboardingPage.openFeedPage();
    }

    @Then("^I see the myvVoosh feed$")
    public void I_see_the_myvVoosh_feed() throws Throwable {
        assert feedPage.onFeedPage();
    }
}
