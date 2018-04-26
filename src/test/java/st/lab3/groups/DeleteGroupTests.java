package st.lab3.groups;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import st.lab3.helpers.DriverFactory;
import st.lab3.helpers.MailTester;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("DeleteTests")
public class DeleteGroupTests extends MailTester {

    String groupName = "tempGroup";

    @Test
    void deleteGroup() {
        DriverFactory.getDrivers().forEach(driver -> {
            driver.get("https://e.mail.ru/addressbook");

            Actions actions = new Actions(driver);

            actions.moveToElement(driver.findElement(By.xpath(
                    "//div[@class='menu']//a[contains(@class,'menu__item__link_folders') and descendant::span[. = '" +
                            groupName + "']]"
            ))).click(driver.findElement(By.xpath(
                    "//div[@class='menu']//a[contains(@class,'menu__item__link_folders') and descendant::span[. = '" +
                            groupName + "']]/i[contains(@class, 'js-edit-label')]"
            ))).build().perform();

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//a[contains(@class, 'js-remove-label') and ancestor::div[" +
                            "not(contains(@style, 'display: none') or contains(@style, 'display:none'))] " +
                            "and contains(text(), 'Удалить')]"
            )));

            driver.findElement(By.xpath(
                    "//a[contains(@class, 'js-remove-label') and ancestor::div[" +
                            "not(contains(@style, 'display: none') or contains(@style, 'display:none'))] " +
                            "and contains(text(), 'Удалить')]"
            )).click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//div[contains(@class,'is-addressbook-labels-remove_in')]//button[contains(@class, 'confirm-ok')]"
            )));

            driver.findElement(By.xpath(
                    "//div[contains(@class,'is-addressbook-labels-remove_in')]//button[contains(@class, 'confirm-ok')]"
            )).click();

            assertThrows(Exception.class, () -> driver.findElement(By.xpath(
                    "//div[@class='menu']//span[contains(@class, 'menu__item__link__text') and text() = '" + groupName +
                            "']"
            )));

        });
    }


}
