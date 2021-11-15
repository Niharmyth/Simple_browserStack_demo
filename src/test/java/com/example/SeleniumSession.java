package com.example;

import org.openqa.selenium.WebDriver;

public class SeleniumSession {

    private static WebDriver webDriver = null;

    public static void startSession() {
        if (webDriver == null) {
            webDriver = WebDriverFactory.createWebDriver();
            //$.driver().use(webDriver);
        }
    }

    public static void endSession() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }

    public static WebDriver webDriver() {
        startSession();
        return webDriver;
    }

}
