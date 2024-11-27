package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

    @Test
    public void testCase01() throws InterruptedException{
        double starRating=4.0;
        driver.get("https://www.flipkart.com/");
        By searchBar=By.xpath("//input[@placeholder='Search for Products, Brands and More']");
        Wrappers.enterText(driver, searchBar, "Washing Machine");
        Thread.sleep(3000);
        By sortOption=By.xpath("//div[contains(text(), 'Popularity')]");
        Wrappers.clickOnElement(driver,sortOption);
        By rating=By.xpath("//span[contains(@id,'productRating')]/div");
        Boolean status=Wrappers.searchStarRatingAndPrintCount(driver, rating, starRating);
        Assert.assertTrue(status);

        System.out.println("Test case 01 ended");

    }

    @Test
    public void testCase02() throws InterruptedException{
        int discount=17;
        driver.get("https://www.flipkart.com/");
        By searchBar=By.xpath("//input[@placeholder='Search for Products, Brands and More']");
        Wrappers.enterText(driver, searchBar, "iPhone");
        Thread.sleep(3000);
        By iPhoneDetails=By.xpath("//div[@class='UkUFwK']/span//ancestor::div[contains(@class,'yKfJKb')]");
        Boolean status=Wrappers.iPhoneTitleAndDiscount(driver, iPhoneDetails, discount);
        Assert.assertTrue(status);

        System.out.println("Test case 02 ended");
    }

    @Test
    public void testCase03() throws InterruptedException{
        driver.get("https://www.flipkart.com/");
        By searchBar=By.xpath("//input[@placeholder='Search for Products, Brands and More']");
        Wrappers.enterText(driver, searchBar, "Coffee Mug");
        Thread.sleep(5000);
        By above4Rating=By.xpath("//div[contains(text(), ' & above')]");
        Wrappers.clickOnElement(driver, above4Rating);
        Thread.sleep(5000);
        Boolean status=Wrappers.coffeeMugTitleAndImageURL(driver, By.xpath("//div[@class='slAVV4']//span[@class='Wphh3N']"));
        Assert.assertTrue(status);

        System.out.println("Test case 03 ended");
    }

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }


    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}