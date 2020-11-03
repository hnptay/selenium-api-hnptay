package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_15_Windows {
    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", ".\\browserdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }


    @Test
    public void TC_01_Window_Tab_01() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        String parentWinTitle = driver.getTitle();
        String parentID = driver.getWindowHandle();
        driver.findElement(By.xpath("//a[contains(text(),'GOOGLE')]")).click();
        switchWindow("Google");
        Assert.assertEquals(driver.getTitle(), "Google");
        switchWindow(parentWinTitle);
        driver.findElement(By.xpath("//a[contains(text(),'FACEBOOK')]")).click();
        switchWindow("Facebook - Đăng nhập hoặc đăng ký");
        Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
        switchWindow(parentWinTitle);
        driver.findElement(By.xpath("//a[contains(text(),'TIKI')]")).click();
        switchWindow("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
        Assert.assertEquals(driver.getTitle(), "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
        closeWindows(parentID);
        Assert.assertEquals(driver.getTitle(), parentWinTitle);
    }

    public void switchWindow(String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWin : allWindows) {
            driver.switchTo().window(runWin);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }
        }
    }

    public void closeWindows(String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWin : allWindows) {
            if (!runWin.equals(parentID)) {
                driver.switchTo().window(runWin);
                driver.close();
            }
            driver.switchTo().window(parentID);
        }
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
