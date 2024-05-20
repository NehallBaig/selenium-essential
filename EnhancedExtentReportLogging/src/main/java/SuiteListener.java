import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class SuiteListener implements ISuiteListener {

    @BeforeSuite
    public void onStart(ISuite suite){
        ExtentReportManager.setupReport(suite.getName());
    }

    @AfterSuite
    public void onFinish(ISuite suite){
        ExtentReportManager.flushReport();
    }

}
