package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_XpathCSS_part_I {

    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://facebook.com");
    }

    @Test
    public void TC01_find_Id() {
        driver.findElement(By.id("u_0_m")).sendKeys("Day La Ho");
    }

    @Test
    public void TC02_find_Class() {
        driver.findElement(By.className("sx_4f5409")).click();
    }

    @Test
    public void TC03_find_Name() {
        driver.findElement(By.name("reg_email__")).sendKeys("nhapmail@gmail.com");
    }

    //@Test
    public void TC04_find_TagName() {

    }

    @Test
    public void TC05_find_Linktext() {
        driver.findElement(By.linkText("Quên tài khoản?")).click();
        driver.navigate().back();
    }

    @Test
    public void TC06_find_partial_Linktext() {
        driver.findElement(By.partialLinkText("Trang")).click();
        driver.navigate().back();
    }

    @Test
    public void TC07_find_CSS() {
        driver.findElement(By.cssSelector("#cookie-use-link")).click();
        driver.navigate().back();
    }

    @Test
    public void TC08_find_Xpath() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@id='u_0_z']/span[2]/label")).click();
        Thread.sleep(3000);
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
