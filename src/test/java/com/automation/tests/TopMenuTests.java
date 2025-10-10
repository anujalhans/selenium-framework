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
        //Accept the cookies
        home.clickAcceptCookies();
        //Click on the "Motorcycles" link in the top menu
        home.clickMotorcycles();
        //Validate the title and url contains "motorcycle"
        String title = getDriver().getTitle();
        //Validate the url contains "motorcycle"
        String url = getDriver().getCurrentUrl();
        //Validate the title and url contains "motorcycle"
        Assert.assertTrue(title.toLowerCase().contains("motorcycle") || url.toLowerCase().contains("motorcycle"),
                "Expected title or URL to contain the 'motorcycle'. Title: " + title + " URL: " + url);
    }
    
    //This test is created to validate the new window is opened and the title and url contains "locate"
    //Scenario is to click on the "Locate Us" link in the top menu and validate the new window is opened and the title and url contains "locate"
    @Test(groups = {"smoke"})
    public void clickLocateUsAndValidateNewWindowUrlTitle() {
        //Get the original window handle
        String original = getDriver().getWindowHandle();
        HomePage home = new HomePage();
        home.clickAcceptCookies();
        //Click on the "Locate Us" link in the top menu
        home.clickLocateUs();

        Set<String> handles = getDriver().getWindowHandles();
        //Loop through the window handles and switch to the new window
        for (String h : handles) {
            if (!h.equals(original)) {
                getDriver().switchTo().window(h);
                break;
            }
        }
        //Validate the title and url contains "locate"
        String title = getDriver().getTitle();
        //Validate the url contains "locate"
        String url = getDriver().getCurrentUrl();
        //Validate the title and url contains "locate"
        Assert.assertTrue(title.toLowerCase().contains("locate") || url.toLowerCase().contains("locate"),
                "Expected title or URL to contain the 'locate'. Title: " + title + " URL: " + url);
                //give one more assert to validate the new window is opened
        Assert.assertTrue(handles.size() > 1, "Expected new window to be opened");
    }

    //This test is created to validate the new window is opened and the title and url contains "support"
    //Scenario is to click on the "Support" link in the top menu and validate the new window is opened and the title and url contains "support"
    @Test(groups = {"smoke"})
    public void clickSupportAndValidateNewWindowUrlTitle() {
        //Get the original window handle
        String original = getDriver().getWindowHandle();
        HomePage home = new HomePage();
        home.clickAcceptCookies();
        //Click on the "Support" link in the top menu
        home.clickSupport();
        //Validate the title and url contains "support"
        String title = getDriver().getTitle();
        //Validate the url contains "support"
        String url = getDriver().getCurrentUrl();
        //Validate the title and url contains "support"
        Assert.assertTrue(title.toLowerCase().contains("support") || url.toLowerCase().contains("support"),
                "Expected title or URL to contain the 'support'. Title: " + title + " URL: " + url);
        //give one more assert to validate the new window is opened
        Assert.assertTrue(handles.size() > 1, "Expected new window to be opened");
    }
}



