package core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Utils {

    public static boolean waitForNonDisplayedElement(WebDriver driver, WebElement element, int sec) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
            wait.until(elementNotToBeDisplayed(element));
            return true;
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            System.out.println(e);
            return false;
        }
    }

    private static ExpectedCondition elementNotToBeDisplayed(WebElement element) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    return !element.getSize().equals(new Dimension(0, 0));

                } catch (NoSuchElementException | StaleElementReferenceException e) {
                    return false;
                }
            }
        };
    }
}
