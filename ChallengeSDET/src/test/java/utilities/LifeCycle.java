package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class LifeCycle {
    @BeforeSuite
    public void beforeSuite() {
        System.setProperty("webdriver.edge.driver", "drivers/msedgedriver.exe");
    }
}
