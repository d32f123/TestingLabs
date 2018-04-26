package st.lab3;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import st.lab3.helpers.DriverFactory;
import st.lab3.helpers.MailTester;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("CreateTests")
public class FilterTests extends MailTester {

    @Test
    void filterByTopic() {
        DriverFactory.getDrivers().forEach(driver -> {
            String testFolder = "testFolder";
            String testSubject = "testSubject";

            driver.get("https://e.mail.ru/settings/filters");

            // click new filter button
            driver.findElement(By.xpath("//span[. = 'Добавить фильтр']")).click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//div[@class='js-conditions-container']"
            )));

            // find the row where we set up the filter
            WebElement row = driver.findElement(By.xpath(
                    "//div[@class='js-conditions-container']/div[not(contains(@style,'display:none') or contains(@style,'display: none'))][1]"
            ));

            // click the button to choose type of filter
            row.findElement(By.xpath(".//a[contains(@class,'filters__dropdown__link')]")).click();

            // select Subject filter
            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//div[@class='js-conditions-container']/div[not(contains(@style,'display:none') or contains(@style,'display: none'))][1]" +
                            "//ul[contains(@class,'filters__dropdown__menu')]"
            )));

            row.findElement(By.xpath(".//ul[contains(@class,'filters__dropdown__menu')]//a[@data-id='Subject']")).click();

            // subject name
            row.findElement(By.xpath(".//textarea[@data-base-name='Condition']")).sendKeys(testSubject);

            // locate element where actions go
            WebElement resultConditionElement = driver.findElement(By.xpath(
                    "//div[@class='form__row' and descendant::div[@class='form__row__label' and contains(text(),'То')]]"
            ));

            // click on select folder
            resultConditionElement.findElement(By.xpath(".//div[@class = 'form__select__box__text js-text']")).click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//div[@class='form__row' and descendant::div[@class='form__row__label' and contains(text(),'То')]]" +
                     "//div[contains(@class,'js-select-list')]//label[@data-name='" + testFolder + "']"
            )));

            // click on needed folder
            resultConditionElement.findElement(By.xpath(
                    ".//div[contains(@class,'js-select-list')]//label[@data-name='" + testFolder + "']"
            )).click();

            driver.findElement(By.xpath("//button[@type='submit']/span[contains(text(),'Сохранить')]")).click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//span[@class='js-txt _js-title notify-message__title__text notify-message__title__text_ok']"
            )));

            assertTrue(driver.findElement(By.xpath(
                    "//span[@class='js-txt _js-title notify-message__title__text notify-message__title__text_ok']"
            )).getText().contains("Фильтр добавлен"));
        });
    }

    @Test
    void filterByCopies() {
        DriverFactory.getDrivers().forEach(driver -> {
            String testFolder = "testFolder";
            String copyAddress = "d32f123@yandex.ru";

            driver.get("https://e.mail.ru/settings/filters");

            // click new filter button
            driver.findElement(By.xpath("//span[. = 'Добавить фильтр']")).click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//div[@class='js-conditions-container']"
            )));

            // find the row where we set up the filter
            WebElement row = driver.findElement(By.xpath(
                    "//div[@class='js-conditions-container']/div[not(contains(@style,'display:none') or contains(@style,'display: none'))][1]"
            ));

            // click the button to choose type of filter
            row.findElement(By.xpath(".//a[contains(@class,'filters__dropdown__link')]")).click();

            // select Subject filter
            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//div[@class='js-conditions-container']/div[not(contains(@style,'display:none') or contains(@style,'display: none'))][1]" +
                            "//ul[contains(@class,'filters__dropdown__menu')]"
            )));

            row.findElement(By.xpath(".//ul[contains(@class,'filters__dropdown__menu')]//a[@data-id='Cc']")).click();

            // copy name
            row.findElement(By.xpath(".//textarea[@data-base-name='Condition']")).sendKeys(copyAddress);

            // locate element where actions go
            WebElement resultConditionElement = driver.findElement(By.xpath(
                    "//div[@class='form__row' and descendant::div[@class='form__row__label' and contains(text(),'То')]]"
            ));

            // click on select folder
            resultConditionElement.findElement(By.xpath(".//div[@class = 'form__select__box__text js-text']")).click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//div[@class='form__row' and descendant::div[@class='form__row__label' and contains(text(),'То')]]" +
                            "//div[contains(@class,'js-select-list')]//label[@data-name='" + testFolder + "']"
            )));

            // click on needed folder
            resultConditionElement.findElement(By.xpath(
                    ".//div[contains(@class,'js-select-list')]//label[@data-name='" + testFolder + "']"
            )).click();

            driver.findElement(By.xpath("//button[@type='submit']/span[contains(text(),'Сохранить')]")).click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//span[@class='js-txt _js-title notify-message__title__text notify-message__title__text_ok']"
            )));

            assertTrue(driver.findElement(By.xpath(
                    "//span[@class='js-txt _js-title notify-message__title__text notify-message__title__text_ok']"
            )).getText().contains("Фильтр добавлен"));
        });
    }
}
