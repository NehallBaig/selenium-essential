package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.time.Duration;


public class DriverManager {

    public WebDriver driver;

    public WebDriver launchDriver() {
        if (driver == null) {
            String browserName = "chrome";
            try {
                if (browserName.equalsIgnoreCase("chrome")) {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--remote-allow-origins=*");
                    driver = new ChromeDriver(options);
                } else if (browserName.equalsIgnoreCase("firefox")) {
                    //  WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                } else if (browserName.equalsIgnoreCase("ie")) {
                    // WebDriverManager.iedriver().setup();
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

    public void quitDriver() {
        driver.quit();
    }

}
