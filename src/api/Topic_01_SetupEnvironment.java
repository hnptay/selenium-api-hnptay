package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_01_SetupEnvironment {

    private WebDriver driver;


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
    }

    @Test
    public void TC_01_Check_Google_Title() {
        String googleTitle = driver.getTitle();
        System.out.println("Title = " + googleTitle);
        Assert.assertEquals(googleTitle, "Google");
    }

    @Test
    public void TC_02_Check_Google_Url() {
        String googleUrl = driver.getCurrentUrl();
        System.out.println("Url = " + googleUrl);
        Assert.assertEquals(googleUrl, "https://www.google.com/");
    }

    @Test
    public void TC_03_Check_Google_Logo() {
        Assert.assertTrue(driver.findElement(By.cssSelector("#hplogo")).isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
