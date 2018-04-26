package st.lab3.messages;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import st.lab3.helpers.DriverFactory;
import st.lab3.helpers.MailHelper;
import st.lab3.helpers.MailTester;

import static org.junit.jupiter.api.Assertions.*;

@Tag("DeleteTests")
public class DeleteMessageTests extends MailTester {

    @Test
    void deleteFromInbox() {
        DriverFactory.getDrivers().forEach(driver -> {
            MailHelper.openMessage(driver, 1);

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[@class='js-txt _js-title notify-message__title__text notify-message__title__text_ok']")));

            driver.findElement(
                    By.xpath("//span[contains(text(), 'Удалить') and not(ancestor::div[contains(@style,'display:none')]) " +
                            "and not(ancestor::div[contains(@style,'display: none')])]")).click();

            String popupText = driver.findElement(
                    By.xpath("//span[@class='js-txt _js-title notify-message__title__text notify-message__title__text_ok']")).getText();

            assertTrue(popupText.startsWith("Удалено 1 письмо"));
        });
    }

    @Test
    void deleteFromTrash() {
        DriverFactory.getDrivers().forEach(driver -> {
            MailHelper.openMessage(driver, "trash/", 1);

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[@class='js-txt _js-title notify-message__title__text notify-message__title__text_ok']")));

            driver.findElement(
                    By.xpath("//span[contains(text(), 'Удалить') and not(ancestor::div[contains(@style,'display:none')]) " +
                            "and not(ancestor::div[contains(@style,'display: none')])]")).click();

            String popupText = driver.findElement(
                    By.xpath("//span[@class='js-txt _js-title notify-message__title__text notify-message__title__text_ok']")).getText();

            assertTrue(popupText.startsWith("Удалено 1 письмо"));
        });
    }

    @Test
    void deleteFromSpam() {
        DriverFactory.getDrivers().forEach(driver -> {
            MailHelper.openMessage(driver, "spam/", 1);

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[@class='js-txt _js-title notify-message__title__text notify-message__title__text_ok']")));

            driver.findElement(
                    By.xpath("//span[contains(text(), 'Удалить') and not(ancestor::div[contains(@style,'display:none')]) " +
                            "and not(ancestor::div[contains(@style,'display: none')])]")).click();

            String popupText = driver.findElement(
                    By.xpath("//span[@class='js-txt _js-title notify-message__title__text notify-message__title__text_ok']")).getText();

            assertTrue(popupText.startsWith("Удалено 1 письмо"));
        });
    }

}
