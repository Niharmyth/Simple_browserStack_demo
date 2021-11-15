package com.example;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.example.SeleniumSession.*;

public class CommonSteps{
    protected WebDriverWait wait;
    String url = "https://bstackdemo.com/";

    @Before
    public void setup() {
        startSession();
       // webDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        wait = new WebDriverWait(webDriver(), 20);
    }

    protected void waitAndClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected void waitForTextToBePresentInElement(WebElement element, String text) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    protected void waitForElementToBePresent(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    @Given("I navigate to Website")
    public void iNavigateToWebsite() {
//        startSession();
        webDriver().navigate().to(url);
        webDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @After
    public void after() {
        endSession();
    }

    @Then("I SignIn with username as {string} and password as {string}")
    public void iSignInWithUsernameAsAndPasswordAs(String username, String password) {
      webDriver().findElement(By.cssSelector("a#signin")).click();
        WebElement loginForm =  webDriver().findElement(By.cssSelector(".Modal_modal__3I0HK form"));
        waitForElementToBePresent(loginForm);
        webDriver().findElement(By.cssSelector("#username input")).sendKeys(username + Keys.ENTER);
        webDriver().findElement(By.cssSelector("#password input")).sendKeys(password + Keys.ENTER);
        webDriver().findElement(By.id("login-btn")).click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".spinner")));
    }

    @And("I add {string} to the cart")
    public void iAddToTheCart(String product) {
        waitAndClick(webDriver().findElement(By.xpath("//p[text() = '" + product + "']/../div[@class = 'shelf-item__buy-btn']")));
    }

    @And("I click on Buy button")
    public void iClickOnBuyButton() {
        WebElement bag_qty = webDriver().findElement(By.cssSelector(".bag__quantity"));
        WebElement bag_close = webDriver().findElement(By.cssSelector(".float-cart__close-btn"));
        WebElement bag_button = webDriver().findElement(By.cssSelector(".bag"));
        WebElement bagOpen = webDriver().findElement(By.cssSelector(".float-cart--open"));
        WebElement buyButton = webDriver().findElement(By.className("buy-btn"));
        waitForTextToBePresentInElement(bag_qty, "1");
        wait.until(ExpectedConditions.visibilityOf(bag_close));
        waitAndClick(bag_button);

        wait.until(ExpectedConditions.visibilityOf(bagOpen));
        wait.until(ExpectedConditions.elementToBeClickable(buyButton)).click();
    }

    @And("I provide shipping details {string}, {string}, {string}, {string} and {string}")
    public void iProvideShippingDetailsAnd(String arg0, String arg1, String arg2, String arg3, String arg4) {
        WebElement firstNameInput = webDriver().findElement(By.id("firstNameInput"));
        WebElement lastNameInput = webDriver().findElement(By.id("lastNameInput"));
        WebElement addressLine1Input = webDriver().findElement(By.id("addressLine1Input"));
        WebElement provinceInput = webDriver().findElement(By.id("provinceInput"));
        WebElement postCodeInput = webDriver().findElement(By.id("postCodeInput"));
        WebElement checkoutBtn = webDriver().findElement(By.id("checkout-shipping-continue"));

        waitForElementToBePresent(firstNameInput);
        firstNameInput.sendKeys("Nihar");
        lastNameInput.sendKeys("J");
        addressLine1Input.sendKeys("31 mesik");
        provinceInput.sendKeys("NSW");
        postCodeInput.sendKeys("2762");
        checkoutBtn.click();
    }

    @And("I verify my Order")
    public void iVerifyMyOrder() {
        String confirmMessage = "Your Order has been successfully placed.";
        WebElement confirmMessageId = webDriver().findElement(By.id("confirmation-message"));
        WebElement downloadOrder = webDriver().findElement(By.id("downloadpdf"));
        wait.until(ExpectedConditions.textToBePresentInElement(confirmMessageId, confirmMessage));
        downloadOrder.click();
    }

    @And("I click on Continue Shopping button")
    public void iClickOnContinueShoppingButton() {
        webDriver().findElement(By.cssSelector(".continueButtonContainer button")).click();
    }

    @And("I click on {string} link")
    public void iClickOnLink(String arg0) {
        waitAndClick(webDriver().findElement(By.cssSelector("a#orders")));
    }

    @Then("I should see orders in the list")
    public void iShouldSeeOrdersInTheList() {
        waitForElementToBePresent(webDriver().findElement(By.cssSelector(".shipment")));

        List<WebElement> orderItems = webDriver().findElements(By.cssSelector(".orders-listing .order"));
        Assert.assertEquals(orderItems.size(), 1);
    }
}
