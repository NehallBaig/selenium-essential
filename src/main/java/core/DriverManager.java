package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class DriverManager {

    public static WebDriver driver;

    public static WebDriver launchDriver() {
        if (driver == null) {
            String browserName = "chrome";
            try {
                if (browserName.equalsIgnoreCase("chrome")) {
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.setHeadless(false);
                    options.addArguments("--remote-allow-origins=*");
                    driver = new ChromeDriver(options);
                } else if (browserName.equalsIgnoreCase("firefox")) {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                } else if (browserName.equalsIgnoreCase("ie")) {
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                }

                driver.manage().window().maximize();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

}
