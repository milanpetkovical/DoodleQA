package functionalTests.pages;

import functionalTests.container.TestContextFn;
import functionalTests.util.Browser;
import functionalTests.util.ConfigFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StartPage {
    private Browser browser = new Browser();
    private RemoteWebDriver remoteWebDriver;
    private WebDriverWait wdWait;
    private WebElement webElement;

    /** Initialize RemoteWebDriver for specific Browser **/
    public void start() throws Exception {
        remoteWebDriver = browser.initWebDriver(Browser.Type.CHROME);
        wdWait = new WebDriverWait(remoteWebDriver, 5);

        /** Save remoteWebDriver andWebDriverWait for next steps  **/
        TestContextFn.remoteWebDriver = remoteWebDriver;
        TestContextFn.wdWait = wdWait;
    }

    /** Page elements **/
    By btnAcceptAllCookie = By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]");
    By btnLogIn = By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/div/header/section/div/nav/ul[2]/li[1]/div/button[2]/span");

    public void NavigateToDoodLeUIAndStartLogin() throws Exception {
        ConfigFile configFile = new ConfigFile();
        start();

        /** Navigate to the specified url **/
        String url = configFile.GetElementFromConfig("baseUrl");
        remoteWebDriver.get(url);

        /** Accept Cookies **/
        webElement = wdWait.until(ExpectedConditions.visibilityOfElementLocated(btnAcceptAllCookie));
        webElement.click();

        /** Start login process **/
        webElement = remoteWebDriver.findElement(btnLogIn);
        webElement.click();
    }
}
