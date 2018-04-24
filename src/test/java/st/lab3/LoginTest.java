package st.lab3;


import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    Lock sequential = new ReentrantLock();

    @BeforeEach
    void setUp() {
        sequential.lock();
    }

    @AfterEach
    void tearDown() {
        sequential.unlock();
    }

    @AfterAll
    static void tearDownAll() {
        DriverFactory.tearDown();
    }

    @Test
    void loginTest() {
        for (WebDriver driver : DriverFactory.getDrivers(false)) {
            driver.get("http://e.mail.ru/login");
            new WebDriverWait(driver, 10).until(d -> d.getTitle().toLowerCase().startsWith("вход"));
            WebElement frame = driver.findElement(By.xpath("//*[@id=\"auth-form\"]/div/div/iframe"));
            driver.switchTo().frame(frame);
            WebElement form = driver.findElement(By.xpath("//*[@id=\"frame\"]"));
            WebElement nameField = form.findElement(By.xpath(".//span[@class='b-email__name']//input"));
            WebElement passField = form.findElement(By.xpath(".//div[contains(@class, 'b-form-row_password')]//input"));

            nameField.sendKeys("d32f123");
            passField.sendKeys("mciBMg(4ItZ7");

            passField.submit();

            driver.switchTo().defaultContent();

            (new WebDriverWait(driver, 5)).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("выход")));

            assertTrue(driver.getTitle().toLowerCase().startsWith("входящие"));
            driver.findElement(By.linkText("выход")).click();
            new WebDriverWait(driver, 2).until(d -> d.getTitle().toLowerCase().startsWith("mail.ru"));
        }
    }

    @Test
    void incorrectPasswordTest() {
        for (WebDriver driver : DriverFactory.getDrivers(false)) {
            driver.get("http://e.mail.ru/login");
            new WebDriverWait(driver, 10).until(d -> d.getTitle().toLowerCase().startsWith("вход"));
            WebElement frame = driver.findElement(By.xpath("//*[@id=\"auth-form\"]/div/div/iframe"));
            driver.switchTo().frame(frame);
            WebElement form = driver.findElement(By.xpath("//*[@id=\"frame\"]"));
            WebElement nameField = form.findElement(By.xpath(".//span[@class='b-email__name']//input"));
            WebElement passField = form.findElement(By.xpath(".//div[contains(@class, 'b-form-row_password')]//input"));

            nameField.sendKeys("d32f123");
            passField.sendKeys("mciBMg(4It7");

            passField.submit();

            (new WebDriverWait(driver, 10)).until((ExpectedCondition<Boolean>) d -> d.getTitle().toLowerCase().startsWith("авторизация"));

            driver.switchTo().defaultContent();

            WebElement errorDiv = driver.findElement(By.xpath("//*[@id='frame']/div[@class='b-login__errors']"));
            assertTrue(errorDiv.getText().startsWith("Неверное имя пользователя или пароль"));
        }
    }

}
