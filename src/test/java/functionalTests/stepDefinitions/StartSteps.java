package functionalTests.stepDefinitions;

import functionalTests.container.TestContextFn;
import functionalTests.pages.StartPage;
import io.cucumber.java.en.Given;

public class StartSteps {
    StartPage startPage = new StartPage();

    @Given("navigate to the Doodle web app and start Login")
    public void navigate_to_the_doodle_web_app_and_start_login() throws Exception {
        startPage.NavigateToDoodLeUIAndStartLogin();
    }
}
