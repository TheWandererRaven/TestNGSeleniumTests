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

public class SearchResultsPage extends BasePage implements BasicPageActions {

    @FindBy(id = "cli_shellHeaderSearchInput")
    public WebElement searchBox;

    @FindBy(xpath = "//a[@name='tab'][text()='Comprar']")
    public WebElement buyTabButton;

    @FindBy(css = "div#coreui-productplacement-f2jtswf > div > div.c-channel-placement-heading > a")
    public WebElement showAllAppsButton;

    @FindBy(className = "m-channel-placement-item")
    public List<WebElement> resultItems;

    @FindBy(className = "c-paragraph-3")
    public WebElement resultsCountsDisplay;

    private final String nextPageButton_ByXpath = "//ul[@class='m-pagination']/li[not(contains(@class, 'f-hide'))]/a[@class='c-glyph'][span[text()='Siguiente']]";
    private final String searchResultsPreview_listItems_ByCss_template = "div[aria-label^='%s'] > div > ul > li";

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        this.url = this.url + "/search";
        waitForPageToLoad();
    }

    @Override
    public void waitForPageToLoad() {
        new WebDriverWait(this.driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOf(searchBox));
    }

    public void clickBuyTabButton() {
        buyTabButton.click();
        this.waitForPageToLoad();
    }

    public int getResultsCount() {
        int resultsCount = resultItems.size();
        List<WebElement> foundButtons = this.driver.findElements(By.xpath(nextPageButton_ByXpath));
        while (foundButtons.size() > 0) {
            foundButtons.get(0).click();
            resultsCount += resultItems.size();
            this.waitForPageToLoad();
            foundButtons = this.driver.findElements(By.xpath(nextPageButton_ByXpath));
        }
        return resultsCount;
    }
    public int getResultsCountInDisplay() {
        return Integer.parseInt(resultsCountsDisplay.getText().replaceAll("([a-zA-Z-])", "").split("  ")[3].replaceAll(" ", ""));
    }
    public void verifyAllResultsAreDisplayed() {
        Assert.assertEquals(this.getResultsCountInDisplay(), this.getResultsCount());
    }
    public void clickOnSearchResultPreviewItem(int itemNumber, resultType type) {
        String selector = searchResultsPreview_listItems_ByCss_template;
        switch (type) {
            case GAMES:
                selector = String.format(searchResultsPreview_listItems_ByCss_template, "juegos");
                break;
            default:
                selector = String.format(searchResultsPreview_listItems_ByCss_template, "aplicaciones");
                break;
        }
        List<WebElement> searchResults = this.driver.findElements(By.cssSelector(selector));
        if(searchResults.size() >= itemNumber) {
            searchResults.get(itemNumber - 1).click();
        }
    }

    public void clickShowAllButton(resultType type) {
        switch (type) {
            case APPLICATIONS:
                showAllAppsButton.click();
                break;
            case TELEVISION:
                break;
            case MOVIES:
                break;
            default:
                break;
        }
    }

    public enum searchType {
        SHOP,
        EXPLORE
    }
    public enum resultType {
        APPLICATIONS,
        TELEVISION,
        MOVIES,
        GAMES
    }
}

