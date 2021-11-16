package com.example.steps;

import com.example.util.Utility;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import io.cucumber.java.en.And;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nonnull;

import static com.example.util.SeleniumSession.webDriver;

public class ProductsPageSteps {
    Utility util = new Utility();
    By bag_qty = By.cssSelector(".bag__quantity");
    By bag_close = By.cssSelector(".float-cart__close-btn");
    By bag_button = By.cssSelector(".bag");
    By bagOpen = By.cssSelector(".float-cart--open");
    By buyButton = By.className("buy-btn");

    @And("I add {string} to the cart")
    public void iAddToTheCart(@Nonnull String product) {

        // Verify API response
        verifyAPIResponse();

        By addToCart = By.xpath("//p[text() = '" + product + "']/../div[@class = 'shelf-item__buy-btn']");
        util.waitAndClick(addToCart);
    }

    @And("I click on Buy button")
    public void iClickOnBuyButton() {
        WebDriverWait wait = new WebDriverWait(webDriver(), 10);

        util.waitForTextToBePresentInElement(bag_qty, "1");
        wait.until(ExpectedConditions.visibilityOf(webDriver().findElement(bag_close)));
        util.waitAndClick(bag_button);

        wait.until(ExpectedConditions.visibilityOf(webDriver().findElement(bagOpen)));

        // Verify Bag Contents
        String productTitle = webDriver().findElement(By.cssSelector("p.title")).getText();
        String subTotal = webDriver().findElement(By.cssSelector("p.sub-price__val")).getText();
        Assert.assertTrue(productTitle.equalsIgnoreCase("iPhone 11"));
        Assert.assertTrue(subTotal.equalsIgnoreCase("$ 599.00"));

        wait.until(ExpectedConditions.elementToBeClickable(webDriver().findElement(buyButton))).click();
    }

    private void verifyAPIResponse() {
        String productAPI = "https://bstackdemo.com/api/products?userName=demouser";
        String expectedResponse = "{\"products\":[{\"availableSizes\":[\"Apple\"],\"currencyFormat\":\"$\",\"currencyId\":\"USD\",\"description\":\"iPhone 12\",\"id\":1,\"installments\":9,\"isFav\":false,\"price\":799,\"sku\":\"iPhone12-device-info.png\",\"title\":\"iPhone 12\"},{\"availableSizes\":[\"Apple\"],\"currencyFormat\":\"$\",\"currencyId\":\"USD\",\"description\":\"iPhone 12 Mini\",\"id\":2,\"installments\":9,\"isFav\":false,\"price\":699,\"sku\":\"iPhone12-device-info.png\",\"title\":\"iPhone 12 Mini\"},{\"availableSizes\":[\"Apple\"],\"currencyFormat\":\"$\",\"currencyId\":\"USD\",\"description\":\"iPhone 12 Pro Max\",\"id\":3,\"installments\":9,\"isFav\":false,\"price\":1099,\"sku\":\"iPhone12-device-info.png\",\"title\":\"iPhone 12 Pro Max\"},{\"availableSizes\":[\"Apple\"],\"currencyFormat\":\"$\",\"currencyId\":\"USD\",\"description\":\"iPhone 12 Pro \",\"id\":4,\"installments\":5,\"isFav\":false,\"price\":999,\"sku\":\"iPhone12Pro-device-info.png\",\"title\":\"iPhone 12 Pro\"},{\"availableSizes\":[\"Apple\"],\"currencyFormat\":\"$\",\"currencyId\":\"USD\",\"description\":\"iPhone 11\",\"id\":5,\"installments\":8,\"isFav\":false,\"price\":599,\"sku\":\"iPhone11-device-info.png\",\"title\":\"iPhone 11\"},{\"availableSizes\":[\"Apple\"],\"currencyFormat\":\"$\",\"currencyId\":\"USD\",\"description\":\"iPhone 11 Pro\",\"id\":6,\"installments\":3,\"isFav\":false,\"price\":699,\"sku\":\"infocardiphone11Pro.png\",\"title\":\"iPhone 11 Pro\"},{\"availableSizes\":[\"Apple\"],\"currencyFormat\":\"$\",\"currencyId\":\"USD\",\"description\":\"iPhone XS\",\"id\":7,\"installments\":4,\"isFav\":false,\"price\":549,\"sku\":\"infocard.png\",\"title\":\"iPhone XS\"},{\"availableSizes\":[\"Apple\"],\"currencyFormat\":\"$\",\"currencyId\":\"USD\",\"description\":\"iPhone XR\",\"id\":8,\"installments\":5,\"isFav\":false,\"price\":499,\"sku\":\"infoiphoneXR.png\",\"title\":\"iPhone XR\"},{\"availableSizes\":[\"Apple\"],\"currencyFormat\":\"$\",\"currencyId\":\"USD\",\"description\":\"iPhone XS Max\",\"id\":9,\"installments\":7,\"isFav\":false,\"price\":649,\"sku\":\"infocard.png\",\"title\":\"iPhone XS Max\"},{\"availableSizes\":[\"Samsung\"],\"currencyFormat\":\"$\",\"currencyId\":\"USD\",\"description\":\"Galaxy S20\",\"id\":10,\"installments\":9,\"isFav\":false,\"price\":999,\"sku\":\"samsung-S20-device-info.png\",\"title\":\"Galaxy S20\"},{\"availableSizes\":[\"Samsung\"],\"currencyFormat\":\"$\",\"currencyId\":\"USD\",\"description\":\"Galaxy S20+\",\"id\":11,\"installments\":9,\"isFav\":false,\"price\":1199,\"sku\":\"samsung-S20+-device-info.png\",\"title\":\"Galaxy S20+\"},{\"availableSizes\":[\"Samsung\"],\"currencyFormat\":\"$\",\"currencyId\":\"USD\",\"description\":\"Galaxy S20 Ultra\",\"id\":12,\"installments\":12,\"isFav\":false,\"price\":1399,\"sku\":\"samsung-S20Ultra-device-info.png\",\"title\":\"Galaxy S20 Ultra\"},{\"availableSizes\":[\"Samsung\"],\"currencyFormat\":\"$\",\"currencyId\":\"USD\",\"description\":\"Galaxy S10\",\"id\":13,\"installments\":5,\"isFav\":false,\"price\":899,\"sku\":\"samsung-S10-device-info.png\",\"title\":\"Galaxy S10\"},{\"availableSizes\":[\"Samsung\"],\"currencyFormat\":\"$\",\"currencyId\":\"USD\",\"description\":\"Galaxy S9\",\"id\":14,\"installments\":5,\"isFav\":false,\"price\":699,\"sku\":\"samsung-s9-device-info.png\",\"title\":\"Galaxy S9\"},{\"availableSizes\":[\"Samsung\"],\"currencyFormat\":\"$\",\"currencyId\":\"USD\",\"description\":\"Galaxy Note 20\",\"id\":15,\"installments\":7,\"isFav\":false,\"price\":999,\"sku\":\"Note20-device-info.png\",\"title\":\"Galaxy Note 20\"},{\"availableSizes\":[\"Samsung\"],\"currencyFormat\":\"$\",\"currencyId\":\"USD\",\"description\":\"Galaxy Note 20 Ultra\",\"id\":16,\"installments\":7,\"isFav\":false,\"price\":1299,\"sku\":\"Note20Ultra-device-info.png\",\"title\":\"Galaxy Note 20 Ultra\"},{\"availableSizes\":[\"Google\"],\"currencyFormat\":\"$\",\"currencyId\":\"USD\",\"description\":\"Pixel 4\",\"id\":17,\"installments\":5,\"isFav\":false,\"price\":899,\"sku\":\"GooglePixel4-device-info.png\",\"title\":\"Pixel 4\"},{\"availableSizes\":[\"Google\"],\"currencyFormat\":\"$\",\"currencyId\":\"USD\",\"description\":\"Pixel 3\",\"id\":18,\"installments\":5,\"isFav\":false,\"price\":599,\"sku\":\"GooglePixel3-device-info.png\",\"title\":\"Pixel 3\"},{\"availableSizes\":[\"Google\"],\"currencyFormat\":\"$\",\"currencyId\":\"USD\",\"description\":\"Pixel 2\",\"id\":19,\"installments\":3,\"isFav\":false,\"price\":399,\"sku\":\"device-info.jpg\",\"title\":\"Pixel 2\"},{\"availableSizes\":[\"OnePlus\"],\"currencyFormat\":\"$\",\"currencyId\":\"USD\",\"description\":\"One Plus 8\",\"id\":20,\"installments\":6,\"isFav\":false,\"price\":799,\"sku\":\"OnePlus8-device-info.png\",\"title\":\"One Plus 8\"},{\"availableSizes\":[\"OnePlus\"],\"currencyFormat\":\"$\",\"currencyId\":\"USD\",\"description\":\"One Plus 8T\",\"id\":21,\"installments\":6,\"isFav\":false,\"price\":899,\"sku\":\"OnePlus8-device-info.png\",\"title\":\"One Plus 8T\"},{\"availableSizes\":[\"OnePlus\"],\"currencyFormat\":\"$\",\"currencyId\":\"USD\",\"description\":\"One Plus 8 Pro\",\"id\":22,\"installments\":6,\"isFav\":false,\"price\":899,\"sku\":\"OnePlus8-device-info.png\",\"title\":\"One Plus 8 Pro\"},{\"availableSizes\":[\"OnePlus\"],\"currencyFormat\":\"$\",\"currencyId\":\"USD\",\"description\":\"One Plus 7T\",\"id\":23,\"installments\":6,\"isFav\":false,\"price\":599,\"sku\":\"OnePlus7T-DeviceInfo.png\",\"title\":\"One Plus 7T\"},{\"availableSizes\":[\"OnePlus\"],\"currencyFormat\":\"$\",\"currencyId\":\"USD\",\"description\":\"One Plus 7\",\"id\":24,\"installments\":3,\"isFav\":false,\"price\":499,\"sku\":\"oneplus7device-info.png\",\"title\":\"One Plus 7\"},{\"availableSizes\":[\"OnePlus\"],\"currencyFormat\":\"$\",\"currencyId\":\"USD\",\"description\":\"One Plus 6T\",\"id\":25,\"installments\":3,\"isFav\":false,\"price\":429,\"sku\":\"Oneplus6T_infocard.png\",\"title\":\"One Plus 6T\"}]}";
        Response response = RestAssured.get(productAPI);
        String responseStr = response.getBody().asString();

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(expectedResponse, responseStr);

        // Verify the product I'm trying to add exists in the API response
        // Very crude way of verifying
        Assert.assertTrue(responseStr.contains("iPhone 11"));
    }

}
