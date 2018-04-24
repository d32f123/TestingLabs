package st.lab3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SendMessageTests {

    @BeforeAll
    static void preAll() {
        DriverFactory.getDrivers();
    }

    private static final String RECIPENT_EMAIL = "d32f123@mail.ru";
    private static final String THEME_EMAIL = "some theme";
    private static final String BODY_EMAIL = "hello there! how is it going";

    @Test
    void validLetterSendTest() {
        DriverFactory.getDrivers().forEach(driver -> {
            WebElement composeButton = driver.findElement(By.cssSelector("a.b-toolbar__btn"));
            composeButton.click();

            new WebDriverWait(driver, 5).until(ExpectedConditions.titleContains("Новое письмо"));
            driver.findElement(By.cssSelector("textarea.js-input:nth-child(6)")).sendKeys(RECIPENT_EMAIL);
            driver.findElement(By.cssSelector(".compose-head__row_nohr > div:nth-child(2) > div:nth-child(1) > input:nth-child(1)"))
                    .sendKeys(THEME_EMAIL);
            driver.switchTo().frame(driver.findElement(
                    By.xpath("//div[@class='compose__editor']/div/div/")));
            driver.findElement(By.xpath("//body")).sendKeys(BODY_EMAIL);

            driver.switchTo().defaultContent();

            driver.findElement(By.cssSelector("div.b-toolbar__item_:nth-child(1) > div:nth-child(1)")).click();
        });
    }

}
