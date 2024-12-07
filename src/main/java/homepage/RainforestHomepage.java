package homepage;

import common.WebAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

public class RainforestHomepage extends WebAPI {
    // WebElements for the page
   // public WebElement loginButton = driver.findElement(By.id("loginButton"));
    // also a way to define webElement
    @FindBy (how = How.ID, using = "username") public WebElement usernameInputField;
    @FindBy (how = How.ID, using = "password") public WebElement passwordInputField;
    @FindBy (how = How.ID, using = "errorMessage") public WebElement loginErrorMessage;
    @FindBy (how = How.ID, using = "loginButton") public WebElement loginButton;
    @FindBy(how=How.XPATH, using = "//input[@id='searchInput' and @type='text']") public WebElement searchBar;
    @FindBy(how=How.XPATH, using = "//button[@onclick='performSearch()']") public WebElement searchButton;
    @FindBy(how = How.ID, using = "select") public WebElement dropdownOption;
    @FindBy(how = How.ID,using ="dropdownResult") public WebElement dropdownResultValidation;

    //Action methods
    public void usernameInputEntry(String usernameInput){
        usernameInputField.click();
        usernameInputField.sendKeys(usernameInput);
    }
    public void passwordInputEntry(String passwordInput){
        passwordInputField.click();
        passwordInputField.sendKeys(passwordInput);
    }
    public void clickLoginButton(){
        loginButton.click();
    }
    public void searchBarEntry(String search){
        searchBar.click();
        searchBar.sendKeys(search);
    }
    //Assertion methods for validation
    public void assertionForLogin(){
        String actualTitle= driver.getTitle();
        Assert.assertEquals(actualTitle,"RainForest","Assertion failed");
    }
    public void assertInvalidUserCredential(){
        String actualErrorMessage = loginErrorMessage.getText();
        String expectedErrorMessage = "Invalid username or password!";
        Assert.assertEquals(actualErrorMessage,expectedErrorMessage,"Assertion failed");
    }
    public void assertDropdownValueSelection(){
        boolean resultIsDisplayed = dropdownResultValidation.isDisplayed();
        Assert.assertTrue(resultIsDisplayed,"Assertion failed");
    }

    //Test Case methods
    public void userCredentialTestCase(String username, String password){
        usernameInputEntry(username);
        passwordInputEntry(password);
        clickLoginButton();
        assertionForLogin();
    }
    public void invalidUserCredentialTestCase(String username, String password){
        usernameInputEntry(username);
        passwordInputEntry(password);
        clickLoginButton();
        assertInvalidUserCredential();
    }

    public void searchBarTest(String input){
        searchBarEntry(input);
        searchButton.click();
    }
    public void dropdownSelectionByValueTest(){
        selectDropDownByValue(dropdownOption,"Congo");
        assertDropdownValueSelection();
    }
}
