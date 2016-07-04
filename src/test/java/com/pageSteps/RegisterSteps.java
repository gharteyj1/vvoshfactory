package com.pageSteps;

import com.pageObjects.*;
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
 * They also need to be reworked because the flow of inviting people has changes. It should no longer use the
 * admin panel but instead use the /c/1234 code to invite users. There's also a new endpoint that Paul created
 * to validate invites instead of having to go to mailinator so check with him
 */

public class RegisterSteps {

    WebDriver driver;
    JavascriptExecutor jsexec;
    AdminPage adminPage;
    VerificationPage verificationPage;
    RegistrationPage registrationPage;
    HomePage homePage;
    TimelinePage timelinePage;
    InterestsActivitiesPage interestsActivitiesPage;
    WelcomePage welcomePage;
    MailinatorPage mailinatorPage;
    String inviteEmail;

    @Before("@register")
    public void initDriver() {
        System.setProperty("webdriver.chrome.driver","chromedriver");
        //System.setProperty("webdriver.chrome.logfile", "/opt/webdriver-tests/chromedriver.log");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        this.driver = new ChromeDriver(options);
        this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        this.driver.manage().window().setSize(new Dimension(1920,1080));
    }

    @After("@register")
    public void afterScenarios() {
        homePage.closeBrowser();
    }

    @Given("^I receive an invite$")
    public void I_receive_an_invite() throws Throwable {
        adminPage = new AdminPage(driver, jsexec);
        adminPage.navigateToAdmin();
        adminPage.setEnvironmentPicker();
        inviteEmail = adminPage.setInviteEmailInput(adminPage.generateRandomEmail());
        mailinatorPage = adminPage.submitInviteButton();
    }

    @When("^Love and Try several interests$")
    public void Love_and_Try_several_interests() throws Throwable {
        welcomePage.beginOnBoarding();
        welcomePage.likeAndLoveInterests();
        interestsActivitiesPage = welcomePage.completeOnBoarding();
    }

    @Then("^registration is complete$")
    public void registration_is_complete() throws Throwable {
        assert interestsActivitiesPage.checkPageHeader().equals("All Activities");
    }

    @When("^I open my email account$")
    public void I_open_my_email_account() throws Throwable {
        mailinatorPage.navigateToMailinator(inviteEmail);
        mailinatorPage.openEmail();
        timelinePage = mailinatorPage.confirmEmailAddress();
    }

    @Then("^my email address is confirmed$")
    public void my_email_address_is_confirmed() throws Throwable {
        assert timelinePage.onPage();
    }




    @Given("^I register my email address$")
    public void I_register_my_email_address() throws Throwable {
        verificationPage = new VerificationPage(driver, jsexec);
        verificationPage.navigateToVerification();
        inviteEmail = verificationPage.setInviteEmailInput(verificationPage.generateRandomEmail());
        mailinatorPage = verificationPage.submitInviteButton();
    }

    @And("^I begin registration process$")
    public void I_begin_registration_process() throws Throwable {
        mailinatorPage.navigateToMailinator(inviteEmail);
        mailinatorPage.openEmail();
        registrationPage = mailinatorPage.beginRegistration();
    }

    @And("^I register$")
    public void I_register() throws Throwable {
        registrationPage.completeRegistrationForm(inviteEmail);

        welcomePage = registrationPage.submitRegistration();
    }
}
