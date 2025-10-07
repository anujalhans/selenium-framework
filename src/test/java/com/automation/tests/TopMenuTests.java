package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Set;

@Listeners({com.automation.listeners.TestListener.class})
public class TopMenuTests extends BaseTest {
     
    //This test is created to validate the title and url contains "motorcycle"
    //Scenario is to click on the "Motorcycles" link in the top menu and validate the title and url contains "motorcycle"
    @Test(groups = {"smoke"})
    public void clickMotorcyclesAndValidateUrlTitle() {
        HomePage home = new HomePage();
        home.clickAcceptCookies();
        home.clickMotorcycles();
        String title = getDriver().getTitle();
        String url = getDriver().getCurrentUrl();
        Assert.assertTrue(title.toLowerCase().contains("motorcycle") || url.toLowerCase().contains("motorcycle"),
                "Expected title or URL to contain the 'motorcycle'. Title: " + title + " URL: " + url);
    }
    
    //This test is created to validate the new window is opened and the title and url contains "locate"
    //Scenario is to click on the "Locate Us" link in the top menu and validate the new window is opened and the title and url contains "locate"
    @Test(groups = {"smoke"})
    public void clickLocateUsAndValidateNewWindowUrlTitle() {
        String original = getDriver().getWindowHandle();
        HomePage home = new HomePage();
        home.clickAcceptCookies();
        home.clickLocateUs();

        Set<String> handles = getDriver().getWindowHandles();
        for (String h : handles) {
            if (!h.equals(original)) {
                getDriver().switchTo().window(h);
                break;
            }
        }
        String title = getDriver().getTitle();
        String url = getDriver().getCurrentUrl();
        Assert.assertTrue(title.toLowerCase().contains("locate") || url.toLowerCase().contains("locate"),
                "Expected title or URL to contain the 'locate'. Title: " + title + " URL: " + url);
    }
}



