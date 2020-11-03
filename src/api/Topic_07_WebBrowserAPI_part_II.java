package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_07_WebBrowserAPI_part_II {
    private WebDriver driver;

    private WebElement element;

    By emailTextboxBy = By.id("mail");
    By passwdTexboxBy = By.id("password");
    By ageUnder18By = By.id("under_18");
    By ageRadioDisableBy = By.id("radio-disabled");
    By educationTextboxBy = By.id("edu");
    By jobRole01By = By.id("job1");
    By jobRole02By = By.id("job2");
    By interestsDevelopmentBy = By.id("development");
    By interestsCheckBoxDisableBy = By.id("check-disbaled");
    By slider01By = By.id("slider-1");
    By slider02By = By.id("slider-2");
    By bioGraphyBy = By.id("bio");


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
    }

    @Test
    public void TC_01_Check_Displayed() {
        if (isElementDisplayed(emailTextboxBy)) {
            sendKeyToElement(emailTextboxBy, "Automation Testing");
        }
        if (isElementDisplayed(ageUnder18By)) {
            clickElement(ageUnder18By);
        }
        if (isElementDisplayed(educationTextboxBy))
            sendKeyToElement(educationTextboxBy, "Automation Testing");

    }

    @Test
    public void TC_02_Check_Enable() {
        System.out.println("-----------TC02-----------");
        if ((isElementEnable(emailTextboxBy))) {
            System.out.println("Email element is Enable");
        } else {
            System.out.println("Email element is Disable");
        }
        if ((isElementEnable(ageUnder18By))) {
            System.out.println("age uder18 element is Enable");
        } else {
            System.out.println("age uder18 element is Disable");
        }
        if ((isElementEnable(educationTextboxBy))) {
            System.out.println("Education element is Enable");
        } else {
            System.out.println("Education element is Disable");
        }
        if ((isElementEnable(jobRole01By))) {
            System.out.println("Job role01 element is Enable");
        } else {
            System.out.println("Job role01 element is Disable");
        }
        if ((isElementEnable(interestsDevelopmentBy))) {
            System.out.println("Interests (Development) element is Enable");
        } else {
            System.out.println("Interests (Development) element is Disable");
        }
        if ((isElementEnable(interestsDevelopmentBy))) {
            System.out.println("Slider 01 element is Enable");
        } else {
            System.out.println("Slider 01 element is Disable");
        }
        if ((isElementEnable(passwdTexboxBy))) {
            System.out.println("passwdTexboxBy element is Enable");
        } else {
            System.out.println("passwdTexboxBy element is Disable");
        }
        if ((isElementEnable(ageRadioDisableBy))) {
            System.out.println("ageRadioDisableBy element is Enable");
        } else {
            System.out.println("ageRadioDisableBy element is Disable");
        }
        if ((isElementEnable(bioGraphyBy))) {
            System.out.println("bioGraphyBy element is Enable");
        } else {
            System.out.println("bioGraphyBy element is Disable");
        }
        if ((isElementEnable(jobRole02By))) {
            System.out.println("jobRole02By element is Enable");
        } else {
            System.out.println("jobRole02By element is Disable");
        }
        if ((isElementEnable(interestsCheckBoxDisableBy))) {
            System.out.println("interestsCheckBoxDisableBy element is Enable");
        } else {
            System.out.println("interestsCheckBoxDisableBy element is Disable");
        }
        if ((isElementEnable(slider02By))) {
            System.out.println("slider02By element is Enable");
        } else {
            System.out.println("slider02By element is Disable");
        }
    }

    @Test
    public void TC_03_Check_Selected() {
        System.out.println("-----------TC03-----------");
        clickElement(ageUnder18By);
        clickElement(interestsDevelopmentBy);
        if (isElementSelected(ageUnder18By)) {
            System.out.println("ageUnder18By element is selected");
        } else {
            System.out.println("ageUnder18By element is unselected");
        }
        clickElement(interestsDevelopmentBy);
        if (isElementSelected(interestsDevelopmentBy)) {
            System.out.println("interestsDevelopmentBy element is selected");
        } else {
            System.out.println("interestsDevelopmentBy element is unselected");
        }

    }

    public boolean isElementDisplayed(By by) {
        element = driver.findElement(by);
        if (element.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isElementEnable(By by) {
        element = driver.findElement(by);
        if (element.isEnabled()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isElementSelected(By by) {
        element = driver.findElement(by);
        if (element.isSelected()) {
            return true;
        } else {
            return false;
        }
    }

    public void sendKeyToElement(By by, String value) {
        element = driver.findElement(by);
        element.sendKeys(value);
    }

    public void clickElement(By by) {
        element = driver.findElement(by);
        element.click();
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
