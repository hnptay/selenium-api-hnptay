package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_03_04_05_XpathCSS_part_II {

    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @BeforeMethod
    public void runEachTC() {
        driver.get("http://live.guru99.com/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
    }

    @Test
    public void TC_01_Login_with_empty_Email_and_Password() {
        driver.findElement(By.xpath("//button[@id='send2']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(),
                "This is a required field.");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(),
                "This is a required field.");
    }

    @Test
    public void TC_02_Login_with_invalid_Email() {
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123@123");
        driver.findElement(By.xpath("//button[@id='send2']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(),
                "Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test
    public void TC_03_Login_with_password_lessthan_6_charactor() {
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
        driver.findElement(By.xpath("//button[@id='send2']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText(),
                "Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void TC_04_Login_with_incorrect_passwd() {
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123123");
        driver.findElement(By.xpath("//button[@id='send2']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//li")).getText(),
                "Invalid login or password.");
    }

    @Test
    public void TC_05_login_successful() {
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123");
        driver.findElement(By.xpath("//button[@id='send2']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='dashboard']//h1")).getText(),
                "MY DASHBOARD");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//p[1]")).getText(),
                "Hello, Automation Testing!");
        String getMessage = driver.findElement(By.xpath("//div[@class='col-1']//p")).getText();
        Assert.assertTrue(getMessage.contains("Automation Testing"));
        Assert.assertTrue(getMessage.contains("automation@gmail.com"));
        driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
        driver.findElement(By.xpath("//div[@class='links']//a[@title='Log Out']")).click();
    }

    @Test
    public void TC_06_Create_a_new_account() {
        Random rand = new Random();
        int rand_1 = rand.nextInt(1000);
        driver.findElement(By.xpath("//a[@class='button']")).click();
        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Aurora");
        driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Wang");
        driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys("aurorawang" + rand_1 + "@gmail.com");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123123");
        driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("123123");
        driver.findElement(By.xpath("//button[@title='Register']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//li")).getText(),
                "Thank you for registering with Main Website Store.");
        driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
        driver.findElement(By.xpath("//div[@class='links']//a[@title='Log Out']")).click();
        String getMessage = driver.findElement(By.xpath("//div[@class='std']//h2")).getText();
        Assert.assertTrue(getMessage.contains("THIS IS DEMO SITE FOR"));
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
