package api;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_13_UserInteraction {
    private WebDriver driver;
    private Actions action;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", ".\\browserdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Hover() {
        driver.get("http://www.myntra.com/");
        action.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'Discover')]"))).perform();
        driver.findElement(By.xpath("//a[contains(text(),'Only')]")).click();
        Assert.assertEquals("https://www.myntra.com/only", driver.getCurrentUrl());
        sleepInSecond(2);
    }

    @Test
    public void TC_02_ClickAndHold() {
        driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
        List<WebElement> allItem = driver.findElements(By.xpath("//ol/li"));
        action.clickAndHold(allItem.get(0)).moveToElement(allItem.get(3)).release().perform();
        List<WebElement> allItemSelected = driver.findElements(By.xpath("//ol/li[@class='ui-state-default ui-selectee ui-selected']"));
        Assert.assertEquals(allItemSelected.size(), 4);

    }

    @Test
    public void TC_03_ClickAndSelectRandomItem() {
        driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
        List<WebElement> allItem = driver.findElements(By.xpath("//ol/li"));
        action.keyDown(Keys.CONTROL).perform();
        allItem.get(2).click();
        allItem.get(3).click();
        allItem.get(4).click();
        allItem.get(6).click();
        allItem.get(9).click();
        allItem.get(11).click();
        action.keyUp(Keys.CONTROL).perform();
        List<WebElement> allItemSelected = driver.findElements(By.xpath("//ol/li[@class='ui-state-default ui-selectee ui-selected']"));
        Assert.assertEquals(allItemSelected.size(), 6);
    }

    @Test
    public void TC_04_DoubleClick() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        action.doubleClick(driver.findElement(By.xpath("//button[contains(text(),'Double click me')]"))).perform();
        Assert.assertEquals("Hello Automation Guys!", driver.findElement(By.xpath("//p[@id='demo']")).getText());
    }

    @Test
    public void TC_05_RightClick() {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        action.contextClick(driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']"))).perform();
        action.moveToElement(driver.findElement(By.xpath("//li[@class='context-menu-item context-menu-icon context-menu-icon-quit']"))).perform();
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='context-menu-item context-menu-icon context-menu-icon-quit context-menu-hover context-menu-visible']")).isDisplayed());
        driver.findElement(By.xpath("//li[@class='context-menu-item context-menu-icon context-menu-icon-quit context-menu-hover context-menu-visible']")).click();
    }

    @Test
    public void TC_06_DragAndDrop() {
        driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
        WebElement srcElement = driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement desElement = driver.findElement(By.xpath("//div[@id='droptarget']"));
        action.dragAndDrop(srcElement, desElement).perform();
        Assert.assertEquals("You did great!", driver.findElement(By.xpath("//div[@id='droptarget']")).getText());
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
