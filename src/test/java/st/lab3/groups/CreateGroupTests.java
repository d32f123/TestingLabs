package st.lab3.groups;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import st.lab3.helpers.DriverFactory;
import st.lab3.helpers.MailTester;

import static org.junit.jupiter.api.Assertions.*;


@Tag("CreateTests")
public class CreateGroupTests extends MailTester {

    String groupName = "tempGroup";

    @Test
    void createGroup() {
        DriverFactory.getDrivers().forEach(driver -> {
            driver.get("https://e.mail.ru/addressbook");

            driver.findElement(By.xpath("//a[@class='menu__option__link js-add-label' and contains(text(), 'Создать группу')]"))
                    .click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='is-addressbook-labels-add_in']")));

            driver.findElement(By.xpath("//div[@class='is-addressbook-labels-add_in']//input[@name='label']")).sendKeys(groupName);
            driver.findElement(By.xpath("//div[@class='is-addressbook-labels-add_in']//button[contains(@class, 'confirm-ok')]")).click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//div[@class='menu']//span[contains(@class, 'menu__item__link__text') and text() = '" + groupName +
                            "']"
            )));
            assertTrue(driver.findElement(By.xpath(
                    "//div[@class='menu']//span[contains(@class, 'menu__item__link__text') and text() = '" + groupName +
                            "']"
            )).getText().contains(groupName));


        });
    }

    @Test
    void createEmptyNameGroup() {
        DriverFactory.getDrivers().forEach(driver -> {
            driver.get("https://e.mail.ru/addressbook");

            driver.findElement(By.xpath("//a[@class='menu__option__link js-add-label' and contains(text(), 'Создать группу')]"))
                    .click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='is-addressbook-labels-add_in']")));

            driver.findElement(By.xpath("//div[@class='is-addressbook-labels-add_in']//button[contains(@class, 'confirm-ok')]")).click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//div[contains(@class, 'form__message_error') and contains(text(), 'Вы не указали название группы')]"
            )));

            assertTrue(driver.findElement(By.xpath(
                    "//div[contains(@class, 'form__message_error') and contains(text(), 'Вы не указали название группы')]"
            )).getText().contains("Вы не указали название группы"));
        });
    }

}
