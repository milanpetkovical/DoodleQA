package functionalTests.stepDefinitions;

import functionalTests.container.TestContextFn;
import functionalTests.pages.CreatedGroupInvitePage;
import functionalTests.pages.DashboardPage;
import io.cucumber.java.en.Then;

public class DashboardSteps {
    DashboardPage dashboardPage =new DashboardPage(TestContextFn.remoteWebDriver, TestContextFn.wdWait);
    CreatedGroupInvitePage createdGroupInvitePage = new CreatedGroupInvitePage(TestContextFn.remoteWebDriver, TestContextFn.wdWait);


    @Then("delete created group invite")
    public void delete_created_group_invite() {
        createdGroupInvitePage.OpenDashboardPage();
        dashboardPage.DeleteCreatedGroupInvite();
    }
}
