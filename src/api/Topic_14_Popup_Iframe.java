package api;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_14_Popup_Iframe {
    private WebDriver driver;
    private JavascriptExecutor js;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", ".\\browserdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        js = (JavascriptExecutor) driver;
    }

    //@Test
    public void TC_01_FixedPopup() {
        driver.get("https://www.zingpoll.com/");
        driver.findElement(By.xpath("//a[@id='Loginform']")).click();
        sleepInsecond(3);
        Assert.assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'SIGN IN')]")).isDisplayed());
        driver.findElement(By.xpath("//div[@class='modal-dialog modal_dialog_custom']//span[contains(text(),'×')]")).click();
        Assert.assertFalse(driver.findElement(By.xpath("//h4[contains(text(),'SIGN IN')]")).isDisplayed());
    }

    //@Test
    public void TC_02_RandomPopup() {
        driver.get("https://blog.testproject.io/");
        if (isPopupDisplayed("")) {
            driver.findElement(By.xpath("")).click();
        }
        driver.findElement(By.xpath("")).sendKeys("Selenium");
        String keyWord = driver.findElement(By.xpath("")).getText();
        Assert.assertTrue(keyWord.contains("Selenium"));
    }

    //@Test
    public void TC_03_Iframe() {
        driver.get("https://kyna.vn/");
        if (isPopupDisplayed("//img[@class='fancybox-image']")) {
            driver.findElement(By.xpath("//a[@class='fancybox-item fancybox-close']")).click();
        }
        sleepInsecond(3);
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@class='face-content']//iframe")));
        sleepInsecond(3);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='face-content']//iframe")).isDisplayed());
        driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']//iframe")));
        Assert.assertEquals("169K likes", driver.findElement(By.xpath("//div[@class='_1drq']")).getText());
        driver.switchTo().defaultContent();
        Assert.assertTrue(driver.findElement(By.xpath("//iframe[@id='cs_chat_iframe']")).isDisplayed());
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='cs_chat_iframe']")));
        WebElement element = driver.findElement(By.xpath("//div[@class='textarea_wrapper']/textarea"));
        element.sendKeys("123");
        element.sendKeys(Keys.ENTER);
        sleepInsecond(3);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='infomation-section scroll pdx-10']")).isDisplayed());
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys("Java");
        driver.findElement(By.xpath("//button[@class='btn btn-secondary search-button']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='menu-heading']/h1")).getText(), "Java");

    }

    //@Test
    public void TC_04_WindowsTab_01() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        String parentWindow = driver.getWindowHandle();
        driver.findElement(By.xpath("//a[contains(text(),'GOOGLE')]")).click();
        switchToWindowByTitle("Google");
        Assert.assertEquals(driver.getTitle(), "Google");
        driver.switchTo().window(parentWindow);
        driver.findElement(By.xpath("//a[contains(text(),'FACEBOOK')]")).click();
        switchToWindowByTitle("Facebook – log in or sign up");
        Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");
        driver.switchTo().window(parentWindow);
        closeAllChillWindow(parentWindow);
        sleepInsecond(3);

    }

    @Test
    public void TC_05_WindowsTab_02() {
        driver.get("http://live.demoguru99.com/index.php/");
        driver.findElement(By.xpath("//a[contains(text(),'Mobile')]")).click();
        String parentWindow = driver.getTitle();
        driver.findElement(By.xpath("//a[@title='Sony Xperia']//ancestor::div[@class='product-info']//a[contains(text(),'Add to Compare')]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Sony Xperia has been added to comparison list.");
        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']//ancestor::div[@class='product-info']//a[contains(text(),'Add to Compare')]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
        driver.findElement(By.xpath("//div[@class='actions']//span[contains(text(),'Compare')]")).click();
        switchToWindowByTitle("Products Comparison List - Magento Commerce");
        driver.close();
        switchToWindowByTitle(parentWindow);
        sleepInsecond(3);
        driver.findElement(By.xpath("//a[contains(text(),'Clear All')]")).click();
        driver.switchTo().alert().accept();
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The comparison list was cleared.");
    }


    public boolean isPopupDisplayed(String locator) {
        try {
            WebElement element = driver.findElement(By.xpath(locator));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void sleepInsecond(long unit) {
        try {
            Thread.sleep(unit * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void switchToWindowByTitle(String title) {
        Set<String> allWindow = driver.getWindowHandles();
        for (String currentWindow : allWindow) {
            driver.switchTo().window(currentWindow);
            String currentTitle = driver.getTitle();
            if (currentTitle.equals(title)) {
                break;
            }
        }
    }

    public void closeAllChillWindow(String parentWindow) {
        Set<String> allWindow = driver.getWindowHandles();
        for (String currentChillWindow : allWindow) {
            if (!currentChillWindow.equals(parentWindow)) {
                driver.switchTo().window(currentChillWindow);
                driver.close();
            }
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
