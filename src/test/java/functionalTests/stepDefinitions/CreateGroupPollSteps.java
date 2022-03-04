package functionalTests.stepDefinitions;

import functionalTests.container.TestContextFn;
import functionalTests.pages.CreateGroupPollPage;
import functionalTests.pages.DashboardPage;
import io.cucumber.java.en.When;

public class CreateGroupPollSteps {
    DashboardPage dashboardPage =new DashboardPage(TestContextFn.remoteWebDriver, TestContextFn.wdWait);
    CreateGroupPollPage createGroupPollPage = new CreateGroupPollPage(TestContextFn.remoteWebDriver, TestContextFn.wdWait);

    @When("user create pull with title {string} on location {string} in time {string} with max {string} participants")
    public void user_create_pull_with_title_on_location_in_time_with_max_participants(String title, String location, String time, String participants) throws InterruptedException {
        dashboardPage.StartCreatePool();
        createGroupPollPage.CreateGroupPull(title, location, time,participants);
    }
}
