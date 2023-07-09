import core.TestContext;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.HiddenElementPage;

public class WaitForNonDisplayedElementTest {

    TestContext context;
    static WebDriver driver;
    HiddenElementPage hiddenElementPage;

    @BeforeMethod(alwaysRun = true)
    public void bf() {
        context = new TestContext();
        context.init();
        this.driver = context.getDriverManager().getDriver();
        hiddenElementPage = context.getPageObjectManager().getHiddenElementPage();
    }

    @Test
    public void waitUntilNonDisplayedElementIsFound() {
        String appUrl = System.getProperty("user.dir")+"/src/test/resources/webApp/HiddenElementExampleWebPage.html";
        driver.get("file:///"+appUrl);

        hiddenElementPage.clickOnBtnDisplay5Sec();
        Assert.assertTrue(hiddenElementPage.isHiddenElementInTheDom());

        driver.navigate().refresh();
        hiddenElementPage.clickOnBtnDisplay10Sec();
        Assert.assertTrue(hiddenElementPage.isHiddenElementInTheDom());

        driver.navigate().refresh();
        hiddenElementPage.clickOnBtnDisplay15Sec();
        Assert.assertTrue(hiddenElementPage.isHiddenElementInTheDom());


    }


}