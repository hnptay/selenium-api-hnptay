package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_10_DropDownList {
    private WebDriver driver;
    private Select select;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Single_Dropdown_List_01() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        select = new Select(driver.findElement(By.id("job1")));
        Assert.assertFalse(select.isMultiple());
        select.selectByVisibleText("Mobile Testing");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Testing");
        select.selectByValue("manual");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Testing");
        select.selectByIndex(9);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Functional UI Testing");
        Assert.assertEquals(select.getOptions().size(), 10);

    }

    @Test
    public void TC_02_Multiple_Dropdown_List_01() {
        select = new Select(driver.findElement(By.id("job2")));
        Assert.assertTrue(select.isMultiple());
        select.selectByVisibleText("Automation");
        select.selectByVisibleText("Mobile");
        select.selectByVisibleText("Desktop");
        List<WebElement> array = select.getAllSelectedOptions();
        for (WebElement selected : array) {
            System.out.println(selected.getText());
        }
        Assert.assertEquals(select.getAllSelectedOptions().size(), 3);
        select.deselectAll();
        Assert.assertEquals(select.getAllSelectedOptions().size(), 0);
    }

    @Test
    public void TC_03_Dropdown_List_02() {
        driver.get("https://demo.nopcommerce.com");
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();
        driver.findElement(By.id("gender-male")).click();
        driver.findElement(By.id("FirstName")).sendKeys("Auora");
        driver.findElement(By.id("LastName")).sendKeys("Wang");
        select = new Select(driver.findElement(By.name("DateOfBirthDay")));
        select.selectByVisibleText("13");
        Assert.assertEquals(select.getOptions().size(), 32);
        select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        select.selectByVisibleText("March");
        Assert.assertEquals(select.getOptions().size(), 13);
        select = new Select(driver.findElement(By.name("DateOfBirthYear")));
        select.selectByVisibleText("1933");
        Assert.assertEquals(select.getOptions().size(), 112);
        driver.findElement(By.id("Email")).sendKeys("auorawang" + randomNumber() + "@outlook.com");
        driver.findElement(By.id("Company")).sendKeys("TMA");
        driver.findElement(By.id("Password")).sendKeys("passwdd");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("passwdd");
        driver.findElement(By.id("register-button")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
        driver.findElement(By.xpath("//a[@class='ico-account']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText(), "My account - Customer info");
        driver.findElement(By.xpath("//a[@class='ico-logout']")).click();
        Assert.assertEquals(driver.getTitle(), "nopCommerce demo store");
    }

    public int randomNumber() {
        Random rand = new Random();
        return rand.nextInt(9999);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
