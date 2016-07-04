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

public class ImagesSteps {

    WebDriver driver;
    JavascriptExecutor jsexec;
    HomePage homePage;
    LoginPage loginPage;
    RememberPage rememberPage;
    UploadMediaPage uploadMediaPage;
    TimelinePage timelinePage;
    PostDetailsPage postDetailsPage;
    PostCreateEditPage postCreateEditPage;
    EventsPage eventsPage;
    EventCreatePage eventCreatePage;
    EventDetailsPage eventDetailsPage;

    String mediaURL;
    String mediaName;
    String postName;
    String eventName;

    @Before("@images")
    public void initDriver() {
        System.setProperty("webdriver.chrome.driver","chromedriver");
        //System.setProperty("webdriver.chrome.logfile", "/opt/webdriver-tests/chromedriver.log");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        this.driver = new ChromeDriver(options);
        this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        this.driver.manage().window().setSize(new Dimension(1920,1080));
    }

    @After("@images")
    public void afterScenarios() {
        homePage.closeBrowser();
    }

    @Given("^I want to upload media$")
    public void I_want_to_upload_media() throws Throwable {
        homePage = new HomePage(driver, jsexec);
        homePage.navigateToHome();
        loginPage = homePage.openLoginPage();
        loginPage.completeLoginForm(globalConstant.accountOneLogin, globalConstant.accountOnePassword);
        timelinePage = loginPage.submitLogin();
    }

    @And("^I add an image with name \"([^\"]*)\"$")
    public void I_add_an_image_with_name(String arg1) throws Throwable {
        mediaURL = globalConstant.imageFolder + arg1;
        mediaName = arg1;
    }

    @When("^I upload media to Remember$")
    public void I_upload_media_to_Remember() throws Throwable {
        rememberPage = timelinePage.openRememberPage();
        uploadMediaPage = rememberPage.openMediaDialog();
        uploadMediaPage.uploadDefinedImage(mediaURL);
        uploadMediaPage.saveMedia();
    }

    @Then("^the image is uploaded successfully$")
    public void the_image_is_uploaded_successfully() throws Throwable {
        Thread.sleep(1500);
        assert rememberPage.verifyMediaUploadSuccess(mediaName);
    }

    @When("^I create the post$")
    public void I_create_the_post() throws Throwable {
        postCreateEditPage = timelinePage.openCreatePostDialog();
        postName = postCreateEditPage.postAddTitle();
        uploadMediaPage = postCreateEditPage.postAddImageViaUpload();

        uploadMediaPage.uploadDefinedImage(mediaURL);
        uploadMediaPage.saveMedia();
        postCreateEditPage.postAddInterest("Football");
    }

    @Then("^my post with image is created successfully$")
    public void my_post_with_image_is_created_successfully() throws Throwable {
        postDetailsPage = postCreateEditPage.savePost();
        assert postDetailsPage.verifyPostContent("Media", true);
    }

    @When("^I upload the image to the event$")
    public void I_upload_the_image_to_the_event() throws Throwable {
        eventsPage = timelinePage.openEventsPage();
        eventCreatePage = eventsPage.openCreateEventDialog();
        eventName = eventCreatePage.enterEventDetails(mediaName);
       // eventDetailsPage = eventCreatePage.planEventWithoutDate();
        
        eventDetailsPage.openEventMediaSection();
        uploadMediaPage = eventDetailsPage.openMediaDialog();
        uploadMediaPage.uploadDefinedImage(mediaURL);
        uploadMediaPage.saveMedia();
    }

    @And("^I remove an image from the event$")
    public void I_remove_an_image_from_the_event() throws Throwable {
        eventDetailsPage.removeImageFromEvent();
    }

    @When("^I update my events image$")
    public void I_update_my_events_image() throws Throwable {
        eventsPage = timelinePage.openEventsPage();
        eventCreatePage = eventsPage.openCreateEventDialog();
        eventName = eventCreatePage.enterEventDetails(mediaName);
        uploadMediaPage = eventCreatePage.openChooseCoverButton();

        uploadMediaPage.uploadDefinedImage(mediaURL);
        uploadMediaPage.saveMedia();
        eventCreatePage = uploadMediaPage.cropEventMedia();
    }

    @Then("^my event is updated successfully$")
    public void my_event_is_updated_successfully() throws Throwable {
        //eventDetailsPage = eventCreatePage.saveEvent();
        //assert !eventDetailsPage.verifyCoverImageNotDefault();
        assert eventDetailsPage.verifyNoMediaItemsPresent();
    }


    @Then("^the image is added successfully to the event$")
    public void the_image_is_added_successfully_to_the_event() throws Throwable {
        Thread.sleep(1500);
        assert eventDetailsPage.verifyMediaUploadSuccess(mediaName);
    }

    @When("^I add an image that is larger than (\\d+)MB$")
    public void I_add_an_image_that_is_larger_than_MB(int arg1) throws Throwable {
        mediaURL = globalConstant.imageFolder+globalConstant.largeMBImageName;

        rememberPage = timelinePage.openRememberPage();
        uploadMediaPage = rememberPage.openMediaDialog();
        uploadMediaPage.uploadDefinedImage(mediaURL);
    }

    @When("^I add an image that is (\\d+)px x (\\d+)px$")
    public void I_add_an_image_that_is_px_x_px(int arg1, int arg2) throws Throwable {
        if (arg1 ==2){
            mediaURL = globalConstant.imageFolder+globalConstant.smallDimensionsImageName;
            mediaName = globalConstant.smallDimensionsImageName;
        }
        else {
            mediaURL = globalConstant.imageFolder+globalConstant.largeDimensionsImageName;
            mediaName = globalConstant.largeDimensionsImageName;
        }

        rememberPage = timelinePage.openRememberPage();
        uploadMediaPage = rememberPage.openMediaDialog();
        uploadMediaPage.uploadDefinedImage(mediaURL);
        uploadMediaPage.saveMedia();
    }

    @Then("^I see a message informing me that the file type is not supported$")
    public void I_see_a_message_informing_me_that_the_file_type_is_not_supported() throws Throwable {
        rememberPage = timelinePage.openRememberPage();
        uploadMediaPage = rememberPage.openMediaDialog();
        uploadMediaPage.uploadDefinedImage(mediaURL);
        assert uploadMediaPage.verifyErrorAlert();
    }

    @When("^I add an image that is corrupted$")
    public void I_add_an_image_that_is_corrupted() throws Throwable {
        mediaURL = globalConstant.imageFolder+globalConstant.corruptedImageName;
        mediaName = globalConstant.corruptedImageName;

        rememberPage = timelinePage.openRememberPage();
        uploadMediaPage = rememberPage.openMediaDialog();
        uploadMediaPage.uploadDefinedImage(mediaURL);
        uploadMediaPage.saveMedia();
    }

    @When("^I add an image with filename “X.jpg”$")
    public void I_add_an_image_with_filename_X_jpg() throws Throwable {
        mediaURL = globalConstant.imageFolder+globalConstant.basicTestImageName;
        mediaName = globalConstant.basicTestImageName;

        rememberPage = timelinePage.openRememberPage();
        uploadMediaPage = rememberPage.openMediaDialog();
        uploadMediaPage.uploadDefinedImage(mediaURL);
        uploadMediaPage.saveMedia();
    }

    @When("^I add an image with non-alphanumeric characters in the filename$")
    public void I_add_an_image_with_non_alphanumeric_characters_in_the_filename() throws Throwable {
        mediaURL = globalConstant.imageFolder+globalConstant.nonAlphanumericImageName;
        mediaName = globalConstant.nonAlphanumericImageName;

        rememberPage = timelinePage.openRememberPage();
        uploadMediaPage = rememberPage.openMediaDialog();
        uploadMediaPage.uploadDefinedImage(mediaURL);
        uploadMediaPage.saveMedia();
    }

    @Then("^the image name is the title$")
    public void the_image_name_is_the_title() throws Throwable {
        // Wait for images to show up in the site
        Thread.sleep(1500);
        rememberPage.openEditImageDialog();
        assert rememberPage.verifyImageIsCorrect(mediaName);
    }

    @Then("^I see a message informing me that the image is too big$")
    public void I_see_a_message_informing_me_that_the_image_is_too_big() throws Throwable {
        assert uploadMediaPage.verifyErrorAlert();
    }
}
