package testNG;

import org.testng.annotations.Test;

public class TestNG_Group {
    @Test(groups = "user" ,enabled = false)
    public void TC_01_Register(){

    }
    @Test(groups = "admin")
    public void TC_02_NewPage(){

    }
    @Test(groups = "user")
    public void TC_03_EditPage(){

    }
    @Test(groups = "admin")
    public void TC_04_DeletePage(){

    }
    @Test(groups = "user")
    public void TC_05_Viewpage(){

    }
}
