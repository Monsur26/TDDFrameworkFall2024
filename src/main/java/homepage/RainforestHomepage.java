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
    @FindBy (how = How.ID, using = "loginButton") public WebElement loginButton;
    @FindBy(how=How.XPATH, using = "//input[@placeholder='Search...']") public WebElement searchBar;

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

    //Test Case methods
    public void userCredentialTestCase(String username, String password){
        usernameInputEntry(username);
        passwordInputEntry(password);
        clickLoginButton();
        assertionForLogin();
    }

    public void searchBarTest(String input){
        searchBarEntry(input);
    }

}
