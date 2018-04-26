package st.lab3.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collection;

public class DriverFactory {
    private static Collection<WebDriver> drivers;
    private static boolean loggedIn;

    public static Collection<WebDriver> getDrivers() {
        return getDrivers(true);
    }

    public static void tearDown() {
        drivers.forEach(WebDriver::quit);
        drivers = null;
        loggedIn = false;
    }

    public static Collection<WebDriver> getDrivers(boolean needLogin) {
        if (drivers != null) {
            if (!loggedIn && needLogin)
                logIn();
            return drivers;
        }
        getNewDrivers();
        if (!loggedIn && needLogin)
            logIn();
        return drivers;
    }

    public static Collection<WebDriver> getNewDrivers() {
        drivers = new ArrayList<>(2);
        drivers.add(new FirefoxDriver());
        //drivers.add(new ChromeDriver());
        loggedIn = false;
        return drivers;
    }

    public static boolean isLoggedIn() { return loggedIn; }
    public static void setLoggedIn(boolean loggedIn ) { DriverFactory.loggedIn = loggedIn; }

    public static void logIn() {
        drivers.forEach(x -> {
            x.get("http://e.mail.ru/login");
            new WebDriverWait(x, 10).until(d -> d.getTitle().toLowerCase().startsWith("вход"));
            WebElement frame = x.findElement(By.xpath("//*[@id=\"auth-form\"]/div/div/iframe"));
            x.switchTo().frame(frame);
            WebElement form = x.findElement(By.xpath("//*[@id=\"frame\"]"));
            WebElement nameField = form.findElement(By.xpath(".//span[@class='b-email__name']//input"));
            WebElement passField = form.findElement(By.xpath(".//div[contains(@class, 'b-form-row_password')]//input"));

            nameField.sendKeys("d32f123");
            passField.sendKeys("mciBMg(4ItZ7");

            passField.submit();
            x.switchTo().defaultContent();

            (new WebDriverWait(x, 5)).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("выход")));
        });
        loggedIn = true;
    }
}
