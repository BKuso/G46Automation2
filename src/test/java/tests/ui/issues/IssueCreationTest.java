package tests.ui.issues;

import io.qameta.allure.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.LoginPage;
import pages.issues.IssuesPage;
import tests.ui.BaseTest;

import java.util.Collection;
import java.util.List;

import static helpers.FileHelper.readDataFromFile;

@RunWith(Parameterized.class)
public class IssueCreationTest extends BaseTest {

    private IssuesPage page;

    private String title;
    private String body;
    private List<String> labels;

    public IssueCreationTest(String title,
                             String body,
                             List<String> labels) {
        this.title = title;
        this.body = body;
        this.labels = labels;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return readDataFromFile("/home/bohdan/projectQALight/G46Automation/src/test/resources/data/issues.txt");
    }

    @Before
    public void signIn(){
        page = new LoginPage(driver)
                .login(System.getProperty("username"), System.getProperty("password"))
                .searchProject()
                .openProjectIssues();
    }

    @Owner("BKuso")
    @Feature("")
    @Stories({@Story("Issue Creation"), @Story("GitHub")})
    @Link("G-46")
    @TmsLink("TC-2")
    @Description("Please ignore me!")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void checkIssueCreation(){
        page.pressToCreateNewIssue()
                .createNewIssue(this.title, this.body, this.labels)
                .validateCreatedIssue(this.title, this.body, this.labels);
    }

    @After
    public void tearDown(){
        page.logout();
    }

}
