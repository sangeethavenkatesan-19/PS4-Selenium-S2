package Extent_Reports;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Search_Test {

	// step1: formulate a test domain url & driver path
	String siteUrl = "https://demowebshop.tricentis.com/";
	String driverPath = "drivers/windows/geckodriver.exe";
	WebDriver driver;
	
	ExtentSparkReporter htmlReporter;
	ExtentReports extentReports;
	ExtentTest test1, test2 ;
	
	@BeforeClass
	public void beforeClass() {

		// create the htmlReporter object
		htmlReporter = new ExtentSparkReporter("extentReport-search.html");

		// create ExtentReport and attach this reports
		extentReports = new ExtentReports();
		extentReports.attachReporter(htmlReporter);

		
		// initialize and start the browser
		WebDriverManager.chromedriver().setup();
				
		// step2: set system properties for selenium dirver
		System.setProperty("webdriver.geckodriver.driver", driverPath);

		// step3: instantiate selenium webdriver
		driver = new FirefoxDriver();

		// step4: launch browser
		driver.get(siteUrl);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
		extentReports.flush();
	}

	@Test(description = "Search Jewelry")
	public void testSearch1() throws InterruptedException {
		// create a test and add logs
		test1 = extentReports.createTest("demowebshop.tricentis.com Search Jewelry", "Test demowebshop.tricentis.com Search Jewelry");
		test1.log(Status.INFO, "Starting test case");
		
		WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"small-searchterms\"]"));
		test1.pass("Find search box on demowebshop.tricentis.com");
		
		searchBox.sendKeys("Jewelry");
		test1.pass("Enter input data 'Jewelry' ");
		
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[1]/div[3]/form/input[2]")).click();
		test1.pass("Click Search");
		
		// add delay
		Thread.sleep(2000);

		String expectedTitle = "Demo Web Shop. Search";
		String actualTitle = driver.getTitle();

		assertEquals(actualTitle, expectedTitle);
		test1.pass("After search the title are matched");
		
		test1.log(Status.INFO, "End test case");
	}

	@Test(description = "Search Apparel and shoe")
	public void testSearch2() throws InterruptedException {
		// create a test and add logs
		test2 = extentReports.createTest("demowebshop.tricentis.com Search Apparel and shoe", "Test demowebshop.tricentis.com Search Apparel and shoe");
		test2.log(Status.INFO, "Starting test case");
		
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[1]/div[1]/div[2]/ul/li[4]/a")).click();
		test2.pass("Click Apparel and shoe");
		
		// add delay
		Thread.sleep(2000);

		String expectedTitle = "Demo Web Shop. Apparel & Shoes";
		String actualTitle = driver.getTitle();

		assertEquals(actualTitle, expectedTitle);
		test2.pass("After search the title are matched");
		
		test2.log(Status.INFO, "End test case");
	}
}