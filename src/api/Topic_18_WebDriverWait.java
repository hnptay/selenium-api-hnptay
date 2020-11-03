package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_WebDriverWait {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", ".\\browserdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        //driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_StaticSleep() {
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
        driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
        sleepInSecond(5);
        Assert.assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).isDisplayed());

    }

    @Test
    public void TC_02_ExplicitWait_Visible() {
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
        driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(),'Hello World!')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).isDisplayed());
    }

    @Test
    public void TC_03_ExplicitWait_Invisible() {
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
        driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));
        Assert.assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).isDisplayed());
    }

    @Test
    public void TC_04_ExplicitWait() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='demo-container size-narrow']"))));
        String date = driver.findElement(By.xpath("//div[@class='RadAjaxPanel']/span")).getText();
        System.out.println("Date before select: " + date);
        driver.findElement(By.xpath("//tr[@class='rcRow']//a[text()='2']")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));
        String date1 = driver.findElement(By.xpath("//div[@class='RadAjaxPanel']/span")).getText();
        System.out.println("Date after select: " + date1);
    }

    @Test
    public void TC_05_Explicitwait1() {
        driver.get("https://gofile.io/?t=uploadFiles");

        driver.findElement(By.xpath("//button[@id='dropZoneBtnSelect']")).sendKeys(".\\uploadFiles\\file1.txt");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='uploadFiles-btnUpload']")));
        //driver.findElement(By.xpath("//button[@id='uploadFiles-btnUpload']")).click();

    }


    public void sleepInSecond(long time) {
        try {
            Thread.sleep(1000 * time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
