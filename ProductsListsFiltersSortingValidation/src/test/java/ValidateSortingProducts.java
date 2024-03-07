import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Collections;
import java.util.List;

public class ValidateSortingProducts {

    private WebDriver driver;

    @FindBy(id = "sortAscDescBtn")
    WebElement shuffleListButton;

    @FindBy(className = "prodAsc")
    List<WebElement> productNamesAsc;

    @FindBy(className = "prodDesc")
    List<WebElement> productNamesDesc;

    @BeforeTest
    public void initDriver() {
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        PageFactory.initElements(driver, this);

        String appUrl = System.getProperty("user.dir") + "/src/test/resources/webApp/SortingValidationExamples.html";
        driver.get("file:///" + appUrl);

    }

    @Test
    public void validateProductSortedNameAscending() {
        Assert.assertTrue(isAscendingByName(productNamesAsc), "Products name are not sorted in ascending");
    }

    @Test
    public void validateProductSortedNameAscendingNegative() {
        SoftAssert softAssert = new SoftAssert();

        // Shuffle
        System.out.println("Shuffle Prod Asc List");
        shuffleList(shuffleListButton);

        softAssert.assertTrue(isAscendingByName(productNamesAsc), "[Negative TestCase] - Products name Asc list is shuffle. Test should fail as expected");

        // Un Shuffle
        shuffleList(shuffleListButton);
        softAssert.assertAll();
    }

    @Test
    public void validateProductSortedNameDescending() {
        Assert.assertTrue(isDescendingByName(productNamesDesc), "Products name are not sorted in descending");
    }

    public static boolean isAscendingByName(List<WebElement> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            String currentNode = list.get(i).getText().toLowerCase();
            String nextNode = list.get(i + 1).getText().toLowerCase();
            // Compare to
            // a value less than 0 if this string is lexicographically less than the string argument;
            if (currentNode.compareTo(nextNode) > 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDescendingByName(List<WebElement> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            String currentNode = list.get(i).getText().toLowerCase();
            String nextNode = list.get(i + 1).getText().toLowerCase();
            // Compare to
            // a value greater than 0 if this string is lexicographically less than the string argument;
            if (currentNode.compareTo(nextNode) < 0) {
                return false;
            }
        }
        return true;
    }

    public static void shuffleList(WebElement element) {
        waitForSometime();
        element.click();
        waitForSometime();

    }

    @AfterTest
    public void quitDriver() {
        driver.quit();
    }

    public static void waitForSometime() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
