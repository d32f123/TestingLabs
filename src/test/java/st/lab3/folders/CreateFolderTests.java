package st.lab3.folders;

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
public class CreateFolderTests extends MailTester {

    @Test
    public void createFolder() {
        DriverFactory.getDrivers().forEach(driver -> {

            String folderName = "fold1";

            driver.get("https://e.mail.ru/settings/folders");
            driver.findElement(By.xpath(".//button[@data-name='newFolder']")).click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//div[@class='is-folder-add_in']"
            )));
            WebElement newFolderWidget = driver.findElement(By.xpath("//div[@class='is-folder-add_in']"));
            newFolderWidget.findElement(By.xpath(".//div[@data-input='name']/div/div[2]/div/input")).sendKeys(folderName);
            newFolderWidget.findElement(By.xpath(".//span[. = 'Добавить']")).click();


            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//span[@class='js-txt _js-title notify-message__title__text notify-message__title__text_ok']"
            )));
            assertTrue(driver.findElement(By.xpath("//span[@class='js-txt _js-title notify-message__title__text notify-message__title__text_ok']")).
                    getText().contains("Папка создана"));
        });
    }

    @Test
    void createInvalidFolder() {
        DriverFactory.getDrivers().forEach(driver -> {

            driver.get("https://e.mail.ru/settings/folders");
            driver.findElement(By.xpath(".//button[@data-name='newFolder']")).click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//div[@class='is-folder-add_in']"
            )));
            WebElement newFolderWidget = driver.findElement(By.xpath("//div[@class='is-folder-add_in']"));
            newFolderWidget.findElement(By.xpath(".//span[. = 'Добавить']")).click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//div[. = 'Заполните обязательное поле']"
            )));

            assertTrue(driver.findElement(By.xpath("//div[. = 'Заполните обязательное поле']")).isDisplayed());
        });
    }

    @Test
    void createProtectedFolder() {
        DriverFactory.getDrivers().forEach(driver -> {

            String folderPassword = "1234";
            String question = "What?";
            String answer = "1234";
            String folderName = "fold2";

            // open folder webpage
            driver.get("https://e.mail.ru/settings/folders");
            // create new folder button
            driver.findElement(By.xpath(".//button[@data-name='newFolder']")).click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//div[@class='is-folder-add_in']"
            )));
            // select new folder widget
            WebElement newFolderWidget = driver.findElement(By.xpath("//div[@class='is-folder-add_in']"));

            // input folder name
            newFolderWidget.findElement(By.xpath(".//div[@data-input='name']//input")).sendKeys(folderName);
            // click password checkbox
            newFolderWidget.findElement(By.xpath(".//div[@data-field-name='secret']//label[@class='b-form-checkbox']")).click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//div[@data-field-name='folder_password']//input"
            )));
            driver.findElement(By.xpath("//div[@data-field-name='folder_password']//input")).sendKeys(folderPassword);
            driver.findElement(By.xpath("//div[@data-field-name='password_repeat']//input")).sendKeys(folderPassword);
            driver.findElement(By.xpath("//div[@data-field-name='question']//input")).sendKeys(question);
            driver.findElement(By.xpath("//div[@data-field-name='answer']//input")).sendKeys(answer);
            driver.findElement(By.xpath("//div[@data-field-name='user_password']//input")).sendKeys(PASSWORD_EMAIL);

            newFolderWidget = driver.findElement(By.xpath("//div[@class='is-folder-add_in']"));
            newFolderWidget.findElement(By.xpath(".//span[. = 'Добавить']")).click();


            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//span[@class='js-txt _js-title notify-message__title__text notify-message__title__text_ok']"
            )));
            assertTrue(driver.findElement(By.xpath("//span[@class='js-txt _js-title notify-message__title__text notify-message__title__text_ok']")).
                    getText().contains("Папка создана"));

        });
    }

    @Test
    void createInvalidProtectedFolder() {
        DriverFactory.getDrivers().forEach(driver -> {

            String folderPassword = "123";
            String question = "What?";
            String answer = "1234";
            String folderName = "fold3";

            // open folder webpage
            driver.get("https://e.mail.ru/settings/folders");
            // create new folder button
            driver.findElement(By.xpath(".//button[@data-name='newFolder']")).click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//div[@class='is-folder-add_in']"
            )));
            // select new folder widget
            WebElement newFolderWidget = driver.findElement(By.xpath("//div[@class='is-folder-add_in']"));

            // input folder name
            newFolderWidget.findElement(By.xpath(".//div[@data-input='name']//input")).sendKeys(folderName);
            // click password checkbox
            newFolderWidget.findElement(By.xpath(".//div[@data-field-name='secret']//label[@class='b-form-checkbox']")).click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//div[@data-field-name='folder_password']//input"
            )));
            driver.findElement(By.xpath("//div[@data-field-name='folder_password']//input")).sendKeys(folderPassword);
            driver.findElement(By.xpath("//div[@data-field-name='password_repeat']//input")).sendKeys(folderPassword);
            driver.findElement(By.xpath("//div[@data-field-name='question']//input")).sendKeys(question);
            driver.findElement(By.xpath("//div[@data-field-name='answer']//input")).sendKeys(answer);
            driver.findElement(By.xpath("//div[@data-field-name='user_password']//input")).sendKeys(PASSWORD_EMAIL);

            newFolderWidget = driver.findElement(By.xpath("//div[@class='is-folder-add_in']"));
            newFolderWidget.findElement(By.xpath(".//span[. = 'Добавить']")).click();


            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//div[@data-field-name='folder_password']//div[contains(@class,'js-password_length')]"
            )));

            assertTrue(driver.findElement(By.xpath("//div[@data-field-name='folder_password']//div[contains(@class,'js-password_length')]")).isDisplayed());

        });
    }

    @Test
    void createProtectedFolderInvalidEmailPassword() {
        DriverFactory.getDrivers().forEach(driver -> {

            String folderPassword = "1234";
            String question = "What?";
            String answer = "1234";
            String folderName = "fold4";

            // open folder webpage
            driver.get("https://e.mail.ru/settings/folders");
            // create new folder button
            driver.findElement(By.xpath(".//button[@data-name='newFolder']")).click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//div[@class='is-folder-add_in']"
            )));
            // select new folder widget
            WebElement newFolderWidget = driver.findElement(By.xpath("//div[@class='is-folder-add_in']"));

            // input folder name
            newFolderWidget.findElement(By.xpath(".//div[@data-input='name']//input")).sendKeys(folderName);
            // click password checkbox
            newFolderWidget.findElement(By.xpath(".//div[@data-field-name='secret']//label[@class='b-form-checkbox']")).click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//div[@data-field-name='folder_password']//input"
            )));
            driver.findElement(By.xpath("//div[@data-field-name='folder_password']//input")).sendKeys(folderPassword);
            driver.findElement(By.xpath("//div[@data-field-name='password_repeat']//input")).sendKeys(folderPassword);
            driver.findElement(By.xpath("//div[@data-field-name='question']//input")).sendKeys(question);
            driver.findElement(By.xpath("//div[@data-field-name='answer']//input")).sendKeys(answer);
            driver.findElement(By.xpath("//div[@data-field-name='user_password']//input")).sendKeys(PASSWORD_EMAIL + "x");

            newFolderWidget = driver.findElement(By.xpath("//div[@class='is-folder-add_in']"));
            newFolderWidget.findElement(By.xpath(".//span[. = 'Добавить']")).click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//div[contains(text(), 'Введен неверный пароль')]"
            )));

            assertTrue(driver.findElement(By.xpath(
                    "//div[contains(text(), 'Введен неверный пароль')]"
            )).isDisplayed());

        });
    }

}
