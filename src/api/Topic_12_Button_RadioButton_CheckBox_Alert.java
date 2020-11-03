package api;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class Topic_12_Button_RadioButton_CheckBox_Alert {
    private WebDriver driver;
    private JavascriptExecutor js;
    private WebElement element;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", ".\\browserdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    //@Test
    public void TC_01_Button() {
        driver.get("http://live.guru99.com/");
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")));
        Assert.assertEquals("http://live.demoguru99.com/index.php/customer/account/login/", driver.getCurrentUrl());
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")));
        Assert.assertEquals("http://live.demoguru99.com/index.php/customer/account/create/", driver.getCurrentUrl());
    }

    //@Test
    public void TC_02_DefaultCheckboxAndRadioButton() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        element = driver.findElement(By.xpath("//input[@id='eq5']"));
        element.click();
        Assert.assertTrue(element.isSelected());
        element.click();
        Assert.assertFalse(element.isSelected());
        driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
        element = driver.findElement(By.xpath("//input[@id='engine3']"));
        element.click();
        if (!element.isSelected()) {
            element.click();
        }
    }

    //@Test
    public void TC_03_CustomCheckboxAndRadioButton() {
        driver.get("https://material.angular.io/components/radio/examples");
        element = driver.findElement(By.xpath("//input[@id='mat-radio-4-input']"));
        js.executeScript("arguments[0].click();", element);
        sleepInSecond(2);
        Assert.assertTrue(element.isSelected());
        if (!element.isSelected()) {
            js.executeScript("arguments[0].click();", element);
        }
        driver.get("https://material.angular.io/components/checkbox/examples");
        WebElement element1 = driver.findElement(By.xpath("//input[@id='mat-checkbox-1-input']"));
        WebElement element2 = driver.findElement(By.xpath("//input[@id='mat-checkbox-2-input']"));
        js.executeScript("arguments[0].click();", element1);
        js.executeScript("arguments[0].click();", element2);
        Assert.assertTrue(element1.isSelected());
        Assert.assertTrue(element2.isSelected());
        js.executeScript("arguments[0].click();", element1);
        js.executeScript("arguments[0].click();", element2);
        Assert.assertFalse(element1.isSelected());
        Assert.assertFalse(element2.isSelected());
        sleepInSecond(2);
    }

    //@Test
    public void TC_04_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[contains(text(),'Click for JS Alert')]")).click();
        Assert.assertEquals("I am a JS Alert", driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        Assert.assertEquals("You clicked an alert successfully", driver.findElement(By.xpath("//p[@id='result']")).getText());

        driver.findElement(By.xpath("//button[contains(text(),'Click for JS Confirm')]")).click();
        Assert.assertEquals("I am a JS Confirm", driver.switchTo().alert().getText());
        driver.switchTo().alert().dismiss();
        Assert.assertEquals("You clicked: Cancel", driver.findElement(By.xpath("//p[@id='result']")).getText());

        driver.findElement(By.xpath("//button[contains(text(),'Click for JS Prompt')]")).click();
        Assert.assertEquals("I am a JS prompt", driver.switchTo().alert().getText());
        driver.switchTo().alert().sendKeys("automation");
        driver.switchTo().alert().accept();
        Assert.assertEquals("You entered: automation", driver.findElement(By.xpath("//p[@id='result']")).getText());


    }

    //@Test
    public void TC_05_AuthenticationAlert() {

    }


    public void sleepInSecond(long time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
