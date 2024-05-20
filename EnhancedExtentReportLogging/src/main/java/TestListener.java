import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    public void onTestStart(ITestResult result) {
        // Creates a test entry in the report to track the execution of the test.
        ExtentReportManager.createTest(result.getName(), "");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Ensures only the last retry test failure is logged by removing multiple failed test logs from the report.
        // If the test has been retried less than 3 times, removes the previous test entry from the report to avoid duplicates.
        if (RetryAnalyzer.getTestCount() < 3) {
            ExtentReportManager.removeTest(result.getName());
        }
    }
}
