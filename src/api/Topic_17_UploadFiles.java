package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_17_UploadFiles {
    private WebDriver driver;
    ;
    private String rootFolderPath = System.getProperty("user.dir");
    private String uploadFile1 = "file1.txt";
    private String uploadFile2 = "file2.txt";
    private String uploadFile3 = "file3.txt";
    private String dirFile1 = rootFolderPath + "\\uploadfiles\\" + uploadFile1;
    private String dirFile2 = rootFolderPath + "\\uploadfiles\\" + uploadFile2;
    private String dirFile3 = rootFolderPath + "\\uploadfiles\\" + uploadFile3;


    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", ".\\browserdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_SendKeys() {
        driver.get("http://blueimp.github.com/jQuery-File-Upload/");
        driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(dirFile1 + "\n" + dirFile2 + "\n" + dirFile3);
        sleepInSecond(3);
    }

    public void sleepInSecond(int time) {
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
