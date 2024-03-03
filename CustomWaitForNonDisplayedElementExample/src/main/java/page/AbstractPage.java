package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {

    public AbstractPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
