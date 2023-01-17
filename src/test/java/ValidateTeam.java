import Common.IPLTeam;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class ValidateTeam {

    JSONObject teamObj;
    IPLTeam team;
    ExtentSparkReporter reporter;
    ExtentReports extentReports;
    ExtentTest extentTest;

    @BeforeSuite
    public void beforeSuiteReporting() {
        reporter=new ExtentSparkReporter("target/ExecutionResult.html");
        extentReports=new ExtentReports();
        extentReports.attachReporter(reporter);
    }

    @AfterSuite
    public void afterSuiteReportCreation(){
        extentReports.flush();
    }

}
