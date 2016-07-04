package com.pageSteps;

import com.pageObjects.*;
import com.utility.globalConstant;
import com.utility.switchWindows;
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

public class LoginSteps {

    WebDriver driver;
    JavascriptExecutor jsexec;
    HomePage homePage;
    LoginPage loginPage;
    TimelinePage timelinePage;
    FacebookLoginPage facebookLoginPage;
    GoogleLoginPage googleLoginPage;
    switchWindows switchWindow = new switchWindows();

    @Before("@login")
    public void initDriver() {
        System.setProperty("webdriver.chrome.driver","chromedriver");
        //System.setProperty("webdriver.chrome.logfile", "/opt/webdriver-tests/chromedriver.log");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        this.driver = new ChromeDriver(options);
        this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        this.driver.manage().window().setSize(new Dimension(1920,1080));
    }

    @After("@login")
    public void afterScenarios() {
        homePage.closeBrowser();
    }

    @Given("^I am not logged in$")
    public void I_am_not_logged_in() throws Throwable {
        homePage = new HomePage(driver, jsexec);
        homePage.navigateToHome();
    }

    @When("^I log in$")
    public void I_login() throws Throwable {
        loginPage = homePage.openLoginPage();
        loginPage.completeLoginForm(globalConstant.accountOneLogin, globalConstant.accountOnePassword);
        timelinePage = loginPage.submitLogin();
    }

    @Then("^I see the vVoosh Timeline$")
    public void I_see_the_vVoosh_Timeline() throws Throwable {
        assert timelinePage.onPage();
    }

    @When("^I log in with invalid credentials$")
    public void I_login_with_invalid_credentials() throws Throwable {
        loginPage = homePage.openLoginPage();
        loginPage.completeLoginForm(globalConstant.invalidLogin, globalConstant.invalidPassword);
    }

    @Then("^I see a login error$")
    public void I_see_a_login_error() throws Throwable {
        assert loginPage.checkLoginFailed();
    }

    @Given("^I am logged in$")
    public void I_am_logged_in() throws Throwable {
        homePage = new HomePage(driver, jsexec);
        homePage.navigateToHome();
        loginPage = homePage.openLoginPage();
        loginPage.completeLoginForm(globalConstant.accountOneLogin, globalConstant.accountOnePassword);
        timelinePage = loginPage.submitLogin();
    }

    @When("^I log out$")
    public void I_log_out() throws Throwable {
        timelinePage.openBurgerMenu();
        homePage = timelinePage.logOut();
    }

    @Then("^I see the vVoosh homepage$")
    public void I_see_the_vVoosh_homepage() throws Throwable {
        assert homePage.checkSlidesPresent();
    }

    @When("^I log in with my Facebook account$")
    public void I_log_in_with_my_Facebook_account() throws Throwable {
        loginPage = homePage.openLoginPage();
        facebookLoginPage = loginPage.openFacebookModal();

        String parentHandle = switchWindow.getSocialWindow(driver);
        facebookLoginPage.completeLoginForm(globalConstant.facebookLogin, globalConstant.facebookPassword);
        timelinePage = facebookLoginPage.submitLogin();
        switchWindow.getParentWindow(driver, parentHandle);
    }

    @When("^I log in with my Chrome account$")
    public void I_log_in_with_my_Chrome_account() throws Throwable {
        loginPage = homePage.openLoginPage();
        googleLoginPage = loginPage.openGoogleModal();

        String parentHandle = switchWindow.getSocialWindow(driver);
        googleLoginPage.completeLoginForm(globalConstant.googleLogin, globalConstant.googlePassword);
        timelinePage = googleLoginPage.submitLogin();
        switchWindow.getParentWindow(driver, parentHandle);
    }

}