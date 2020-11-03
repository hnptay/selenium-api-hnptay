package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_06_WebBrowserAPI_part_I {
    private WebDriver driver;

    By createAnAccountBy = By.xpath("//div[@class='buttons-set']/a[@class='button']");
    By buttonMyAccountBy = By.xpath("//div[@class='footer']//a[@title='My Account']");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void runEachTCs() {
        driver.get("http://live.demoguru99.com/");
        clickElement(buttonMyAccountBy);
    }


    @Test
    public void TC_01_Verify_Url() {
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
        clickElement(createAnAccountBy);
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
    }

    @Test
    public void TC_02_Verify_Title() {
        Assert.assertEquals(driver.getTitle(), "Customer Login");
        clickElement(createAnAccountBy);
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
    }

    @Test
    public void TC_03_Navigate_function() {
        clickElement(createAnAccountBy);
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
        driver.navigate().back();
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
        driver.navigate().forward();
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
    }

    @Test
    public void TC_04_Get_Page_Source_Code() {
        String getText1 = driver.getPageSource();
        Assert.assertTrue(getText1.contains("Login or Create an Account"));
        clickElement(createAnAccountBy);
        String getText2 = driver.getPageSource();
        Assert.assertTrue(getText2.contains("Create an Account"));
    }

    public void clickElement(By by) {
        WebElement element = driver.findElement(by);
        element.click();
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
