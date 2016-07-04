package com.utility;

import org.openqa.selenium.By;

/**
 * Created by user on 01/12/2015.
 */
public class globalElement {

    public static final By eventsPageButton = By.linkText("Events");//By.xpath("//a[contains(@title, 'Events')]");
    public static final By rememberPageButton = By.xpath("//a[contains(@title, 'Remember')]");
    public static final By burgerMenuButton = By.xpath("//a[contains(@title, 'Account Menu')]");
    public static final By logoutButton = By.linkText("Logout");
    public static final By profilePageButton = By.linkText("Profile");
    public static final By hamburgerLogoutButton = By.cssSelector("#side-nav-main ul li a");

    public static final By timelinePageButton = By.xpath("//a[contains(@class, 'brand-container')]");

    public static final By friendsPageButton = By.xpath("//a[contains(@title, 'Friends')]");
    public static final By createPostButton = By.xpath("//*[contains(@class, 'js-create-post')]");
    public static final By searchButton = By.xpath("//input[contains(@class, 'js-search-input')]");
    public static final By searchInput = By.xpath("//input[contains(@class, 'js-global-search-input')]");
    public static final By chooseMediaButton = By.id("dropupload");
    public static final By uploadedMedia = By.xpath("//a[contains(@class, 'js-media-item-link')][1]");
    public static final By eventHeroTitle = By.xpath("//h1[contains(@class, 'truncate')]");

    public static final By notificationsButton = By.xpath("//a[contains(@title, 'Notifications')]");
    public static final By ignoreFriendRequestButton = By.xpath("//button[contains(@class, 'js-ignore-friend-request')]");
    public static final By acceptFriendRequestButton = By.xpath("//button[contains(@class, 'js-accept-friend-request')]");
    public static final By notificationFriendRequestText = By.xpath("//p[contains(@class, 'navbar-dropdown-item-lead')]");

    public static final By myEventList = By.xpath("//div[contains(@id, 'my-events')]//ul[contains(@class, 'upcoming-past-event-list')]/li");
    public static final By myProfileEventList = By.xpath("//div[contains(@id, 'myvoosh-upcoming-events')]//ul[contains(@class, 'row-events-list')]/li");


}
