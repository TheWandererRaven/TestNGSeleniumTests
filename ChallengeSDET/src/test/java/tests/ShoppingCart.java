package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;
import utilities.DataFetcher;

import java.time.Duration;

public class ShoppingCart {
    @Parameters({ "Search Term" })
    @Test(dataProvider = "JsonDataFetcher", dataProviderClass = DataFetcher.class)
    void shoppingCartValidationTest(String searchTerm){
        // SETUP
        //System.setProperty("webdriver.edge.driver", "drivers/msedgedriver.exe");
        // TODO: Move this to the data provider method to make a proper browser selection and driver init
        WebDriver driver =  new EdgeDriver();
        driver.manage().window().maximize();

        HomePage homePage = new HomePage(driver);
        homePage.navigateToPage();

        // STEP 1
        homePage.clickNavBarSearchButton();
        homePage.searchTextInSearchBox(searchTerm);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.clickBuyTabButton();
        searchResultsPage.clickOnSearchResultPreviewItem(1, SearchResultsPage.resultType.GAMES);
        // STEP 2
        GameDetailsPage gameDetailsPage = new GameDetailsPage(driver);
        gameDetailsPage.clickAddToCartButton();
        // STEP 3
        gameDetailsPage.verifyCartButtonCountMatches(1);
        // STEP 4
        homePage.navigateToPage();
        homePage.searchTextInSearchBox(searchTerm);
        searchResultsPage.waitForPageToLoad();
        searchResultsPage.clickBuyTabButton();
        searchResultsPage.clickOnSearchResultPreviewItem(4, SearchResultsPage.resultType.GAMES);
        gameDetailsPage.waitForPageToLoad();
        gameDetailsPage.clickAddToCartButton();
        gameDetailsPage.verifyCartButtonCountMatches(2);
        // STEP 5
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.removeItemFromList(2);
        shoppingCartPage.verifyCartButtonCountMatches(1);
        driver.close();
    }
}
