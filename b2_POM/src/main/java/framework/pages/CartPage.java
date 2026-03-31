package framework.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.base.BasePage;

/**
 * Page giỏ hàng
 */
public class CartPage extends BasePage {

    @FindBy(className = "cart_item")
    private List<WebElement> items;

    @FindBy(css = ".cart_item button")
    private List<WebElement> removeButtons;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * ⚠️ QUAN TRỌNG: không throw exception khi rỗng
     */
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public CartPage removeFirstItem() {
        if (removeButtons != null && !removeButtons.isEmpty()) {
            waitAndClick(removeButtons.get(0));
        }
        return this;
    }
}