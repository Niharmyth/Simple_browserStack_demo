package com.example.util;

import org.openqa.selenium.WebDriver;

public class SeleniumSession {

    private static WebDriver webDriver = null;

    public static void startSession() {
        if (webDriver == null) {
            webDriver = WebDriverFactory.createWebDriver();
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
