package functionalTests.util;

import io.cucumber.java.After;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.HashMap;
import java.util.logging.Logger;


public class Browser {

//    WebDriver driver;
    protected static final String DRIVER_DIRECTORY = "src/test/resources/drivers.";
    protected static final String OS_LONG_NAME = System.getProperty("os.name");
    protected static final String OS_SHORT_NAME = OS_LONG_NAME.substring(0, OS_LONG_NAME.indexOf(' '));
    private static final Logger seleniumLogger = Logger.getLogger("org.openqa.selenium.remote.ProtocolHandshake");

    protected static final HashMap<String, String> executableFileTypes = new HashMap<>();
    static {
        seleniumLogger.setLevel(java.util.logging.Level.OFF);
        executableFileTypes.put("Windows", ".exe");
        executableFileTypes.put("Linux", "");
        executableFileTypes.put("Mac", "");
    }

    public enum Type {
        EDGE, EXPLORER, CHROME, FIREFOX, SAFARI, OPERA
    }

    private static boolean browserActive = false;
    private Type browserType = Type.CHROME;

    protected static RemoteWebDriver remoteWebDriver;
//    protected static WebDriverWait wdWait = new WebDriverWait(remoteWebDriver, 20);
    protected String baseURL;
    private String ipAddress = "127.0.0.1";
    private Process driverProcess;
    private String downloadPath = "downloads";

    /** Open specific Browser **/
    public RemoteWebDriver initWebDriver(Type browserType) throws Exception {

        System.out.println("STARTING BROWSER: " + browserType.toString());

        if (checkIfActive()) {
            remoteWebDriver.close();
            remoteWebDriver.quit();
            remoteWebDriver = null;
            browserActive = false;
        }

        initializeBrowser(browserType);
        browserActive = true;

       // remoteWebDriver.manage().deleteAllCookies();
        remoteWebDriver.manage().window().maximize();
        //remoteWebDriver.manage().window().setSize(new Dimension(1550, 900));
        //remoteWebDriver.manage().window().fullscreen();

        return remoteWebDriver;
    }


    /** Initialize RemoteWebDriver for specific Browser **/
    public void initializeBrowser(Type browserType) throws Exception {

        String port;
        URL clientURL;

        this.browserType = browserType;
        switch (this.browserType) {

            case EDGE:

                driverProcess = Runtime.getRuntime().exec(DRIVER_DIRECTORY + OS_SHORT_NAME.toLowerCase() + "/" + "MicrosoftWebDriver" + executableFileTypes.get(OS_SHORT_NAME));
                Thread.sleep(1000);

                EdgeOptions edgeOptions = new EdgeOptions();

                port = "17556";
                clientURL = new URL("http://" + ipAddress + ":" + port);
                remoteWebDriver = new RemoteWebDriver(clientURL, edgeOptions);
                break;

            case EXPLORER:

                driverProcess = Runtime.getRuntime().exec(DRIVER_DIRECTORY + OS_SHORT_NAME.toLowerCase() + "/" + "IEDriverServer" + executableFileTypes.get(OS_SHORT_NAME));
                Thread.sleep(1000);

                InternetExplorerOptions ieOptions = new InternetExplorerOptions();

                port = "5555";
                clientURL = new URL("http://" + ipAddress + ":" + port);
                remoteWebDriver = new RemoteWebDriver(clientURL, ieOptions);
                break;

            case CHROME:

                driverProcess = Runtime.getRuntime().exec( DRIVER_DIRECTORY + OS_SHORT_NAME.toLowerCase() + "/" + "chromedriver" + executableFileTypes.get(OS_SHORT_NAME));
                Thread.sleep(1000);

                HashMap<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", downloadPath);

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setExperimentalOption("prefs", chromePrefs);

                port = "9515";
                clientURL = new URL("http://" + ipAddress + ":" + port);
                remoteWebDriver = new RemoteWebDriver(clientURL, chromeOptions);
                break;

            case OPERA:

                driverProcess = Runtime.getRuntime().exec(DRIVER_DIRECTORY + OS_SHORT_NAME.toLowerCase() + "/" + "operadriver" + executableFileTypes.get(OS_SHORT_NAME));
                Thread.sleep(1000);

                HashMap<String, Object> operaPrefs = new HashMap<>();
                operaPrefs.put("profile.default_content_settings.popups", 0);
                operaPrefs.put("download.default_directory", downloadPath);

                OperaOptions operaOptions = new OperaOptions();
                operaOptions.setExperimentalOption("prefs", operaPrefs);

                port = "9515";
                clientURL = new URL("http://" + ipAddress + ":" + port);
                remoteWebDriver = new RemoteWebDriver(clientURL, operaOptions);
                break;

            case FIREFOX:

                driverProcess = Runtime.getRuntime().exec(DRIVER_DIRECTORY + OS_SHORT_NAME.toLowerCase() + "/" + "geckodriver" + executableFileTypes.get(OS_SHORT_NAME));
                Thread.sleep(1000);

                FirefoxProfile firefoxProfile = new FirefoxProfile();
                firefoxProfile.setPreference("browser.download.useDownloadDir", true);
                firefoxProfile.setPreference("browser.download.folderList", 2);
                firefoxProfile.setPreference("browser.download.dir", downloadPath);
                firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
                firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv");
                firefoxProfile.setPreference("pdfjs.disabled", true);

                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setProfile(firefoxProfile);

                port = "4444";
                clientURL = new URL("http://" + ipAddress + ":" + port);
                remoteWebDriver = new RemoteWebDriver(clientURL, firefoxOptions);
                break;

            case SAFARI:

                SafariOptions safariOptions = new SafariOptions();
                safariOptions.setCapability("safari.options.dataDir", downloadPath);

                port = "4444";
                clientURL = new URL("http://" + ipAddress + ":" + port + "/wd/hub/");
                remoteWebDriver = new RemoteWebDriver(clientURL, safariOptions);
                break;

            default:
                throw new Exception("Browser type is not supported");
        }
    }

    /** Cleanup all Browser resources **/
    @After
    public void afterScenario(){
        if (!checkIfActive()) {
            return;
        }

        System.out.println("CLOSING BROWSER: " + browserType.toString());

//        remoteWebDriver.close();
        remoteWebDriver.quit();
        remoteWebDriver = null;
        browserActive = false;
        if (driverProcess != null) {
            driverProcess.destroy();
        }
    }


    /** Check if Browser is active **/
    public boolean checkIfActive() {

        return browserActive && remoteWebDriver != null;
    }

}
