package st.lab3.folders;

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

@Tag("IntermediateTests")
public class MessageSortTests extends MailTester {

    @Test
    void inboxToOutbox() {
        DriverFactory.getDrivers().forEach(driver -> {
            MailHelper.openMessage(driver, 1);
            //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            WebElement toolbar = driver.findElement(By.xpath("//div[@id='b-toolbar__right']"));

            toolbar.findElement(By.xpath("./div[not(contains(@style, 'display: none'))]//div[contains(@title, 'Переместить') or contains(@data-title, 'Переместить')]"))
                    .click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[@class='b-dropdown__list__item' and @data-text='Отправленные']")));
            driver.findElement(By.xpath("//a[@class='b-dropdown__list__item' and @data-text='Отправленные']")).click();



            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[@class='js-txt _js-title notify-message__title__text notify-message__title__text_ok']")));

            String popupText = driver.findElement(
                    By.xpath("//span[@class='js-txt _js-title notify-message__title__text notify-message__title__text_ok']")).getText();

            assertTrue(popupText.startsWith("Письмо перемещено в папку «Отправленные»"));

        });
    }

    @Test
    void inboxToDeleted() {
        DriverFactory.getDrivers().forEach(driver -> {
            MailHelper.openMessage(driver, 1);

            WebElement toolbar = driver.findElement(By.xpath("//div[@id='b-toolbar__right']"));

            toolbar.findElement(By.xpath("./div[not(contains(@style, 'display: none'))]//div[contains(@title, 'Переместить') or contains(@data-title, 'Переместить')]"))
                    .click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[@class='b-dropdown__list__item' and @data-text='Корзина']")));
            driver.findElement(By.xpath("//a[@class='b-dropdown__list__item' and @data-text='Корзина']")).click();



            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[@class='js-txt _js-title notify-message__title__text notify-message__title__text_ok']")));

            String popupText = driver.findElement(
                    By.xpath("//span[@class='js-txt _js-title notify-message__title__text notify-message__title__text_ok']")).getText();

            assertTrue(popupText.startsWith("Письмо перемещено в Корзину"));
        });
    }

    @Test
    void inboxToSpam() {
        DriverFactory.getDrivers().forEach(driver -> {
            MailHelper.openMessage(driver, 1);
            //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            WebElement toolbar = driver.findElement(By.xpath("//div[@id='b-toolbar__right']"));

            toolbar.findElement(By.xpath("./div[not(contains(@style, 'display: none'))]//div[contains(@title, 'Переместить') or contains(@data-title, 'Переместить')]"))
                    .click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[@class='b-dropdown__list__item' and @data-text='Спам']")));
            driver.findElement(By.xpath("//a[@class='b-dropdown__list__item' and @data-text='Спам']")).click();



            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[@class='js-txt _js-title notify-message__title__text notify-message__title__text_ok']")));

            String popupText = driver.findElement(
                    By.xpath("//span[@class='js-txt _js-title notify-message__title__text notify-message__title__text_ok']")).getText();

            assertTrue(popupText.startsWith("Письмо перемещено в папку «Спам»"));

        });
    }

    @Test
    void inboxToInbox() {
        DriverFactory.getDrivers().forEach(driver -> {
            MailHelper.openMessage(driver, 1);
            //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            WebElement toolbar = driver.findElement(By.xpath("//div[@id='b-toolbar__right']"));

            toolbar.findElement(By.xpath("./div[not(contains(@style, 'display: none'))]//div[contains(@title, 'Переместить') or contains(@data-title, 'Переместить')]"))
                    .click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[contains(@class, 'b-dropdown__list__item') and @data-text='Входящие']")));
            driver.findElement(By.xpath("//a[contains(@class, 'b-dropdown__list__item') and @data-text='Входящие']")).click();



            new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//span[@class='js-txt _js-title notify-message__title__text notify-message__title__text_ok']")));


            assertFalse(driver.findElement(
                    By.xpath("//span[@class='js-txt _js-title notify-message__title__text notify-message__title__text_ok']")).isDisplayed());

        });
    }

    @Test
    void inboxToSpamMultiple() {
        DriverFactory.getDrivers().forEach(driver -> {
            driver.get("http://e.mail.ru/messages/inbox");

            driver.findElement(By.xpath("//div[contains(@class,'b-datalist__item js-datalist-item')][1]" +
                    "//div[contains(@class,'js-item-checkbox b-datalist__item__cbx')]")).click();
            driver.findElement(By.xpath("//div[contains(@class,'b-datalist__item js-datalist-item')][2]" +
                    "//div[contains(@class,'js-item-checkbox b-datalist__item__cbx')]")).click();

            driver.findElement(
                    By.xpath("//span[contains(text(), 'Переместить') and not(ancestor::div[contains(@style,'display:none')]) " +
                            "and not(ancestor::div[contains(@style,'display: none')])]")).click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[@class='b-dropdown__list__item' and @data-text='Спам']")));
            driver.findElement(By.xpath("//a[@class='b-dropdown__list__item' and @data-text='Спам']")).click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[@class='js-txt _js-title notify-message__title__text notify-message__title__text_ok']")));

            String popupText = driver.findElement(
                    By.xpath("//span[@class='js-txt _js-title notify-message__title__text notify-message__title__text_ok']")).getText();

            assertTrue(popupText.startsWith("2 письма перемещено в папку «Спам»"));
        });
    }

    @Test
    void inboxToUserFolder() {
        DriverFactory.getDrivers().forEach(driver -> {
            MailHelper.openMessage(driver, 1);
            //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            WebElement toolbar = driver.findElement(By.xpath("//div[@id='b-toolbar__right']"));

            toolbar.findElement(By.xpath("./div[not(contains(@style, 'display: none'))]//div[contains(@title, 'Переместить') or contains(@data-title, 'Переместить')]"))
                    .click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[@class='b-dropdown__list__item' and @data-text='temp']")));
            driver.findElement(By.xpath("//a[@class='b-dropdown__list__item' and @data-text='temp']")).click();



            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[@class='js-txt _js-title notify-message__title__text notify-message__title__text_ok']")));

            String popupText = driver.findElement(
                    By.xpath("//span[@class='js-txt _js-title notify-message__title__text notify-message__title__text_ok']")).getText();

            assertTrue(popupText.startsWith("Письмо перемещено в папку «temp»"));

        });
    }
}
