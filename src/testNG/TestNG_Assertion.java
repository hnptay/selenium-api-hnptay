package testNG;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNG_Assertion {
    @Test
    public void TC_01(){
        String testNG = "testNG";
        String  jUnit = "jUnit";
        boolean status = testNG.equals(jUnit);
        Assert.assertTrue(status);
    }
}
