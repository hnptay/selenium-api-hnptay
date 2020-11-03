package testNG;

import org.testng.annotations.*;

public class TestNG_Annotations {

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("before suite");
    }
    @BeforeTest
    public void beforeTest(){
        System.out.println("before test");
    }
    @BeforeClass
    public void beforeClass(){
        System.out.println("before class");
    }
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("before method");
    }
    @Test
    public void TC_01() {
        System.out.println("run on tc1");
    }
    @Test
    public void TC_02() {
        System.out.println("run on tc2");
    }
    @Test
    public void TC_03() {
        System.out.println("run on tc3");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("after suite");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("after class");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("after test");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("after method");
    }
}
