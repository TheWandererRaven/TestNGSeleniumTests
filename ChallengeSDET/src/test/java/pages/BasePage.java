package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class BasePage {
    String url = "https://www.microsoft.com/es-mx";

    public WebDriver driver;


    @FindBy(id = "shellmenu_2")
    public WebElement navBar_WindowsButton;

    @FindBy(id = "search")
    public WebElement navBarSearchButton;

    @FindBy(id = "cli_shellHeaderSearchInput")
    public WebElement navBarSearchBox;

    @FindBy(id = "uhf-shopping-cart")
    public WebElement cartButton;

    @FindBy(css = "#uhf-shopping-cart > .shopping-cart-amount")
    public WebElement cartButton_itemsCount;

    //private final String cartButton_itemsCount_ByXpath = "//a[@id='uhf-shopping-cart']/span[@class='shopping-cart-amount']";

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToPage() {
        this.driver.get(this.url);
    }

    public void clickNavBarWindowsButton() {
        navBar_WindowsButton.click();
    }

    public void clickNavBarSearchButton() {
        navBarSearchButton.click();
    }

    public void searchTextInSearchBox(String textInput) {
        navBarSearchButton.click();
        new WebDriverWait(this.driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOf(navBarSearchBox));
        navBarSearchBox.sendKeys(textInput);
        navBarSearchBox.sendKeys(Keys.ENTER);
    }

    public void verifyCartButtonCountMatches(int itemsCount) {
        try {
            String locator = "//a[@id='uhf-shopping-cart']/span[@class='shopping-cart-amount'][text()='" + itemsCount + "']";
            new WebDriverWait(this.driver, Duration.ofSeconds(15)).until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))
            );
        } catch (TimeoutException e) {
            Assert.fail("Cart Button didn't updated with new item count.");
        }
    }
}
