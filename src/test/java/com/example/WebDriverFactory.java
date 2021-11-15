package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.HashMap;

public class WebDriverFactory {
    static final Logger LOGGER = LoggerFactory.getLogger(WebDriverFactory.class);

    /**
     * This method currently creates a Chrome Web Driver.
     * However, it can be enhanced to create Chrome/IE/Firefox drivers.
     */
    public static WebDriver createWebDriver() {
        LOGGER.info("Running currently on Chrome driver");
        String[] arguments = new String[]{"--ignore-certificate-errors"};
        return setChromedriver(arguments);
    }

    private static ChromeDriver setChromedriver(String... arguments) {
        try {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver(generateChromeOptions(arguments));
        } catch (SessionNotCreatedException e) {
            throw e;
        }
    }

    @Nonnull
    private static ChromeOptions generateChromeOptions(@Nonnull String... arguments) {
        LOGGER.info("Generate Chrome Options");
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePref = new HashMap<>();
        options.setExperimentalOption("prefs", chromePref);
        options.addArguments(arguments);
        options.setAcceptInsecureCerts(true);
        return options;
    }
}
