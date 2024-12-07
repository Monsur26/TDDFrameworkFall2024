package homepage;

import common.WebAPI;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utility.Database;
import utility.ReadExcelFile;

public class RainforestHomepageTest extends WebAPI {
    static RainforestHomepage rainforestHomepage;
    public static void getInitElements() {
        rainforestHomepage = PageFactory.initElements(driver, RainforestHomepage.class);
    }
    @Test (testName = "login test")
    public void UserCredentialTesting(){
        getInitElements();
        rainforestHomepage.userCredentialTestCase("Username1","password1");
    }
    @Test (testName = "search Test")
    public void SearchBarTesting(){
        getInitElements();
        rainforestHomepage.searchBarTest("Cat");
    }
    @Test(testName = "invalid user cred")
    public void invalidUserCredTest(){
        getInitElements();
        rainforestHomepage.invalidUserCredentialTestCase("user","pass");
    }
    @Test(testName = "dropdownTest")
    public void dropdownTest(){
        getInitElements();
        rainforestHomepage.dropdownSelectionByValueTest();
    }
    @DataProvider(name = "testdata")
    public Object[][] testDataExample() {
        ReadExcelFile configuration = new ReadExcelFile("Data/datafile.xlsx");
        int rows = configuration.getRowCount(0);
        Object[][] signin_credentials = new Object[rows][2];

        for (int i = 0; i < rows; i++) {
            signin_credentials[i][0] = configuration.getData(0, i, 0);
            signin_credentials[i][1] = configuration.getData(0, i, 1);
        }
        return signin_credentials;
    }
    @Test(dataProvider = "testdata")
    public void testUser(String user, String pass) throws InterruptedException {
        getInitElements();
        rainforestHomepage.userCredentialTestCase(user,pass);
        rainforestHomepage.assertionForLogin();

    }
    @DataProvider(name = "DBdata")
    public Object[] testDataFromDB() throws Exception {
        Database db = new Database();
        Object[] data = db.getUserDatafromDB().toArray();
        return data;
    }
    @Test(dataProvider = "DBdata")
    public void testSearchByDB(String key){
        getInitElements();
        rainforestHomepage.searchBarTest(key);
    }
}
