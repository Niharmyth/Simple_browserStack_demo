package com.example.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.annotation.Nonnull;
import java.util.HashMap;

public class WebDriverFactory {

    public static WebDriver createWebDriver() {
        return createWebDriver("chrome");
    }

    public static WebDriver createWebDriver(@Nonnull String browserName) {
        String[] arguments = new String[]{"--ignore-certificate-errors"};
        switch (browserName) {
            case "chrome":
                return setChromedriver(arguments);
            default:
                throw new RuntimeException("Unsupported webdriver: " + browserName);
        }
    }

    private static ChromeDriver setChromedriver(@Nonnull String... arguments) {
        try {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver(generateChromeOptions(arguments));
        } catch (SessionNotCreatedException e) {
            throw e;
        }
    }

    @Nonnull
    private static ChromeOptions generateChromeOptions(@Nonnull String... arguments) {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePref = new HashMap<>();
        options.setExperimentalOption("prefs", chromePref);
        options.addArguments(arguments);
        options.setAcceptInsecureCerts(true);
        return options;
    }
}
