import core.TestContext;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.HiddenElementPage;

import static core.Utils.waitForSomeTime;

public class WaitForNonDisplayedElementTest {

    TestContext context;
    WebDriver driver;
    HiddenElementPage hiddenElementPage;

    @BeforeMethod(alwaysRun = true)
    public void init() {
        context = new TestContext();
        context.init();
        this.driver = context.getDriverManager().getDriver();
        hiddenElementPage = context.getPageObjectManager().getHiddenElementPage();
    }

    @Test
    public void waitUntilNonDisplayedElementIsFound() {
        SoftAssert softAssert = new SoftAssert();
        String appUrl = System.getProperty("user.dir")+"/src/test/resources/webApp/HiddenElementExampleWebPage.html";
        driver.get("file:///"+appUrl);

        hiddenElementPage.clickOnBtnDisplay3Sec();
        waitForSomeTime(driver, 2);
        softAssert.assertTrue(hiddenElementPage.isHiddenElementInTheDom());
        System.out.println("found element after 3 second");

        waitForSomeTime(driver, 2);
        driver.navigate().refresh();
        hiddenElementPage.clickOnBtnDisplay5Sec();
        softAssert.assertTrue(hiddenElementPage.isHiddenElementInTheDom());
        System.out.println("found element after 5 second");

        waitForSomeTime(driver, 2);
        driver.navigate().refresh();
        hiddenElementPage.clickOnBtnDisplay10Sec();
        softAssert.assertTrue(hiddenElementPage.isHiddenElementInTheDom());
        System.out.println("found element after 10 second");

        softAssert.assertAll();
    }

    @AfterMethod()
    public void quitDriver() {
        waitForSomeTime(driver, 5);
        context.getDriverManager().quitDriver();
    }


}