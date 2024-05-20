import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ExtentReportManager {

    private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);
    private static ExtentReports extentReport;
    private static String extentReportPrefix;
    private static ExtentTest extentTest;


    public static void setupReport(String testName) {
        extentReport = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/report/" +
                getExtentReportPrefixName(testName) + ".html");
        extentReport.attachReporter(spark);
    }

    private static String getExtentReportPrefixName(String testName) {
        String date = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        if(Objects.isNull(extentReportPrefix)){
            extentReportPrefix = testName + "-" + date;
        }
        return extentReportPrefix;
    }

    public static void createTest(String testName, String description) {
      extentTest = extentReport.createTest(testName, description);
    }

    public  static void removeTest(String testName) {
        extentReport.removeTest(testName);
    }

    public static ExtentTest getTest() {
            return extentTest;
    }

    public static void flushReport() {
        System.out.println("flush report");
        extentReport.flush();
    }
}
