package com.automation.pages;

import com.automation.base.DriverFactory;
import com.automation.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    private final WebDriver driver;
    //This is the top menu link for Motorcycles
    private final By topMenuMotorcycles = By.xpath("//a[@title='Motorcycles']//span[@class='desktop-title-text']");
    //This is the top menu link for Locate Us
    private final By topMenuLocateUs = By.xpath("//a[@title='Locate Us']//span[@class='desktop-title-text']");
    //This is the button to accept the cookies
    private final By acceptCookies = By.xpath("//button[contains(text(), 'Accept')]");

    public HomePage() {
        this.driver = DriverFactory.getDriver();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void clickMotorcycles() {
        WebElement link = WaitUtils.waitForClickable(topMenuMotorcycles);
        link.click();
    }

    public void clickLocateUs() {
        WebElement link = WaitUtils.waitForClickable(topMenuLocateUs);
        link.click();
    }

    public void clickAcceptCookies() {
        try {
        WebElement cookieButton = WaitUtils.waitForVisible(acceptCookies);
        cookieButton.click();
        System.out.println("Cookies accepted.");
    } catch (Exception e) {
        System.out.println("Cookies not found or already accepted.");
    }
    }
}



// comment added 1234