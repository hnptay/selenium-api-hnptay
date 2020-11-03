package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_09_TextBox_TexArea {
    private WebDriver driver;
    private WebElement element;
    String email, userID, passwd, loginPageUrl, name, dob, addr, city, state, pin, phone, custID;
    String editEmail, editAddr, editCity, editState, editPin, editPhone;
    By nameBy = By.name("name");
    By dobBy = By.name("dob");
    By addrBy = By.name("addr");
    By genderBy = By.name("gender");
    By cityBy = By.name("city");
    By stateBy = By.name("state");
    By pinBy = By.name("pinno");
    By phoneBy = By.name("telephoneno");
    By emailBy = By.name("emailid");
    By passwdBy = By.name("password");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://demo.guru99.com/v4");
        email = "auorawang" + randomNumber() + "@hotmail.com";
        name = "Aura Wang";
        dob = "2013-03-13";
        addr = "345 PUBG";
        city = "HCM";
        state = "Binh Thanh";
        pin = "700000";
        phone = "0123458098";
        editEmail = "auorawang" + randomNumber() + "@outlook.com";
        editAddr = "678 LOL";
        editCity = "LA";
        editState = "Bacelona";
        editPin = "800000";
        editPhone = "0987123456";

    }

    @Test
    public void TC_01_RegisterToSystem() {
        loginPageUrl = driver.getCurrentUrl();
        driver.findElement(By.xpath("//a[text()='here']")).click();
        driver.findElement(By.name("emailid")).sendKeys(email);
        driver.findElement(By.name("btnLogin")).click();
        userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
        System.out.println("User ID: " + userID);
        passwd = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
        System.out.println("Passwd: " + passwd);

    }

    @Test
    public void TC_02_LoginToSystem() {
        driver.get(loginPageUrl);
        driver.findElement(By.name("uid")).sendKeys(userID);
        driver.findElement(By.name("password")).sendKeys(passwd);
        driver.findElement(By.name("btnLogin")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : " + userID + "']")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(), "Welcome To Manager's Page of Guru99 Bank");
    }

    @Test
    public void TC_03_NewCustomer() {
        driver.findElement(By.xpath("//a[text()='New Customer']")).click();
        sendkeyToElement(nameBy, name);
        driver.findElement(By.xpath("//input[@value='m']")).click();
        sendkeyToElement(dobBy, dob);
        sendkeyToElement(addrBy, addr);
        sendkeyToElement(cityBy, city);
        sendkeyToElement(stateBy, state);
        sendkeyToElement(pinBy, pin);
        sendkeyToElement(phoneBy, phone);
        sendkeyToElement(emailBy, email);
        sendkeyToElement(passwdBy, passwd);
        driver.findElement(By.name("sub")).click();
        custID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
        System.out.println("Customer ID: " + custID);
        Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(), "Customer Registered Successfully!!!");

        //Verify
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText(), custID);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dob);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), addr);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
    }

    @Test
    public void TC_04_EditCustomer() {
        driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
        driver.findElement(By.name("cusid")).sendKeys(custID);
        driver.findElement(By.name("AccSubmit")).click();

        Assert.assertFalse(driver.findElement(nameBy).isEnabled());
        Assert.assertFalse(driver.findElement(genderBy).isEnabled());
        Assert.assertFalse(driver.findElement(dobBy).isEnabled());

        clearTextBox(addrBy);
        sendkeyToElement(addrBy, editAddr);
        clearTextBox(cityBy);
        sendkeyToElement(cityBy, editCity);
        clearTextBox(stateBy);
        sendkeyToElement(stateBy, editState);
        clearTextBox(pinBy);
        sendkeyToElement(pinBy, editPin);
        clearTextBox(phoneBy);
        sendkeyToElement(phoneBy, editPhone);
        clearTextBox(emailBy);
        sendkeyToElement(emailBy, editEmail);
        driver.findElement(By.name("sub")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(), "Customer details updated Successfully!!!");

        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAddr);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editState);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editPhone);
    }

    public int randomNumber() {
        Random rand = new Random();
        return rand.nextInt(9999);
    }

    public void sendkeyToElement(By by, String value) {
        element = driver.findElement(by);
        element.sendKeys(value);
    }

    public void clearTextBox(By by) {
        element = driver.findElement(by);
        element.clear();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
