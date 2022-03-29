package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ReadingConfigFiles {

	WebDriver driver;
	String browser;
	String url;

	// Storing WebElements using By Class in Global Location
	
	By userNameLocator = By.xpath("//input[@id='username']");
	By passwordLocator = By.xpath("//input[@id=\"password\"]");
	By signInButtonLocator = By.xpath("//button[@type='submit']");
	By dashBoardHeaderLocator = By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div/h2");
	By CustomerMenuLocator = By.xpath("//span[contains(text(),'Customers')]");
	By AddCustomerMenuLocator = By.xpath("//a[contains(text(),'Add Customer')]");
	By AddCustomerHeaderLocator=By.xpath("//*[@id=\"page-wrapper\"]/div[3]/div[1]/div/div/div/div[1]/h5");
	By fullNameMenuLocator = By.xpath("//input[@id='account']");
	By fullNameHeaderLocator=By.xpath("//*[@id=\"rform\"]/div[1]/div[1]/div[1]/label");
	By selectCompanyDropDownLocator = By.xpath("//select[@name='cid']");
	By emailAdressMenuLocator = By.xpath("//input[@id='email']");
	By phoneMenuLocator = By.xpath("//input[@id='phone']");
	By addressMenuLocator = By.xpath("//input[@id='address']");
	By cityMenuLocator = By.xpath("//input[@id='city']");
	By stateMenuLocator = By.xpath("//input[@id='state']");
	By zipPostalLocator = By.xpath("//input[@id='zip']");
	By countryMenuLocator = By.xpath("//input[@class='select2-search__field' and @type='search' and @tabindex= '0' and @spellcheck='false' and@ role='textbox' and@ autocorrect='off' and@ style='width: 0.75em;']");
	By saveButtonLocator = By.xpath("//button[@class='md-btn md-btn-primary waves-effect waves-light']");


	// Login data
	String userName = "demo@techfios.com";
	String Password = "abc123";

	// Test data
	String fullNameMenu = "Seleneium September";
	String selectCompany = "Techfios";
	String emailAddress = "manjushamob@gmail.com";
	String phoneMenu = "4680988345";
	String cityMenu = "Richardson";
	String stateMenu = "Texas";
	String zipPostal = "75081";
	String countryMenu = "USA";

	@BeforeTest
	public void readConfig() {
		try {
			InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			Properties prop = new Properties();
			prop.load(input);
			browser = prop.getProperty("browser");
			System.out.println("Browser used: " + browser);
			url = prop.getProperty("url");

		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	@BeforeMethod
	public void init() {

		if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "driver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		

		driver.get(url);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Test
	public void loginTest() {

		driver.findElement(userNameLocator).sendKeys(userName);
		driver.findElement(passwordLocator).sendKeys(Password);
		driver.findElement(signInButtonLocator).click();

		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(signInButtonLocator));

		String expectedDashBoardHeader = driver.findElement(dashBoardHeaderLocator).getText();
		Assert.assertEquals(expectedDashBoardHeader, "Dashboard", "WrongPage!!");

	}

	@Test
	public void AddContact() {

		driver.findElement(userNameLocator).sendKeys(userName);
		driver.findElement(passwordLocator).sendKeys(Password);
		driver.findElement(signInButtonLocator).click();
		
		driver.findElement(CustomerMenuLocator).click();
		driver.findElement(AddCustomerMenuLocator).click();
		
		String AddCustomerMenuLocator = driver.findElement(AddCustomerHeaderLocator).getText();
		Assert.assertEquals(AddCustomerMenuLocator, "Add Contact", "WrongPage!!");
		
		Random rnd =new Random();
		int rndGeneratedNumber=rnd.nextInt(999);
		
		
		driver.findElement(fullNameMenuLocator).sendKeys(fullNameMenu + rndGeneratedNumber);
		driver.findElement(selectCompanyDropDownLocator).sendKeys(selectCompany);
		driver.findElement(emailAdressMenuLocator).sendKeys(emailAddress+ rndGeneratedNumber);
		driver.findElement(phoneMenuLocator).sendKeys(phoneMenu);
		driver.findElement(stateMenuLocator).sendKeys(stateMenu);
		driver.findElement(countryMenuLocator).sendKeys(countryMenu);
		driver.findElement(saveButtonLocator).click();

		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(signInButtonLocator));

		String expectedDashBoardHeader = driver.findElement(dashBoardHeaderLocator).getText();
		Assert.assertEquals(expectedDashBoardHeader, "Contacts", "WrongPage!!");

		
		
		
		
	}

	
	
	@AfterMethod
	public void tearDown() {

		driver.close();
		driver.quit();

	}

}

// to read java files we use some classes like
// InputStream//BufferedReader//Scanner//FileReader
// InputStream-To read
// OutputStream-To write
// when we try to read/ perform/ something in java which statement we use to
// execute/if it fails/if the code
// doesn't execute?? Answer is-""Try-catch""

// we have downloaded the
		// geckodriver.exe file and saved it
		// to our driver.
//		=========we can use this in both browser using if and else========