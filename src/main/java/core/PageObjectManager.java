package core;

import org.openqa.selenium.WebDriver;
import page.HiddenElementPage;

public class PageObjectManager {
    private WebDriver driver;
    private HiddenElementPage hiddenElementPage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public HiddenElementPage getHiddenElementPage() {
        return hiddenElementPage == null ? hiddenElementPage = new HiddenElementPage(driver) : hiddenElementPage;
    }
}
