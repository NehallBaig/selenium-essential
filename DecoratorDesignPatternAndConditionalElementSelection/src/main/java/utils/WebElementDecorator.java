package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebElementDecorator  implements ElementDecorator  {

    private static final Logger logger = LoggerFactory.getLogger(WebElementDecorator.class);

    private final WebElement webElement;
    private final WebDriver driver;

    public WebElementDecorator(WebElement webElement, WebDriver driver) {
        this.webElement = webElement;
        this.driver = driver;
    }

    @Override
    public void decorateTextBox() {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border = '2px solid green';", webElement);
        } catch (Exception e) {
            logger.error("Error occurred while decorating text box: {}", e.getMessage());
        }
    }

    @Override
    public void decorateCheckBox() {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style= 'accent-color: green;'", webElement);
        } catch (Exception e) {
            logger.error("Error occurred while decorating check box: {}", e.getMessage());
        }
    }
}
