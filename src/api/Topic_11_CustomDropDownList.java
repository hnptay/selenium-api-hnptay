package api;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_11_CustomDropDownList {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", ".\\browserdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 15);
        js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Jquery() {
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
        selectItemInCustomDropdownList("//span[@id='number-button']", "//ul[@id='number-menu']//li", "19");
        sleepInSecond(1);
        String[] expectedItem = {"19"};
        Assert.assertTrue(checkItemSelected(expectedItem, "//span[@id='number-button']//span[@class='ui-selectmenu-text']"));
    }

    @Test
    public void TC_02_Angular() {
        driver.get("https://bit.ly/2UV2vYi");
        selectItemInCustomDropdownList("//select[@id='games_hidden']//following-sibling::span", "//ul[@id='games_options']//li",
                "Badminton");
        sleepInSecond(1);
        String[] expectedItem = {"Badminton"};
        Assert.assertTrue(checkItemSelected(expectedItem, "//select[@id='games_hidden']//option"));
    }

    @Test
    public void TC_03_ReactJs() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemInCustomDropdownList("//div[@class='ui fluid selection dropdown']",
                "//div[@class='visible menu transition']//div", "Stevie Feliciano");
        sleepInSecond(1);
        String[] expectedItem = {"Stevie Feliciano"};
        Assert.assertTrue(checkItemSelected(expectedItem, "//div[@class='menu transition']//div[@class='active selected item']//span"));
    }

    @Test
    public void TC_04_VueJs() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemInCustomDropdownList("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//li", "Second Option");
        sleepInSecond(1);
        String[] expectedItem = {"Second Option"};
        Assert.assertTrue(checkItemSelected(expectedItem, "//li[@class='dropdown-toggle']"));
    }

    //@Test
    public void TC_05_Editable() {

    }

    @Test
    public void TC_06_MultipleSelect() {
        driver.get("http://multiple-select.wenzhixin.net.cn/examples#basic.html");
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src='templates/template.html?v=189&url=basic.html']")));
        String[] expectedItem = {"January", "May", "November", "December"};
        selectMultipleItemInCustomDropDownList("//option[text()='January']//ancestor::div[@class='col-sm-10']//div[@class='ms-parent multiple-select']",
                "//option[text()='January']//ancestor::div[@class='col-sm-10']//div[@class='ms-drop bottom']//ul//li//label//span", expectedItem,
                "//option[text()='January']//ancestor::div[@class='col-sm-10']//div[@class='ms-drop bottom']//ul//li[@class='selected']//label//span");
        sleepInSecond(1);
        Assert.assertTrue(checkItemSelected(expectedItem,
                "//option[text()='January']//ancestor::div[@class='col-sm-10']//div[@class='ms-drop bottom']//ul//li[@class='selected']//label//span"));
    }

    public void selectItemInCustomDropdownList(String parentLocator, String allItemsLocator, String expectedItem) {
        driver.findElement(By.xpath(parentLocator)).click();
        sleepInSecond(1);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsLocator)));
        List<WebElement> allItems = driver.findElements(By.xpath(allItemsLocator));
        for (WebElement chillItem : allItems) {
            if (chillItem.getText().equals(expectedItem)) {
                if (chillItem.isDisplayed()) {
                    chillItem.click();
                } else {
                    js.executeScript("arguments[0].scrollIntoView();", chillItem);
                    sleepInSecond(1);
                    js.executeScript("arguments[0].click();", chillItem);
                }
                sleepInSecond(1);
                break;
            }
        }
    }


    public void selectMultipleItemInCustomDropDownList(String parentLocator, String allIteamLocator, String[] expectedItems, String allItemSelectedLocator) {
        driver.findElement(By.xpath(parentLocator)).click();
        sleepInSecond(1);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allIteamLocator)));
        List<WebElement> allItems = driver.findElements(By.xpath(allIteamLocator));
        for (WebElement chillItem : allItems) {
            for (String expectedItem : expectedItems) {
                if (chillItem.getText().equals(expectedItem)) {
                    if (chillItem.isDisplayed()) {
                        chillItem.click();
                    } else {
                        js.executeScript("arguments[0].scrollIntoView();", chillItem);
                        sleepInSecond(1);
                        js.executeScript("arguments[0].click();", chillItem);
                    }
                }
            }
            List<WebElement> allItemSelected = driver.findElements(By.xpath(allItemSelectedLocator));
            if (allItemSelected.size() == expectedItems.length) {
                break;
            }
        }
    }


    public boolean checkItemSelected(String[] expectedItems, String allItemSelectedLocator) {
        int numberOfItem;
        List<WebElement> allItemsSelected = driver.findElements(By.xpath(allItemSelectedLocator));
        numberOfItem = 0;
        for (WebElement selectedItem : allItemsSelected) {
            for (String expectedItem : expectedItems)
                if (selectedItem.getText().contains(expectedItem)) {
                    numberOfItem++;
                } else if (js.executeScript("return arguments[0].textContent;", selectedItem).equals(expectedItem)) {
                    numberOfItem++;
                }
        }
        if (numberOfItem == allItemsSelected.size()) {
            return true;
        } else {
            return false;
        }
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
