package HelenID;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class AppTest
{
    private static WebDriver driver;

    @Before
    public void openPage(){
        System.setProperty("webdriver.chrome.driver", "C://WebDriver//chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kask6iktundubkorras.sayat.me/");
    }


    // Method for login
    public void login(String username, String password) {
        driver.findElement(By.linkText("Logi sisse")).click();
        WebElement username1 = driver.findElement(By.id("fburl_d"));
        username1.click();
        username1.sendKeys(username);
        WebElement pw = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/form[2]/div/input[1]"));
        pw.click();
        pw.sendKeys(password);
        driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/form[2]/div/button")).click();
    }

    // Method for reaching person using search box and navigate to the profile
    public void searchPerson() throws InterruptedException {
        WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"topNav\"]/div/div[1]/div[3]/div[2]/div/input"));
        searchBox.click();
        searchBox.sendKeys("marikass");
        Thread.sleep(5000);
        WebElement result = driver.findElement(By.xpath("//*[@id='ui-id-1']"));
        result.click();
    }

    // Give feedback on someone's account
    public void giveFeedBack(String message) throws InterruptedException {
        WebElement textArea = driver.findElement(By.xpath("/html/body/div[3]/div/div/form/textarea"));
        textArea.click();
        textArea.sendKeys(message);
        driver.findElement(By.xpath("/html/body/div[3]/div/div/form/div[3]/button")).click();
    }

    @Test
    // Validate if the feedback was really given
    public void validateFeedback() throws InterruptedException {
        login("helenhendrikson", "helen1234");
        searchPerson();
        giveFeedBack("Oled tore kasutaja");
        WebElement comment = driver.findElement(By.xpath("//*[@id='answers']/li[1]/div[@class='post-answer-text']"));
        Assert.assertEquals("Oled tore kasutaja", comment.getText());
    }

    @AfterClass
    public static void close() throws Exception {
        driver.quit();
    }

}