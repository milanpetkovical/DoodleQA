package functionalTests.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
    private RemoteWebDriver remoteWebDriver;
    private WebDriverWait wdWait;
    private WebElement webElement;

    public DashboardPage(RemoteWebDriver remoteWebDriver, WebDriverWait wdWait) {
        this.remoteWebDriver = remoteWebDriver;
        this.wdWait = wdWait;
    }

    /** Page elements **/
    By btnCreatePoll = By.xpath("//*[@id=\"root\"]/div[1]/div/div[2]/div[1]/div/div[1]/div/main/article[2]/div/div/div[2]/div/a[1]/article/div/div/button");
    By btnAdditionalOption = By.xpath(".//div[contains(@class, 'activity-content-wrapper__89c0229df991a0bef50daa4d2cdd13b8 ActivityWrapper__56e5e8a039e3f0bcb19a9fddb098b76a')]/div[2]");
    By btnDelete = By.xpath(".//div[contains(@class, 'activity-content-wrapper__89c0229df991a0bef50daa4d2cdd13b8 ActivityWrapper__56e5e8a039e3f0bcb19a9fddb098b76a')]/div[2]/div/ul/li[2]");
    By dialogDelete = By.id("react-aria-modal-dialog");
    By btnSubmitDelete = By.xpath(".//div[contains(@class, 'StandardModal-container')]/div[3]/div/button[2]");

    /** Found btn Create Poll and start Create Group Poll  **/
    public void StartCreatePool() {
        webElement = wdWait.until(ExpectedConditions.visibilityOfElementLocated(btnCreatePoll));
        webElement.click();
    }

    public void DeleteCreatedGroupInvite() {
        /** Found btn Created Poll and click on option **/
        webElement = wdWait.until(ExpectedConditions.visibilityOfElementLocated(btnAdditionalOption));
        webElement.click();

        /** Found btn delete Poll and click on it **/
        webElement = wdWait.until(ExpectedConditions.visibilityOfElementLocated(btnDelete));
        webElement.click();

        /** Found new window and confirm delete **/
        webElement = wdWait.until(ExpectedConditions.visibilityOfElementLocated(dialogDelete));
        webElement = webElement.findElement(btnSubmitDelete);
        webElement.click();



    }
}
