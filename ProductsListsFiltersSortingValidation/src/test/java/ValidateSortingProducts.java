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

    @FindBy (id = "sortAscDescBtn")
    WebElement shuffleListButton;

    @FindBy(className = "prodAsc")
    List<WebElement> productNamesAsc;

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
        // Shuffle
        System.out.println("Shuffle Prod Asc List");
        shuffleList(shuffleListButton);

        Assert.assertTrue(isAscendingByName(productNamesAsc), "[Negative TestCase] - Products name Asc list is shuffle. Test should fail as expected");

        // Re Shuffle
        shuffleList(shuffleListButton);
    }

    public static boolean isAscendingByName(List<WebElement> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            // Compare to
            // a value less than 0 if this string is lexicographically less than the string argument;
            if (list.get(i).getText().toLowerCase().compareTo(list.get(i + 1).getText().toLowerCase()) > 0) {
                return false;
            }
        }
        return true;
    }

    public static void shuffleList(WebElement element){
        element.click();
    }

    @AfterTest
    public void quitDriver() {
        driver.quit();
    }
}
