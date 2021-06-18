package LoginTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

class FirstTest {

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));


    @BeforeEach
    public void before() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().setScriptTimeout(Duration.ofMillis(500));
        driver.manage().window().maximize();

    }

    //    @Test
    void test() {
        //Шаг 1: Авторизация
        wait.until(visibilityOf(driver.findElement(By.xpath("//form[@id='login-form']"))));

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        wait.until(visibilityOf(driver.findElement(By.xpath("//h1[@class='oro-subtitle']"))));

        //Шаг 2: Перейти в командировки
        WebElement costsList = driver.findElement(By.xpath(
                "//ul[contains(@class, 'main-menu')]/li/a/span[text()='Расходы']"));
        costsList.click();
        wait.until(visibilityOf(costsList.findElement(By.xpath(
                "./ancestor::li//ul[@class='dropdown-menu menu_level_1']"))));
        driver.findElement(By.xpath("//span[text()='Командировки']")).click();
        loading();

        //Шаг 3: Фильтр
        driver.findElement(By.xpath("//div[@class='filter-item oro-drop']/div[contains(text(), 'Стадия')]")).click();
        wait.until(visibilityOf(
                driver.findElement(By.xpath("//div[contains(@class,'ui-multiselect-menu ui-corner-all')]"))));
        driver.findElement(By.xpath
                ("//div[contains(@class,'ui-multiselect-menu ui-corner-all')]//input[@type='search']")).
                sendKeys("Согласование с ОСР");
        driver.findElement(By.xpath("//label[@title='Согласование с ОСР']")).click();
        loading();

        String id = driver.findElement(By.xpath("//td[text()='Питер']/parent::tr/td[contains(@class, 'name')]"))
                .getText();
        driver.findElement(By.xpath("//div[@class='filter-item oro-drop']/div[contains(text(), 'Номер')]")).click();
        wait.until(visibilityOf(
                driver.findElement(By.xpath("//input[@name='value']")))).sendKeys(id, Keys.ENTER);
        loading();

        //Шаг 4: Переход в командировку
        driver.findElement(By.xpath(String.format("//td[contains(@class, 'grid-body-cell-name')][text() = '%s']", id)))
                .click();
        loading();
        WebElement actualId = driver.findElement(By.xpath("//h1[@class='user-name']"));
        wait.until(visibilityOf(actualId));
        assertEquals(id, actualId.getText(), "Мы попали на неверную страницу");

        //Шаг 5: Отменить
        driver.findElement(By.xpath("//a[@data-transition-label='Отменить']")).click();
        loading();
        wait.until(visibilityOf(driver.findElement(By.xpath("//div[@role='dialog']"))));
        driver.findElement(By.xpath("//div[@role='dialog']//button[@type='submit']")).click();
        String massage = "Не удается выполнить переход";
        assertTrue(driver.findElement(By.xpath("//div[@class='flash-messages-frame']")).isDisplayed(),
                "Нотификация не отобрахилась");
        assertEquals(massage.replaceAll("о", "Ы"),
                driver.findElement(By.xpath("//div[@class='message']")).getText(),
                String.format("Текст нотификации не совпадает. Ожидаемое значение [%s]", massage));

        System.out.println("Этот человек не поедет в отпуск...ХА-ХА-ХА!");
    }

    @AfterEach
    public void after() {
        driver.quit();
    }

    public void loading() {
        wait.until(invisibilityOf(driver.findElement(
                By.xpath("//div[@class='loader-mask shown']"))));
    }
}
