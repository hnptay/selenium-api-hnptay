package api;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_16_JavascriptExecutor {
    private WebDriver driver;
    private JavascriptExecutor js;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", ".\\browserdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    //@Test
    public void TC_01() {
        driver.get("http://live.demoguru99.com/");
        Assert.assertEquals(js.executeScript("return document.domain;"), "live.demoguru99.com");
        Assert.assertEquals(js.executeScript("return document.URL;"), "http://live.demoguru99.com/");
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[contains(text(),'Mobile')]")));
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[contains(text(),'Samsung Galaxy')]//ancestor::div[@class='product-info']//span[text()='Add to Cart']")));
        String successMsg = js.executeScript("return document.documentElement.innerText;").toString();
        Assert.assertTrue(successMsg.contains("Samsung Galaxy was added to your shopping cart."));
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[contains(text(),'Customer Service')]")));
        Assert.assertEquals(js.executeScript("return document.title;"), "Customer Service");
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//input[@id='newsletter']")));
        js.executeScript("window.location='http://demo.guru99.com/v4/'");
        Assert.assertEquals(js.executeScript("return document.domain;"), "demo.guru99.com");

    }

    @Test
    public void TC_02_RemoveAttribute() {
        driver.get("http://demo.guru99.com/v4");
        sendKeyByJS("mngr281677", "//input[@name='uid']");
        sendKeyByJS("aqadEbY", "//input[@name='password']");
        clickByJS("//input[@name='btnLogin']");
        clickByJS("//a[contains(text(),'New Customer')]");


    }

    //@Test
    public void TC_03_() {
    }

    //@Test
    public void TC_04_() {
    }

    //@Test
    public void TC_05_() {
    }

    //@Test
    public void TC_06_() {
    }

    public void sendKeyByJS(String inputValue, String locator) {
        js.executeScript("arguments[0].setAttribute('value','" + inputValue + "')", driver.findElement(By.xpath(locator)));
    }

    public void clickByJS(String locator) {
        js.executeScript("argument[0].click();", driver.findElement(By.xpath(locator)));
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}

