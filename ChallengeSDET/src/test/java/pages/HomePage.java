package pages;

import interfaces.BasicPageActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage implements BasicPageActions {

    @FindBy(id = "StoreLocations-HighlightHero-5qxe3qz")
    public WebElement mainCarousel;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void navigateToPage() {
        this.driver.get(this.url);
        this.waitForPageToLoad();
    }

    @Override
    public void waitForPageToLoad() {
        new WebDriverWait(this.driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOf(mainCarousel));
    }
}
