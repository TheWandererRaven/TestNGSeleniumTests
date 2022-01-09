package pages;

import interfaces.BasicPageActions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class GameDetailsPage extends BasePage implements BasicPageActions {

    @FindBy(css = "img[class^='ProductDetailsHeader-module__productImage']")
    //@FindBy(css = "img[class^='ProductDetailsHeader-module__productImage___tT14m']")
    public WebElement productImage;

    @FindBy(css = "button[title$='al carro']")
    public WebElement addToCartButton;

    public GameDetailsPage(WebDriver driver) {
        super(driver);
        this.url = this.url + "/games/store";
        this.waitForPageToLoad();
    }

    @Override
    public void waitForPageToLoad() {
        new WebDriverWait(this.driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOf(addToCartButton));
    }

    public void clickAddToCartButton() {
        // TODO: Add validation to assess if the "Add to Cart" option is present as a button or if it is present as part
        // TODO: ...of the "..." button. This is because of the page responsiveness
        new WebDriverWait(this.driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOf(addToCartButton));
        addToCartButton.click();
    }

}
