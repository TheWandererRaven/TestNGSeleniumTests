package pages;

import interfaces.BasicPageActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class ShoppingCartPage extends BasePage implements BasicPageActions {

    @FindBy(css = "div[class^='headerContainer--'] > a")
    public WebElement continueShoppingButton;

    @FindBy(css = "div[class^='lineItemRow'] > div > div > div[class^='itemDetailsAndLegal'] > div[class^='legalAndStatementContainer'] > div > button[aria-label^='Quitar']")
    public List<WebElement> removeItemButtons;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
        this.url = this.url + "/store/cart";
        waitForPageToLoad();
    }

    @Override
    public void waitForPageToLoad() {
        new WebDriverWait(this.driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOf(continueShoppingButton));
    }

    public void removeItemFromList(int itemIndex) {
        new WebDriverWait(this.driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfAllElements(removeItemButtons));
        if(removeItemButtons.size() >= itemIndex)
            removeItemButtons.get(itemIndex - 1).click();
        else Assert.fail("Requested item to be removed with index [" + itemIndex + "] exceeds the number of current items in the cart [" + removeItemButtons.size() + "]");
    }
}

