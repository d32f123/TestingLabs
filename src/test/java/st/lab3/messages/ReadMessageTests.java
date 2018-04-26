package st.lab3.messages;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import st.lab3.helpers.DriverFactory;
import st.lab3.helpers.MailHelper;
import st.lab3.helpers.MailTester;

import static org.junit.jupiter.api.Assertions.*;

@Tag("IntermediateTests")
public class ReadMessageTests extends MailTester {

    @BeforeAll
    protected static void preAll() {
        DriverFactory.getDrivers();
        MailHelper.sendMessage(RECIPIENT_EMAIL, THEME_EMAIL, BODY_EMAIL);
    }
    @Test
    void openEMail() {
        DriverFactory.getDrivers().forEach(driver -> {
            driver.get("https://e.mail.ru/messages/inbox/");

            driver.findElement(By.xpath("//div[contains(@class, 'b-datalist__item js-datalist-item')][1]")).click();

            new WebDriverWait(driver, 5).until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='js-helper js-readmsg-msg']")));
            new WebDriverWait(driver, 5).until(ExpectedConditions.titleIs("Почта Mail.Ru"));

            assertEquals("Почта Mail.Ru", driver.getTitle());
        });
    }

    @Test
    void checkEmailTopic() {
        DriverFactory.getDrivers().forEach(driver -> {
            driver.get("http://e.mail.ru/messages/inbox");

            driver.findElement(By.xpath("//div[contains(@class, 'b-datalist__item js-datalist-item')][1]")).click();

            new WebDriverWait(driver, 5).until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='js-helper js-readmsg-msg']")));

            assertEquals(THEME_EMAIL, driver.findElement(By.xpath("//div[@class='b-letter__head__subj__text']")).getText());
        });
    }

    @Test
    void checkEmailBody() {
        DriverFactory.getDrivers().forEach(driver -> {
            driver.get("http://e.mail.ru/messages/inbox");

            driver.findElement(By.xpath("//div[contains(@class, 'b-datalist__item js-datalist-item')][1]")).click();

            new WebDriverWait(driver, 5).until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='js-helper js-readmsg-msg']")));

            assertEquals(BODY_EMAIL, driver.findElement(By.xpath("//div[@class='js-helper js-readmsg-msg']/div/div/div")).getText());
        });
    }

}
