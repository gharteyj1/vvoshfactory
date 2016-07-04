package com.utility;

import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.TimelinePage;

/**
 * Created by user on 22/06/2016.
 */
public class manageSessions {

    public static TimelinePage logoutAndLogin(String loginUser, String loginPass, TimelinePage timelinePage, LoginPage loginPage, HomePage homePage) throws InterruptedException {
        timelinePage.openBurgerMenu();
        homePage = timelinePage.logOut();

        loginPage = homePage.openLoginPage();

        loginPage.completeLoginForm(loginUser, loginPass);
        timelinePage = loginPage.submitLogin();
        return timelinePage;
    }
}
