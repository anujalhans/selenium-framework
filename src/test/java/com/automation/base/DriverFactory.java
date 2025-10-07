package com.automation.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public final class DriverFactory {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverFactory() {}

    public static WebDriver getDriver() {
        return DRIVER.get();
    }

    public static void initDriver() {
        String browser = System.getProperty("browser", ConfigReader.get("browser"));
        boolean bsEnabled = ConfigReader.getBoolean("bs.enabled", false);

        WebDriver webDriver;
        if (bsEnabled) {
            webDriver = createBrowserStackDriver();
        } else {
            switch (browser.toLowerCase()) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions fopts = new FirefoxOptions();
                    webDriver = new FirefoxDriver(fopts);
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions eopts = new EdgeOptions();
                    webDriver = new EdgeDriver(eopts);
                    break;
                case "chrome":
                default:
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions copts = new ChromeOptions();
                    copts.addArguments("--start-maximized");
                    webDriver = new ChromeDriver(copts);
                    break;
            }
        }

        int implicit = ConfigReader.getInt("implicitTimeout", 0);
        int pageLoad = ConfigReader.getInt("pageLoadTimeout", 30);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicit));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoad));
        DRIVER.set(webDriver);
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }

    private static WebDriver createBrowserStackDriver() {
        String user = ConfigReader.get("bs.user");
        String key = ConfigReader.get("bs.key");
        String browserName = ConfigReader.get("bs.browserName");
        String browserVersion = ConfigReader.get("bs.browserVersion");
        String os = ConfigReader.get("bs.os");
        String osVersion = ConfigReader.get("bs.osVersion");
        String project = ConfigReader.get("bs.projectName");
        String build = ConfigReader.get("bs.buildName");
        boolean local = ConfigReader.getBoolean("bs.local", false);

        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("browserVersion", browserVersion);
        Map<String, Object> bsOptions = new HashMap<>();
        bsOptions.put("os", os);
        bsOptions.put("osVersion", osVersion);
        bsOptions.put("projectName", project);
        bsOptions.put("buildName", build);
        bsOptions.put("local", local);
        capabilities.setCapability("bstack:options", bsOptions);

        try {
            String url = "https://" + user + ":" + key + "@hub.browserstack.com/wd/hub";
            return new RemoteWebDriver(URI.create(url).toURL(), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid BrowserStack URL", e);
        }
    }
}



