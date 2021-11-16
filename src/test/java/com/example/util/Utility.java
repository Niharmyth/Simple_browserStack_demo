package com.example.util;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nonnull;

import static com.example.util.SeleniumSession.webDriver;

public class Utility {
    WebDriverWait wait;

    public Utility() {
        wait = new WebDriverWait(webDriver(), 20);
    }

    public void waitAndClick(@Nonnull By element) {
        waitForElementToBePresent(element);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void waitForTextToBePresentInElement(@Nonnull By element, @Nonnull String text) {
        wait.until(ExpectedConditions.textToBePresentInElement(webDriver().findElement(element), text));
    }

    public void waitForElementToBePresent(@Nonnull By element) {
        wait.until(ExpectedConditions.visibilityOf(webDriver().findElement(element)));
    }

    public void waitForSpinnerToDisappear() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".spinner")));
    }

    public void verifyTitle(String expectedTitle) {
        String currentTitle = webDriver().getTitle();
        Assert.assertTrue(currentTitle.contains(expectedTitle));
    }

    public static void maximizeBrowser() {
        webDriver().manage().window().maximize();
    }

    public void verifyOrderSummary() {
        String orderTitle = webDriver().findElement(By.cssSelector("h3.cart-title")).getText();
        String productTitle = webDriver().findElement(By.cssSelector("h5.product-title")).getText();
        String subTotal = webDriver().findElement(By.cssSelector(".cart-total .cart-priceItem-value > span")).getText();
        Assert.assertTrue(orderTitle.equalsIgnoreCase("Order Summary"));
        Assert.assertTrue(productTitle.equalsIgnoreCase("iPhone 11"));
        Assert.assertTrue(subTotal.contains("599"));
    }
}
