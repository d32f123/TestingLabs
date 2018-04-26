package st.lab3.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import st.lab3.helpers.DriverFactory;

public class MailHelper {
    public static void openMessage(WebDriver driver, int index) {
        openMessage(driver, "inbox/", index);
    }

    public static void openMessage(WebDriver driver, String postfix, int index) {
        driver.get("https://e.mail.ru/messages/" + postfix);

        driver.findElement(By.xpath("//div[contains(@class, 'b-datalist__item js-datalist-item')][" + index + "]")).click();

        new WebDriverWait(driver, 5).until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='js-helper js-readmsg-msg']")));
        new WebDriverWait(driver, 5).until(ExpectedConditions.titleIs("Почта Mail.Ru"));
    }

    public static void sendMessage(String recipient, String topic, String body) {
        WebDriver driver = DriverFactory.getDrivers().iterator().next();
        WebElement composeButton = driver.findElement(By.cssSelector("a.b-toolbar__btn"));
        composeButton.click();


        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@class='compose-head']//textarea[@class='js-input compose__labels__input']")));

        // input recipent
        driver.findElement(By.xpath("//div[@class='compose-head']//textarea[@class='js-input compose__labels__input']")).sendKeys(recipient);
        // input topic
        driver.findElement(By.cssSelector(".compose-head__row_nohr > div:nth-child(2) > div:nth-child(1) > input:nth-child(1)")).click();
        driver.findElement(By.cssSelector(".compose-head__row_nohr > div:nth-child(2) > div:nth-child(1) > input:nth-child(1)")).sendKeys(topic);

        // input main body
        driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='compose__editor']//iframe")));
        driver.findElement(By.xpath("//body[@class='mceContentBody compose2']")).click();
        driver.findElement(By.xpath("//body[@class='mceContentBody compose2']")).sendKeys(body);

        // very important(!)
        driver.switchTo().defaultContent();

        // send
        driver.findElement(By.cssSelector("div.b-toolbar__item_:nth-child(1) > div:nth-child(1)")).click();

        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message-sent__title")));
    }
}
