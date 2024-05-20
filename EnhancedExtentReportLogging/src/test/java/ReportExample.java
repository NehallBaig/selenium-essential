import com.aventstack.extentreports.ExtentReports;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import org.testng.annotations.*;

import java.util.Objects;


@Listeners({SuiteListener.class, TestListener.class})
public class ReportExample  {

    private static int count;

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void RetryTest() {

        ExtentReportManager.getTest().info("Remove duplicate failure logs");

        count++;
        System.out.println(count);

        // Multiple retries
        //**********
        if (count > 2) {
            ExtentReportManager.getTest().pass("pass");
            Assert.assertTrue(true);
        } else {
            ExtentReportManager.getTest().fail("fail");
            Assert.fail();
        }
        //**********


        //Comment above and uncomment below to run once
//        ExtentReportManager.getTest().pass("pass");
//        Assert.assertTrue(true);


    }

}


