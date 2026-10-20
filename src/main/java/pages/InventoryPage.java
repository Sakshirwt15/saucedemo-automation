package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InventoryPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By pageTitle = By.className("title");
    private By addToCartBackpack = By.id("add-to-cart-sauce-labs-backpack");
    private By addToCartBikeLight = By.id("add-to-cart-sauce-labs-bike-light");
    private By removeBackpack = By.id("remove-sauce-labs-backpack");
    private By cartBadge = By.className("shopping_cart_badge");
    private By cartIcon = By.className("shopping_cart_link");


    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getPageTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).getText();
    }

    public void addBackpackToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBackpack)).click();
    }

    public void addBikeLightToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBikeLight)).click();
    }

    public void removeBackpackFromCart() {
        wait.until(ExpectedConditions.elementToBeClickable(removeBackpack)).click();
    }

    public String getCartCount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge)).getText();
    }

    public void goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
    }

    public boolean isCartBadgeDisplayed() {
        return !driver.findElements(cartBadge).isEmpty();
    }
}