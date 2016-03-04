import com.thoughtworks.selenium.Selenium;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginTest {


    @Test
    public void testPositiveNewRegistration() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Intel/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://bootjava8-itakade.rhcloud.com/#/home");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='submit']")));

        WebElement mailInput = driver.findElement(By.id("emailField"));
        WebElement passwordInput = driver.findElement(By.id("passwordField"));
        mailInput.clear();
        mailInput.sendKeys("rokas@rokas.lt");
        passwordInput.clear();
        passwordInput.sendKeys("12345678");
        WebElement loginButton = driver.findElement(By.xpath("//input[@type='submit']"));
        loginButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Mano registracijos')]")));
        WebElement newRegistrationButton = driver.findElement(By.xpath("//*[contains(text(), 'Mano registracijos')]"));
        newRegistrationButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Nauja registracija')]")));

        WebElement newRegistration = driver.findElement(By.xpath("//*[contains(text(), 'Nauja registracija')]"));
        newRegistration.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("commentField")));
        WebElement firstname = driver.findElement(By.id("nameField"));
        WebElement lastname = driver.findElement(By.id("surnameField"));
        WebElement phone = driver.findElement(By.id("phoneField"));
        WebElement mail = driver.findElement(By.id("emailField"));
        WebElement site = driver.findElement(By.id("unitSelect"));
        WebElement date = driver.findElement(By.id("dateField"));
        WebElement topic = driver.findElement(By.id("topicSelect"));
        WebElement comments = driver.findElement(By.id("commentField"));

        firstname.sendKeys("p998ryf");
        lastname.sendKeys("autotest");
        phone.sendKeys("861872204");
        mail.sendKeys("rokas@rokas.lt");
        site.sendKeys("Antakalnio g. 45");

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date current = new Date();
        String Today = dateFormat.format(current);

        date.sendKeys(Today);
        topic.sendKeys("taupymas ir investavimas");
        comments.sendKeys("comment");

        WebElement submitRegistration = driver.findElement(By.xpath("//input[@type='submit']"));
        submitRegistration.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(.,'Atgal')]")));
        WebElement back = driver.findElement(By.xpath("//button[contains(.,'Atgal')]"));
        back.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Nauja registracija')]")));
        //boolean msgBox = driver.getPageSource().contains("Registracijos internetu");
        boolean isPresent = driver.findElements(By.xpath("//*[contains(text(), 'Antakalnio45')]")).size() > 0;

        Assert.assertTrue(isPresent);

        driver.quit();
    }

    @Test
    public void testNegativeLogin() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Intel/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://bootjava8-itakade.rhcloud.com/#/home");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("emailField")));

        WebElement mailInput = driver.findElement(By.id("emailField"));
        WebElement passwordInput = driver.findElement(By.id("passwordField"));
        mailInput.clear();
        mailInput.sendKeys("xxx");
        passwordInput.clear();
        passwordInput.sendKeys("12345678");
        WebElement loginButton = driver.findElement(By.xpath("//input[@type='submit']"));
        loginButton.click();
        Thread.sleep(2000);
        boolean msgBox = driver.getPageSource().contains("Susisiekite su mumis");

        Assert.assertFalse(msgBox);
        driver.quit();

    }

}

