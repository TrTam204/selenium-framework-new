package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InventoryPage extends BasePage {

    @FindBy(css = ".inventory_item button")
    private List<WebElement> addButtons;

    @FindBy(className = "shopping_cart_link")
    private WebElement cart;

    public InventoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public InventoryPage addFirstItemToCart() {
        if (!addButtons.isEmpty()) {
            waitAndClick(addButtons.get(0));
        }
        return this;
    }

    public CartPage goToCart() {
        waitAndClick(cart);
        return new CartPage(driver);
    }

    public boolean isLoaded() {
        return isElementVisible(By.className("inventory_list"));
    }
}
