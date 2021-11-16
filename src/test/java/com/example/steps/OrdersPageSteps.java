package com.example.steps;

import com.example.util.Utility;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static com.example.util.SeleniumSession.webDriver;

public class OrdersPageSteps {
    Utility util = new Utility();

    @And("I verify my Order")
    public void iVerifyMyOrder() {
        WebDriverWait wait = new WebDriverWait(webDriver(), 10);

        String confirmMessage = "Your Order has been successfully placed.";
        WebElement confirmMessageId = webDriver().findElement(By.id("confirmation-message"));
        WebElement downloadOrder = webDriver().findElement(By.id("downloadpdf"));
        wait.until(ExpectedConditions.textToBePresentInElement(confirmMessageId, confirmMessage));

        // verify Order Summary Cart
        util.verifyOrderSummary();

        downloadOrder.click();
    }

    @Then("I should see orders in the list")
    public void iShouldSeeOrdersInTheList() {
        util.waitForElementToBePresent(By.cssSelector(".shipment"));

        List<WebElement> orderItems = webDriver().findElements(By.cssSelector(".orders-listing .order"));
        Assert.assertEquals(orderItems.size(), 1);

        // Verify API response
        verifyAPIResponse();
    }

    /**
     * The order page has issues
     * API response always returns 404 instead of 200
     */
    private void verifyAPIResponse() {
        SoftAssert softAssert = new SoftAssert();
        String productAPI = "https://bstackdemo.com/api/orders?userName=demouser";
        Response response = RestAssured.get(productAPI);

        int statusCode = response.getStatusCode();
        softAssert.assertEquals(statusCode, 200);
        softAssert.assertAll();

        System.err.println("Expected failure here !!!");
    }

}
