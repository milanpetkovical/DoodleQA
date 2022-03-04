package functionalTests.stepDefinitions;

import functionalTests.container.TestContextFn;
import functionalTests.pages.LoginPage;
import io.cucumber.java.en.When;

public class LoginSteps {

    /** Forward saved remote web driver and web driver wait **/
    LoginPage loginPage = new LoginPage(TestContextFn.remoteWebDriver, TestContextFn.wdWait);

    @When("user login on Doodle UI with username {string} and pass {string}")
    public void user_login_on_doodle_ui_with_username_and_pass(String username, String password) throws Exception {
        loginPage.LoginOnDoodLeUI(username,password);
    }

}
