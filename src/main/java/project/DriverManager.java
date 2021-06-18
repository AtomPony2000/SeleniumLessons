package project;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Properties;

import static java.lang.System.setProperty;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import static project.properties.TestProperties.getInstance;

public class DriverManager {

    protected static WebDriver driver;
    private static final Properties properties = getInstance().getProperties();

    public static WebDriver getWebDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    public static void initDriver() {
        driver = new ChromeDriver();
        setProperty(properties.getProperty("WEB_DRIVER"), properties.getProperty("WEB_DRIVER_PATH"));
        driver.get(properties.getProperty("HOSTNAME"));
        driver.manage().timeouts().implicitlyWait(ofSeconds(10));
        driver.manage().timeouts().setScriptTimeout(ofMillis(500));
        driver.manage().window().maximize();
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
