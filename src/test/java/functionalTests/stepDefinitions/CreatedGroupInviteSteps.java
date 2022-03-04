package functionalTests.stepDefinitions;

import functionalTests.container.TestContextFn;
import functionalTests.pages.CreatedGroupInvitePage;
import io.cucumber.java.en.Then;

public class CreatedGroupInviteSteps {
    CreatedGroupInvitePage createdGroupInvitePage = new CreatedGroupInvitePage(TestContextFn.remoteWebDriver, TestContextFn.wdWait);

    @Then("validate created invite title {string} and location {string}")
    public void validate_created_invite_title_and_location(String title, String location) {
        createdGroupInvitePage.ValidateCreatedGroupInvite(title,location);
    }

}
