package MakePlannieWork.Plannie.controller;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.junit.Test;

public class GebruikerControllerTest {

    private WebDriver driver;

//    @BeforeEach
//    public void setUp() {
//        System.setProperty("webdriver.chrome.driver", "Algemeen/chromedriver.exe");
//
//        driver = new ChromeDriver();
//    }
//
//    @AfterEach
//    public void tearDown() {
//        driver.close();
//    }

    @Test
    public void testGoogleSearch() throws InterruptedException {
        // Optional. If not specified, WebDriver searches the PATH for chromedriver.
        System.setProperty("webdriver.chrome.driver", "Algemeen/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("http://www.google.com/");

        Thread.sleep(5000);  // Let the user actually see something!
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("ChromeDriver");
        searchBox.submit();
        Thread.sleep(5000);  // Let the user actually see something!
        driver.quit();
    }

}