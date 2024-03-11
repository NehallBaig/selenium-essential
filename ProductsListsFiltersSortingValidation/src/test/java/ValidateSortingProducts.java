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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateSortingProducts {

    private WebDriver driver;

    @FindBy(id = "sortAscDescBtn")
    WebElement shuffleListButton;

    @FindBy(className = "prodAsc")
    List<WebElement> productNamesAsc;

    @FindBy(className = "prodDesc")
    List<WebElement> productNamesDesc;

    @FindBy(className = "priceAsc")
    List<WebElement> productPriceAsc;

    @FindBy(className = "priceDesc")
    List<WebElement> productPriceDesc;

    @FindBy(xpath = "//*[contains(@class,'promotedProductAsc')]")
    List<WebElement> promotedProductName;

    @FindBy(xpath = "//*[contains(@class,'PPQPT1')]")
    List<WebElement> ppqpt1PositiveProductList;

    @FindBy(xpath = "//*[contains(@class,'PPQPT2')]")
    List<WebElement> ppqpt2PositiveProductList;

    @FindBy(xpath = "//*[contains(@class,'PPQNT')]")
    List<WebElement> negativeProductList;

    @BeforeTest
    public void initDriver() {
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        PageFactory.initElements(driver, this);

        String appUrl = System.getProperty("user.dir") + "/src/test/resources/webApp/SortingValidationExamples.html";
        driver.get("file:///" + appUrl);

    }


    @Test(priority = 1)
    public void validateProductSortedNameAscending() {
        Assert.assertTrue(isAscendingByName(productNamesAsc), "Products name are not sorted in ascending");
    }

    @Test(priority = 2)
    public void validateProductSortedNameDescending() {
        Assert.assertTrue(isDescendingByName(productNamesDesc), "Products name are not sorted in descending");
    }

    @Test(priority = 3)
    public void validateProductSortedPriceAscending() {
        Assert.assertTrue(isAscendingByPrice(productPriceAsc));
    }

    @Test(priority = 4)
    public void validateProductSortedPriceDescending() {
        Assert.assertTrue(isDescendingByPrice(productPriceDesc));
    }
    @Test(priority = 5)
    public void validateProductSortedPromotedNameAscending() {
        Assert.assertTrue(isAscendingByPromotedProductName(promotedProductName, "ad"), "Products name are not sorted in ascending");
    }

    @Test(priority = 6)
    public void validateProductSortedPPQPTTest() {
        Assert.assertTrue(isAscendingByPromotedProductName(ppqpt1PositiveProductList, "ad"), "Products name are not sorted in ascending");
    }

    @Test(priority = 7)
    public void validateProductSortedPPQPT2Test() {
        Assert.assertTrue(isAscendingByPromotedProductName(ppqpt2PositiveProductList, "ad"), "Products name are not sorted in ascending");
    }
    @Test(priority = 8)
    public void validateProductSortedNegativeTest() {
        Assert.assertFalse(isAscendingByPromotedProductName(negativeProductList, "ad"), "Products name are not sorted in ascending");
    }
    @Test(priority = 9)
    public void validateProductSortedNameAscendingNegative() {
        // Shuffle
        System.out.println("Shuffle Prod Asc List");
        shuffleList();
        Assert.assertFalse(isAscendingByName(productNamesAsc), "[Negative TestCase] - Products name Asc list is shuffle. Test should pass as expected");
    }


    @Test(priority = 10)
    public void validateProductSortedNameDescendingNegative() {
        Assert.assertFalse(isDescendingByName(productNamesDesc), "Products name are not sorted in descending");
    }

    @Test(priority = 11)
    public void validateProductSortedPriceAscendingNegative() {
        Assert.assertFalse(isAscendingByPrice(productPriceAsc));
    }

    @Test(priority = 12)
    public void validateProductSortedPriceDescendingNegative() {
        Assert.assertFalse(isDescendingByPrice(productPriceDesc));
    }
    @Test(priority = 13)
    public void validateProductSortedPromotedNameAscendingNegative() {
        Assert.assertFalse(isAscendingByPromotedProductName(promotedProductName, "ad"), "Products name are not sorted in ascending");
    }

    @Test(priority = 14)
    public void validateProductSortedPPQPTTestNegative() {
        Assert.assertFalse(isAscendingByPromotedProductName(ppqpt1PositiveProductList, "ad"), "Products name are not sorted in ascending");
    }

    @Test(priority = 15)
    public void validateProductSortedPPQPT2TestNegative() {
        Assert.assertFalse(isAscendingByPromotedProductName(ppqpt2PositiveProductList, "ad"), "Products name are not sorted in ascending");
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

    public static boolean isAscendingByPrice(List<WebElement> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            try {
                double currentNode = extractDoubleFromPrice(list.get(i).getText());
                double nextNode = extractDoubleFromPrice(list.get(i + 1).getText());

                if (currentNode > nextNode) {
                    return false;
                }
            } catch (Exception e) {
                System.out.println("Exception while extracting double values: " + e.getMessage());
                return false;
            }
        }
        return true;
    }

    public static boolean isDescendingByPrice(List<WebElement> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            try {
                double currentNode = extractDoubleFromPrice(list.get(i).getText());
                double nextNode = extractDoubleFromPrice(list.get(i + 1).getText());

                if (currentNode < nextNode) {
                    return false;
                }
            } catch (Exception e) {
                System.out.println("Exception while extracting double values: " + e.getMessage());
                return false;
            }
        }
        return true;
    }

    public static boolean isAscendingByPromotedProductName(List<WebElement> list, String prefix){
        boolean isPromoted = false;

        for (int i = 0; i < list.size()-1; i++) {
            try {
                String currentNode = list.get(i).getText().toLowerCase();
                String nextNode = list.get(i + 1).getText().toLowerCase();

                isPromoted = currentNode.contains(prefix);

                if(!isPromoted && nextNode.contains(prefix)) {
                    return false;
                }

                if(currentNode.compareTo(nextNode)>0){
                    return  false;
                }

            } catch (Exception e) {
                System.out.println("Exception while extracting double values: " + e.getMessage());
                return false;
            }
        }
        return  true;
    }

    public static double extractDoubleFromPrice(String stringPrice) {
        try {
            if (validatePrice(stringPrice)) {
                String price = (stringPrice.split("\\$")[1]).replace(",", "");
                return Double.parseDouble(price);
            } else {
                return 0.0;
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            // Handle potential exceptions gracefully
            throw new IllegalArgumentException("Invalid price format", e);
        }
    }

    public static boolean validatePrice(String price) {
        try {
            // Pattern to match valid price format with dollar sign, comma, and digits
            Pattern pattern = Pattern.compile(".*[$,\\d].*");
            Matcher matcher = pattern.matcher(price);
            return matcher.matches();
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid price format", e);
        }
    }

    public void shuffleList() {
        waitForSometime();
        shuffleListButton.click();
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
