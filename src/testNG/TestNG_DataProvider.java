package testNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestNG_DataProvider {
    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", ".\\browserdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test(dataProvider = "UserAndPassword")
    public void TC_01_Login(String username, String password) {
        driver.get("");
    }

    @DataProvider
    public Object[][] UserAndPassword() {
        return new Object[][]{
                {"abc001@gmail.com", "1234"},
                {"abc002@gmail.com", "1234"}
        };
    }

    public String[] username() {
        return new String[]{"anc", "aksjel"};
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
