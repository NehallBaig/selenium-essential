package page;

import core.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HiddenElementPage extends AbstractPage {

    private WebDriver driver;

    public HiddenElementPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "myElement")
    WebElement hiddenElement;

    @FindBy(id = "btnDisplay3Sec")
    WebElement btnDisplay3Sec;
    @FindBy(id = "btnDisplay5Sec")
    WebElement btnDisplay5Sec;
    @FindBy(id = "btnDisplay10Sec")
    WebElement btnDisplay10Sec;

    public boolean isHiddenElementInTheDom() {
        return Utils.waitForNonDisplayedElement(driver, hiddenElement, 20);
    }

    public void clickOnBtnDisplay3Sec() {
        btnDisplay3Sec.click();
    }

    public void clickOnBtnDisplay5Sec() {
        btnDisplay5Sec.click();
    }

    public void clickOnBtnDisplay10Sec() {
        btnDisplay10Sec.click();
    }

}

