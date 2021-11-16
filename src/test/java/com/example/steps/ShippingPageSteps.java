package com.example.steps;

import com.example.util.Utility;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;

import static com.example.util.SeleniumSession.webDriver;

public class ShippingPageSteps {
    Utility util = new Utility();
    By firstNameInput = By.id("firstNameInput");
    By lastNameInput = By.id("lastNameInput");
    By addressLine1Input = By.id("addressLine1Input");
    By provinceInput = By.id("provinceInput");
    By postCodeInput = By.id("postCodeInput");
    By checkoutBtn = By.id("checkout-shipping-continue");

    @And("I provide shipping details {string}, {string}, {string}, {string} and {string}")
    public void iProvideShippingDetailsAnd(String firstName, String lastName, String address, String province, String postcode) {
        util.waitForElementToBePresent(firstNameInput);

        webDriver().findElement(firstNameInput).sendKeys(firstName);
        webDriver().findElement(lastNameInput).sendKeys(lastName);
        webDriver().findElement(addressLine1Input).sendKeys(address);
        webDriver().findElement(provinceInput).sendKeys(province);
        webDriver().findElement(postCodeInput).sendKeys(postcode);

        // verify Order Summary Cart
        util.verifyOrderSummary();

        webDriver().findElement(checkoutBtn).click();
    }


}
