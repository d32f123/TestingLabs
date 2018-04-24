package st.lab3;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.jupiter.api.Assertions.*;

public class SendMessageTests {

    Lock sequential = new ReentrantLock();

    @BeforeEach
    void setUp() {
        sequential.lock();
    }

    @AfterEach
    void tearDown() {
        sequential.unlock();
    }

    @BeforeAll
    static void preAll() {
        DriverFactory.getDrivers();
    }

    @AfterAll
    static void tearAll() {
        //DriverFactory.tearDown();
    }

    private static final String RECIPENT_EMAIL = "d32f123@mail.ru";
    private static final String THEME_EMAIL = "some theme";
    private static final String BODY_EMAIL = "hello there! how is it going";

    @Test
    void validLetterSendTest() {
        DriverFactory.getDrivers().forEach(driver -> {
            // act
            WebElement composeButton = driver.findElement(By.cssSelector("a.b-toolbar__btn"));
            composeButton.click();


            new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[@class='compose-head']//textarea[@class='js-input compose__labels__input']")));

            // input recipent
            driver.findElement(By.xpath("//div[@class='compose-head']//textarea[@class='js-input compose__labels__input']")).sendKeys(RECIPENT_EMAIL);
            // input topic
            driver.findElement(By.cssSelector(".compose-head__row_nohr > div:nth-child(2) > div:nth-child(1) > input:nth-child(1)")).click();
            driver.findElement(By.cssSelector(".compose-head__row_nohr > div:nth-child(2) > div:nth-child(1) > input:nth-child(1)")).sendKeys(THEME_EMAIL);

            // input main body
            driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='compose__editor']//iframe")));
            driver.findElement(By.xpath("//body[@class='mceContentBody compose2']")).click();
            driver.findElement(By.xpath("//body[@class='mceContentBody compose2']")).sendKeys(BODY_EMAIL);

            // very important(!)
            driver.switchTo().defaultContent();

            // send
            driver.findElement(By.cssSelector("div.b-toolbar__item_:nth-child(1) > div:nth-child(1)")).click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message-sent__title")));

            // assert
            driver.get("https://e.mail.ru/messages/sent/");
            new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//div[@class='b-datalist__item js-datalist-item']")));

            WebElement email = driver.findElement(By.xpath("//div[@class='b-datalist__item js-datalist-item'][1]"));
            assertTrue(email.getText().contains(BODY_EMAIL));
            assertTrue(email.getText().contains(RECIPENT_EMAIL));
            assertTrue(email.getText().contains(THEME_EMAIL));
        });
    }

    @Test
    void letterWithTopicOnly() {
        DriverFactory.getDrivers().forEach(driver -> {
            WebElement composeButton = driver.findElement(By.cssSelector("a.b-toolbar__btn"));
            composeButton.click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[@class='compose-head']//textarea[@class='js-input compose__labels__input']")));
            driver.findElement(By.xpath("//div[@class='compose-head']//textarea[@class='js-input compose__labels__input']")).sendKeys(RECIPENT_EMAIL);
            driver.findElement(By.cssSelector(".compose-head__row_nohr > div:nth-child(2) > div:nth-child(1) > input:nth-child(1)")).click();
            driver.findElement(By.cssSelector(".compose-head__row_nohr > div:nth-child(2) > div:nth-child(1) > input:nth-child(1)")).sendKeys(THEME_EMAIL);

            // send
            driver.findElement(By.cssSelector("div.b-toolbar__item_:nth-child(1) > div:nth-child(1)")).click();

            // confirm send
            new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector(".is-compose-empty_in > form:nth-child(1) > div:nth-child(2) > button:nth-child(1)")
            ));
            driver.findElement(By.cssSelector(".is-compose-empty_in > form:nth-child(1) > div:nth-child(2) > button:nth-child(1)")).click();



            // assert
            driver.get("https://e.mail.ru/messages/sent/");
            new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//div[@class='b-datalist__item js-datalist-item']")));

            assertTrue(driver.findElement(By.xpath("//div[@class='b-datalist__item js-datalist-item'][1]")).getText().contains(RECIPENT_EMAIL));
            assertTrue(driver.findElement(By.xpath("//div[@class='b-datalist__item js-datalist-item'][1]")).getText().contains(THEME_EMAIL));
        });
    }

    @Test
    void letterWithBodyOnly() {
        DriverFactory.getDrivers().forEach(driver -> {
            // act
            WebElement composeButton = driver.findElement(By.cssSelector("a.b-toolbar__btn"));
            composeButton.click();
            new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[@class='compose-head']//textarea[@class='js-input compose__labels__input']")));
            driver.findElement(By.xpath("//div[@class='compose-head']//textarea[@class='js-input compose__labels__input']")).sendKeys(RECIPENT_EMAIL);

            driver.switchTo().frame(driver.findElement(
                    By.xpath("//div[@class='compose__editor']//iframe")));
            driver.findElement(By.xpath("//body[@class='mceContentBody compose2']")).click();
            driver.findElement(By.xpath("//body[@class='mceContentBody compose2']")).sendKeys(BODY_EMAIL);

            driver.switchTo().defaultContent();

            // send
            driver.findElement(By.cssSelector("div.b-toolbar__item_:nth-child(1) > div:nth-child(1)")).click();

            // confirm send
            //driver.findElement(By.cssSelector(".is-compose-empty_in > form:nth-child(1) > div:nth-child(2) > button:nth-child(1)")).click();


            // assert
            driver.get("https://e.mail.ru/messages/sent/");
            new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//div[@class='b-datalist__item js-datalist-item']")));

            WebElement email = driver.findElement(By.xpath("//div[@class='b-datalist__item js-datalist-item'][1]"));
            assertTrue(email.getText().contains(BODY_EMAIL));
            assertTrue(email.getText().contains(RECIPENT_EMAIL));
        });
    }

    @Test
    void letterCancelSendTest() {
        DriverFactory.getDrivers().forEach(driver -> {
            // act
            driver.get("http://e.mail.ru/messages/inbox/");
            WebElement composeButton = driver.findElement(By.cssSelector("a.b-toolbar__btn"));
            composeButton.click();
            new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[@class='compose-head']//textarea[@class='js-input compose__labels__input']")));
            driver.findElement(By.xpath("//div[@class='compose-head']//textarea[@class='js-input compose__labels__input']")).sendKeys(RECIPENT_EMAIL);

            // press cancel
            driver.findElement(
                    By.cssSelector("#b-toolbar__right > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > span:nth-child(1)"
                    )).click();

            // assert
            new WebDriverWait(driver, 5).until(d -> !d.getTitle().contains("Новое письмо"));
            assertFalse(driver.getTitle().contains("Новое письмо"));
        });
    }
}
