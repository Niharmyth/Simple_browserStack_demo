package com.example.steps;

import com.example.util.Utility;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nonnull;
import java.util.concurrent.TimeUnit;

import static com.example.util.SeleniumSession.*;
import static com.example.util.Utility.maximizeBrowser;

public class CommonSteps {
    WebDriverWait wait;
    static Utility util = new Utility();
    String url = "https://bstackdemo.com/";

    @Before
    public void setup() {
        startSession();
        wait = new WebDriverWait(webDriver(), 20);
        webDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Given("I navigate to Website")
    public void iNavigateToWebsite() {
        webDriver().navigate().to(url);
        maximizeBrowser();
        util.verifyTitle("StackDemo");
    }

    @Then("I SignIn with username as {string} and password as {string}")
    public void iSignInWithUsernameAsAndPasswordAs(@Nonnull String username, @Nonnull String password) throws Throwable {
        By loginForm = By.cssSelector(".Modal_modal__3I0HK form");
        webDriver().findElement(By.cssSelector("a#signin")).click();
        util.waitForElementToBePresent(loginForm);
        webDriver().findElement(By.cssSelector("#username input")).sendKeys(username + Keys.ENTER);
        webDriver().findElement(By.cssSelector("#password input")).sendKeys(password + Keys.ENTER);
        webDriver().findElement(By.id("login-btn")).click();

        util.waitForSpinnerToDisappear();
        i_should_login_successfully();
    }

    @Then("^I should login successfully$")
    public static void i_should_login_successfully() {
        By logout = By.cssSelector("a#logout");
        util.waitForElementToBePresent(logout);
    }

    @And("I click on Continue Shopping button")
    public void iClickOnContinueShoppingButton() {
        webDriver().findElement(By.cssSelector(".continueButtonContainer button")).click();
    }

    @And("I click on {string} link")
    public void iClickOnLink(String link) {
        By orderLink = By.cssSelector("a#" + link);
        util.waitAndClick(orderLink);
        util.verifyTitle("StackDemo");
    }

    @After
    public void after() {
        endSession();
    }
}
