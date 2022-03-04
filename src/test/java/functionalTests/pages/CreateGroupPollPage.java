package functionalTests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateGroupPollPage {
    private RemoteWebDriver remoteWebDriver;
    private WebDriverWait wdWait;
    private WebElement webElement;

    public CreateGroupPollPage(RemoteWebDriver remoteWebDriver, WebDriverWait wdWait) {
        this.remoteWebDriver = remoteWebDriver;
        this.wdWait = wdWait;
    }

    /** Page elements **/
    By tfTitle = By.xpath("//*[@id=\"root\"]/div/form/div/div[2]/div/div/div/div/div/section[1]/div/section/div[1]/div/label/span[2]/input");
    By tfLocation = By.xpath("//*[@id=\"location\"]");
    By srLocation = By.xpath("//*[@id=\"google\"]/div[2]");
    By viewCalendar = By.id("MeetingTimesSection");
    By tblDataTime = By.xpath(".//div[contains(@class, 'rbc-time-content')]/div[8]/div[19]");
    By cbParticipants = By.xpath(".//div[contains(@class, 'MeetingSettings__setting')][2]");
    By tfParticipants = By.xpath(".//input[contains(@class, 'Input-field')]");
    By btnCreateInvite = By.xpath(".//button[contains(@class, 'Button Button--blue Button--inputButton Button--active ActionBar__submit-button')]");


    public void CreateGroupPull(String title, String location, String time, String participants) throws InterruptedException {
        /** Found title txt file and insert forwarded title **/
        webElement = wdWait.until(ExpectedConditions.visibilityOfElementLocated(tfTitle));
        webElement.sendKeys(title);

        /** Found location txt file and insert forwarded location and select after google search **/
        webElement = wdWait.until(ExpectedConditions.visibilityOfElementLocated(tfLocation));
        webElement.sendKeys(location);
        webElement = wdWait.until(ExpectedConditions.visibilityOfElementLocated(srLocation));
        webElement.click();

        /** Found forwarded date and time and select **/
        JavascriptExecutor js = (JavascriptExecutor) remoteWebDriver;
        Actions actions = new Actions(remoteWebDriver);

        webElement = wdWait.until(ExpectedConditions.visibilityOfElementLocated(viewCalendar));
        webElement = webElement.findElement(tblDataTime);
        js.executeScript("arguments[0].scrollIntoView();",webElement);
        actions.moveToElement(webElement).click().perform();

        /** Select participants check box and insert number of participants **/
        webElement = wdWait.until(ExpectedConditions.elementToBeClickable(cbParticipants));
        webElement.click();



        webElement = webElement.findElement(tfParticipants);
        actions.moveToElement(webElement).click().perform();
        webElement.sendKeys(participants);


        /** Found and click on Create Invite btn **/
        webElement =wdWait.until(ExpectedConditions.elementToBeClickable(btnCreateInvite));
        actions.moveToElement(webElement).click().perform();

    }
}
