package st.lab3.messages;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import st.lab3.helpers.DriverFactory;
import st.lab3.helpers.MailTester;

import static org.junit.jupiter.api.Assertions.*;

@Tag("CreateTests")
public class SendMessageTests extends MailTester {

    private void sendMail(WebDriver driver) {
        // send
        driver.findElement(By.xpath("//div[@data-mnemo='toolbar-compose']//span[contains(text(), 'Отправить')]")).click();
        try {
            driver.switchTo().alert().accept();
            driver.findElement(By.xpath("//div[@class='compose-head']//textarea[@class='js-input compose__labels__input']")).sendKeys(RECIPIENT_EMAIL);
            driver.findElement(By.xpath("//div[@data-mnemo='toolbar-compose']//span[contains(text(), 'Отправить')]")).click();
        } catch (NoAlertPresentException ex) { } finally {
            driver.switchTo().defaultContent();
        }
    }

    @Test
    void validLetterSendTest() {
        DriverFactory.getDrivers().forEach(driver -> {
            // act
            WebElement composeButton = driver.findElement(By.cssSelector("a.b-toolbar__btn"));
            composeButton.click();


            new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[@class='compose-head']//textarea[@class='js-input compose__labels__input']")));

            // input recipent
            driver.findElement(By.xpath("//div[@class='compose-head']//textarea[@class='js-input compose__labels__input']")).sendKeys(RECIPIENT_EMAIL);
            // input topic
            driver.findElement(By.xpath("//div[@class='compose-head']//input[@name='Subject']")).click();
            driver.findElement(By.xpath("//div[@class='compose-head']//input[@name='Subject']")).sendKeys(THEME_EMAIL);

            // input main body
            driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='compose__editor']//iframe")));
            driver.findElement(By.xpath("//body[@class='mceContentBody compose2']")).click();
            driver.findElement(By.xpath("//body[@class='mceContentBody compose2']")).sendKeys(BODY_EMAIL);

            // very important(!)
            driver.switchTo().defaultContent();

            // send
            sendMail(driver);

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message-sent__title")));

            // assert
            driver.get("https://e.mail.ru/messages/sent/");

            WebElement email = driver.findElement(By.xpath("//div[@class='b-datalist__item js-datalist-item'][1]"));
            assertTrue(email.getText().contains(BODY_EMAIL));
            assertTrue(email.getText().contains(RECIPIENT_EMAIL));
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
            driver.findElement(By.xpath("//div[@class='compose-head']//textarea[@class='js-input compose__labels__input']")).sendKeys(RECIPIENT_EMAIL);
            driver.findElement(By.xpath("//div[@class='compose-head']//input[@name='Subject']")).click();
            driver.findElement(By.xpath("//div[@class='compose-head']//input[@name='Subject']")).sendKeys(THEME_EMAIL);



            // send
            sendMail(driver);

            // confirm send
            new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[@class='is-compose-empty_in']//button[@type='submit']")
            ));
            driver.findElement(By.xpath("//div[@class='is-compose-empty_in']//button[@type='submit']")).click();



            // assert
            driver.get("https://e.mail.ru/messages/sent/");

            assertTrue(driver.findElement(By.xpath("//div[@class='b-datalist__item js-datalist-item'][1]")).getText().contains(RECIPIENT_EMAIL));
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
            driver.findElement(By.xpath("//div[@class='compose-head']//textarea[@class='js-input compose__labels__input']")).sendKeys(RECIPIENT_EMAIL);

            driver.switchTo().frame(driver.findElement(
                    By.xpath("//div[@class='compose__editor']//iframe")));
            driver.findElement(By.xpath("//body[@class='mceContentBody compose2']")).click();
            driver.findElement(By.xpath("//body[@class='mceContentBody compose2']")).sendKeys(BODY_EMAIL);

            driver.switchTo().defaultContent();

            // send
            sendMail(driver);

            // confirm send
            //driver.findElement(By.cssSelector(".is-compose-empty_in > form:nth-child(1) > div:nth-child(2) > button:nth-child(1)")).click();


            // assert
            driver.get("https://e.mail.ru/messages/sent/");

            WebElement email = driver.findElement(By.xpath("//div[@class='b-datalist__item js-datalist-item'][1]"));
            assertTrue(email.getText().contains(BODY_EMAIL));
            assertTrue(email.getText().contains(RECIPIENT_EMAIL));
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
            driver.findElement(By.xpath("//div[@class='compose-head']//textarea[@class='js-input compose__labels__input']")).sendKeys(RECIPIENT_EMAIL);


            Actions actions = new Actions(driver);
            // press cancel
            actions.moveToElement(driver.findElement(By.xpath("//div[@data-mnemo='toolbar-compose']//span[@class='b-toolbar__btn__text' and text() = 'Отмена']")))
                    .doubleClick()
                    .build().perform();

            try {
                driver.switchTo().alert().accept();
            } catch (NoAlertPresentException ex) { } finally {
                driver.switchTo().defaultContent();
            }

            // assert
            new WebDriverWait(driver, 5).until(d -> !d.getTitle().contains("Новое письмо"));
            assertFalse(driver.getTitle().contains("Новое письмо"));
        });
    }
}
