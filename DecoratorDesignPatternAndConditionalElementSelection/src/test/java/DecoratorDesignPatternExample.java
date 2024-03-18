import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.WebElementDecorator;

import java.util.List;

public class DecoratorDesignPatternExample {

    private WebDriver driver;

    @FindBy(className="table")
    WebElement table;

    @FindBy(id="totalAgreedPrice")
    WebElement totalAgreePrice;

    @BeforeTest
    public void init(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        PageFactory.initElements(driver, this);
    }



    @Test
    public void verifyConditionSelectionUsingDesignPattern() {

        String path = System.getProperty("user.dir");
        driver.get("file:///"+path+"/src/test/resources/webApp/Example.html");

        double expectedAgreedPrice, actualAgreedPrice = 0.0;

        var rows = table.findElements(By.className("rows"));
        expectedAgreedPrice = calculateTotalAgreedPrice(rows, driver);

        actualAgreedPrice = convertDollarCurrencyToDouble(totalAgreePrice.getText());
        Assert.assertEquals(actualAgreedPrice,expectedAgreedPrice);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("alert('" + expectedAgreedPrice + "');");
    }

    private double calculateTotalAgreedPrice(List<WebElement> rows, WebDriver driver) {
        double totalAgreedPrice = 0.0;

        for (WebElement row : rows) {
            //Querying nested table row element if the checkbox is checked
            WebElement element = row.findElement(By.xpath(".//input[@type='checkbox']"));
            if (element.isSelected()) {
                // Implementing decorator pattern on CheckBox element
                new WebElementDecorator(element, driver).decorateCheckBox();
                waitForSomeTime(1);

                WebElement agreedPriceTextBox = row.findElement(By.xpath(".//input[@placeholder='T.B.A']"));

                // Implementing decorator pattern on TextBox element
                new WebElementDecorator(agreedPriceTextBox, driver).decorateTextBox();
                waitForSomeTime(1);

                String agreeCurrencyPrice = agreedPriceTextBox.getAttribute("value");
                try {
                    totalAgreedPrice += convertDollarCurrencyToDouble(agreeCurrencyPrice);
                } catch (NumberFormatException e) {
                    System.out.println("Error: Unable to convert currency string to double.");
                    e.printStackTrace();
                }
            }
        }
        return totalAgreedPrice;
    }

    private double convertDollarCurrencyToDouble(String currencyString) throws NumberFormatException {
        // Remove any non-numeric characters except '.' and ','
        currencyString = currencyString.replaceAll("[^0-9.,]", "");

        // Replace ',' with ''
        currencyString = currencyString.replace(",", "");

        // Parse the string to double
        return Double.parseDouble(currencyString);
    }


    @AfterMethod()
    public void quitDriver() {
        waitForSomeTime(5);
        driver.quit();
    }

    private void waitForSomeTime(int timeout){
        try {
            Thread.sleep(timeout * 500L);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
