package functionalTests.pages;

import functionalTests.container.TestContextFn;
import functionalTests.util.Browser;
import functionalTests.util.ConfigFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private RemoteWebDriver remoteWebDriver;
    private WebDriverWait wdWait;
    private WebElement webElement;

    public LoginPage(RemoteWebDriver remoteWebDriver, WebDriverWait wdWait) {
        this.remoteWebDriver = remoteWebDriver;
        this.wdWait = wdWait;
    }

    /** Page elements **/
    By tfUsername = By.xpath("//*[@id=\"username\"]");
    By tfPassword = By.xpath("//*[@id=\"password\"]");
    By btnLogIn = By.xpath("/html/body/div[1]/div[2]/div[1]/div/form/div/div/div[2]/main/button");


    public void LoginOnDoodLeUI(String username, String pass) throws Exception {
        /** Found username txt field and insert provided username **/
        webElement = wdWait.until(ExpectedConditions.visibilityOfElementLocated(tfUsername));
        webElement.sendKeys(username);

        /** Found password txt field and insert provided password **/
        webElement = wdWait.until(ExpectedConditions.visibilityOfElementLocated(tfPassword));
        webElement.sendKeys(pass);

        /** Click on login btn **/
        webElement = remoteWebDriver.findElement(btnLogIn);
        webElement.click();
    }
}
