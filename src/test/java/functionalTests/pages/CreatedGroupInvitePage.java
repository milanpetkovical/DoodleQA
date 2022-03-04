package functionalTests.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreatedGroupInvitePage {
    private RemoteWebDriver remoteWebDriver;
    private WebDriverWait wdWait;
    private WebElement webElement;

    public CreatedGroupInvitePage(RemoteWebDriver remoteWebDriver, WebDriverWait wdWait) {
        this.remoteWebDriver = remoteWebDriver;
        this.wdWait = wdWait;
    }

    /** Page elements **/
    By txtTitle = By.xpath("//*[@id=\"root\"]/form/div/div[2]/div[1]/div/div/div/section[2]/div[1]/div/h2");
    By txtLocation = By.xpath("//*[@id=\"root\"]/form/div/div[2]/div[1]/div/div/div/section[2]/div[1]/div/div[2]/div[2]/div/div");
    By imgLogo = By.xpath(".//a[contains(@class, 'LogoLink')]");


    public void ValidateCreatedGroupInvite(String title, String location) {
        webElement = wdWait.until(ExpectedConditions.visibilityOfElementLocated(txtTitle));
        Assert.assertEquals(webElement.getText(), title);

        webElement = wdWait.until(ExpectedConditions.visibilityOfElementLocated(txtLocation));
        Assert.assertEquals(webElement.getText(), location);
    }

    public void OpenDashboardPage() {
        webElement = wdWait.until(ExpectedConditions.visibilityOfElementLocated(imgLogo));
        webElement.click();
    }
}
