package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({com.automation.listeners.TestListener.class})
public class HomePageTests extends BaseTest {

    @Test(groups = {"smoke"})
    public void validateHomePageTitle() {
        HomePage home = new HomePage();
        home.clickAcceptCookies();
        String title = home.getTitle();
        Assert.assertTrue(title != null && !title.isBlank(), "Page title should not be blank");
    }
}



