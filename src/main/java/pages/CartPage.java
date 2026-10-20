package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebElement;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By cartItems = By.className("cart_item");
    private By itemNames = By.className("inventory_item_name");
    private By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public int getItemCount() {
        wait.until(ExpectedConditions.urlContains("cart"));
        return driver.findElements(cartItems).size();
    }

    public List<String> getItemNames() {
        wait.until(ExpectedConditions.urlContains("cart"));
        return driver.findElements(itemNames).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }
}