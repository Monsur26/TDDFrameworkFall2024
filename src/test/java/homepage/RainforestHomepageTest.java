package homepage;

import common.WebAPI;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class RainforestHomepageTest extends WebAPI {
    static RainforestHomepage rainforestHomepage;
    public static void getInitElements() {
        rainforestHomepage = PageFactory.initElements(driver, RainforestHomepage.class);
    }
    @Test (testName = "login test")
    public void UserCredentialTesting(){
        getInitElements();
        rainforestHomepage.userCredentialTestCase("Username","password");
    }
    @Test (testName = "search Test")
    public void SearchBarTesting(){
        getInitElements();
        rainforestHomepage.searchBarTest("Cat");
    }
}
