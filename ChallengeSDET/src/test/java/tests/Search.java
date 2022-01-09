package tests;

import pages.HomePage;
import pages.SearchResultsPage;
import pages.WindowsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.DataFetcher;

import java.lang.reflect.Method;

public class Search {
    //@Parameters({ "Search Term" })
    //@Test(dataProvider = "JsonDataFetcher", dataProviderClass = DataFetcher.class)
    void searchValidationTest(String searchTerm){
        // SETUP
        //System.setProperty("webdriver.edge.driver", "drivers/msedgedriver.exe");
        // TODO: Move this to the data provider method to make a proper browser selection and driver init
        WebDriver driver =  new EdgeDriver();
        driver.manage().window().maximize();

        // STEP 1
        HomePage homePage = new HomePage(driver);
        homePage.navigateToPage();
        // STEP 2
        homePage.clickNavBarWindowsButton();
        // STEP 3
        WindowsPage windowsPage = new WindowsPage(driver);
        // STEP 4
        windowsPage.searchTextInSearchBox(searchTerm);
        // STEP 5
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.clickBuyTabButton();
        // STEP 6
        searchResultsPage.clickShowAllButton(SearchResultsPage.resultType.APPLICATIONS);
        // STEP 7
        searchResultsPage.verifyAllResultsAreDisplayed();
        driver.close();
    }
}
