package pages;

import interfaces.BasicPageActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WindowsPage extends BasePage implements BasicPageActions {

    @FindBy(css = ".hubmain.windows > .padding-top-36.hero-item-video")
    public WebElement updateVideo;

    public WindowsPage(WebDriver driver) {
        super(driver);
        this.url = this.url + "/windows";
        waitForPageToLoad();
    }

    @Override
    public void waitForPageToLoad() {
        new WebDriverWait(this.driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOf(updateVideo));
    }
}
