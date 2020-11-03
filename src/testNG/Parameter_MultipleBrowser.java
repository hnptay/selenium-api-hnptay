package testNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Parameter_MultipleBrowser {
    private WebDriver driver;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        if (browserName.equals("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", ".\\browserdriver\\chromedriver.exe");
            driver = new ChromeDriver();
        } else {
            System.setProperty("wenbdriver.edge.driver", ".\\browserdriver\\");
            driver = new EdgeDriver();
        }
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_openBrowser() throws InterruptedException {
        driver.get("https://google.com");
        Thread.sleep(3000);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
