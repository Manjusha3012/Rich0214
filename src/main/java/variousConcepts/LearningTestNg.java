package variousConcepts;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LearningTestNg {

WebDriver driver;

By userNameLocator;
By passwordLocator;
By signInButtonLocator;
By dashBoardHeaderLocator;
//By CustomerMenuLocator;
//By AddCustomerMenuLocator;



	
@BeforeMethod
	public void init() {

		System.setProperty("webdriver.chrome.driver","driver\\chromedriver.exe");
				

		driver = new ChromeDriver();
		
		driver.get("https://techfios.com/billing/?ng=login/");
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

@Test
		public void loginTest() {
		
	//Storing WebElement using By Class 
	
	userNameLocator=By.xpath("//input[@id='username']");			
	passwordLocator =By.xpath("//input[@id=\"password\"]");					
    signInButtonLocator =By.xpath("//button[@type='submit']");
	dashBoardHeaderLocator =By.xpath("//h2[contains(text(),'Dashboard')]");
	//CustomerMenuLocator= By.xpath("//span[contains(text(),'Customers')]");
	//AddCustomerMenuLocator=By.xpath("//a[contains(text(),'Add Customer')]");
    
	driver.findElement(userNameLocator).sendKeys("demo@techfios.com");
	driver.findElement(passwordLocator).sendKeys("abc123");
	driver.findElement(signInButtonLocator).click();
	
	
	WebDriverWait wait = new WebDriverWait(driver,60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(signInButtonLocator));

	String actualDashBoardHeader= driver.findElement(dashBoardHeaderLocator).getText();
	Assert.assertEquals(actualDashBoardHeader,"Dashboard", "WrongPage!!");
    
}



@AfterMethod

	public void tearDown() {
		driver.close();
		driver.quit();
		
		
	}
	
}
	
	

