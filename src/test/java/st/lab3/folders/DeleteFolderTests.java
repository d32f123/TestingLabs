package st.lab3.folders;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import st.lab3.helpers.DriverFactory;
import st.lab3.helpers.MailTester;

import static org.junit.jupiter.api.Assertions.*;

@Tag("DeleteTests")
public class DeleteFolderTests extends MailTester {

    @Test
    void deleteFolder() {
        DriverFactory.getDrivers().forEach(driver -> {
            driver.get("https://e.mail.ru/settings/folders");

            String folderName = "del1";

            WebElement folder = driver.findElement(By.xpath("//div[contains(@class,'b-folders__item') and " +
                    "descendant::div[contains(@class,'b-folders__item-col_title') and text()='" + folderName + "']]"));

            folder.findElement(By.xpath(".//div[contains(@class,'b-folders__item-control') and @data-name='remove']")).click();

            WebElement popup = driver.findElement(By.xpath("//div[contains(@class,'is-folder-remove_in')"));
            popup.findElement(By.xpath(".//button[@data-name='submit']")).click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//span[@class='js-txt _js-title notify-message__title__text notify-message__title__text_ok' and text() = 'Папка удалена']"
            )));

            assertTrue(driver.findElement(By.xpath(
                    "//span[@class='js-txt _js-title notify-message__title__text notify-message__title__text_ok' and text() = 'Папка удалена']"
            )).isDisplayed());
        });
    }

    @Test
    void failToDeleteInboxFolder() {
        DriverFactory.getDrivers().forEach(driver -> {
            driver.get("https://e.mail.ru/settings/folders");

            String folderName = "Входящие";

            WebElement folder = driver.findElement(By.xpath("//div[contains(@class,'b-folders__item') and " +
                    "descendant::div[contains(@class,'b-folders__item-col_title') and text()='" + folderName + "']]"));

            assertThrows(Exception.class, () -> folder.findElement(By.xpath(".//div[contains(@class,'b-folders__item-control') and @data-name='remove']")).click());
        });
    }

}
