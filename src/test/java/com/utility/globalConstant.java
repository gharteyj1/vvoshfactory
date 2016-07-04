package com.utility;

public class globalConstant {

    //Page URL's
    public static final String environ = System.getProperty("environ");
    public static final String URL = "https://vvoosh:Discover8@"+environ+".vvoosh.com";

    public static final String adminURL = "https://vvoosh:Discover8@admin.vvoosh.com/#/registration-invites";
    public static final String emailURL = "https://mailinator.com/inbox2.jsp?public_to=";
    public static final String registrationCode = "/c/72xJG4";
    public static final String onboardingURL = globalConstant.URL + "/welcome";
    public static final String footballInterestPage = globalConstant.URL + "/activity/152/football";

    //Interest Names
    public static final String interestFilm ="Film";
    public static final String interestCooking ="Cooking";
    public static final String interestFitness ="Fitness";
    public static final String interestIndieMusic ="Indie Music";
    public static final String interestElectronicMusic ="Electronic Music";

    //Login
    public static final String invalidLogin = "nobody@nowhere.com";
    public static final String invalidPassword = "invalid";

    //Registration
    public static final String registerFirstname = "Tester";
    public static final String registerSurname = "McTesty";
    public static final String registerPassword = "password12";
    public static final String registerLocation = "London, United Kingdom";

    //accounts Friends
    public static final String accountOneLogin = "waqas1@mailinator.com";
    public static final String accountOnePassword = "password12";
    public static final String accountOneName = "WaqasOne Khan";
    public static final String accountTwoLogin = "waqas2@mailinator.com";
    public static final String accountTwoPassword = "password12";
    public static final String accountTwoName = "WaqasTwo Khan";

    //accounts non friends
    public static final String accountThreeLogin = "waqas3@mailinator.com";
    public static final String accountThreePassword = "password12";
    public static final String accountThreeName = "WaqasThree Khan";

    //social accounts
    public static final String facebookLogin = "mierdecillavolante3@hotmail.com";
    public static final String facebookPassword = "miervol";
    public static final String googleLogin = "vvooshqa1@gmail.com";
    public static final String googlePassword = "64PaulStreet";

    //image locations
    public static final String imageFolder = System.getProperty("user.dir") + "/src/test/resources/images/";
    public static final String basicTestImageName = "auto_test.png";
    public static final String largeDimensionsImageName = "10000by5000.jpg";
    public static final String smallDimensionsImageName = "2by2.png";
    public static final String corruptedImageName = "corrupted.png";
    public static final String nonAlphanumericImageName = "!@£$%^&*()_+.png";
    public static final String largeMBImageName = "21MB_image.jpg";
    public static final String profileImageName = "generic-profile.jpg";

    //events
    public static final String defaultCoverImage = "background-image:url('https://cdn.vvoosh.com/public/assets/img/hero-external-event-mask.png')";

}
