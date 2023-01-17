import Common.Constants;
import Common.IPLTeam;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileReader;

public class ValidateTeam {

    JSONObject teamObj;
    IPLTeam team;
    ExtentSparkReporter reporter;
    ExtentReports extentReports;
    ExtentTest extentTest;

    @BeforeSuite
    public void beforeSuiteReporting() {
        reporter = new ExtentSparkReporter("target/ExecutionResult.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
    }

    @AfterSuite
    public void afterSuiteReportCreation() {
        extentReports.flush();
    }

    @BeforeTest
    public void addTeam() {
        try {
            teamObj = new JSONObject();
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader(Constants.REQUESTBODY);
            teamObj = (JSONObject) parser.parse(reader);
            String teamName = teamObj.get("name").toString();
            String teamLocation = teamObj.get("location").toString();
            team = new IPLTeam(teamName, teamLocation);
            JSONArray players = (JSONArray) teamObj.get("player");
            for (int i = 0; i < players.size(); i++) {
                JSONObject playerObj = (JSONObject) players.get(i);
                String playerName = (String) playerObj.get("name");
                String playerCountry = (String) playerObj.get("country");
                String playerRole = (String) playerObj.get("role");
                String playerPrice = (String) playerObj.get("price");
                team.addPlayer(playerName, playerCountry, playerRole, playerPrice);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void verifyForeignPlayerCount() {
        try
        {
            extentTest= extentReports.createTest("Verify foreign player count");
            int countForeignPlayers=0;
            for(int i=0;i<team.playerList.size();i++)
            {
                String countryName=team.playerList.get(i).playerCountry;
                if(!countryName.equals("India"))
                    countForeignPlayers++
            }
            extentTest.log(Status.INFO,"Total count of foreign players:"+countForeignPlayers);
            try{
                Assert.assertTrue(countForeignPlayers<=4);
                System.out.println("Test Case: Passed");
                extentTest.pass("PASSED");
            }
            catch (Exception e)
            {
                System.out.println("Test Case:Failed\n"+"Actual count of foreign players:"+countForeignPlayers);
                extentTest.fail("FAIELD");
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

}
