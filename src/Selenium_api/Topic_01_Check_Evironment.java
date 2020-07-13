package Selenium_api;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01_Check_Evironment {

	// khai bao driver
	WebDriver driver;
	String Email = "emailrandom" + randomNumber()+"@gmail.com";
	

	@BeforeClass
	public void beforeClass() {
		// khoi tao trinh duyet
		System.setProperty("webdriver.gecko.driver", ".\\Drivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//
		// Khoi tao Chrome
		//System.setProperty("webdriver.chrome.driver", ".\\Drivers\\chromedriver.exe");
		//driver = new ChromeDriver();
		//
		// khoi tao IE
		// System.setProperty("webdriver.ie.driver", ".\\Drivers\\IEDriverServer.exe")
		// driver = new InternetExplorerDriver();
		//
		// cho page load thanh cong
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// maximize browser len
		driver.manage().window().maximize();

		// get URL cua web
		driver.get("http://live.demoguru99.com/");
	
	}

	



	private int randomNumber() {
		// TODO Auto-generated method stub
		Random	number =new Random();
		return number.nextInt(1000);
	}











	public void TC_01_LoginEmpty() throws Exception {
		
		// step1: log in trang
		//driver.get("http://live.demoguru99.com/");
		// step2: vao trang dang nhap
		// click Account -> Log in
		driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Log In')]")).click();
		Thread.sleep(2000);
		// step 3: de trong
		WebElement emailTextbox = driver.findElement(By.id("email"));
		emailTextbox.clear();
		
		WebElement passTextbox = driver.findElement(By.id("pass"));
		passTextbox.clear();
		
		// step 4: Click login
		driver.findElement(By.xpath(".//*[@id='send2']")).click();
		Thread.sleep(1000);
		
		// step 5: verify erroe message xuat hien tai 2 field
		String emailValidateText = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals("This is a required field.", emailValidateText);
		
		String passValidateText = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		
		Assert.assertEquals("This is a required field.", passValidateText);
		
	}

	public void TC_02_LogInEmailInvalid() throws Exception {
		
		// step1: log in trang
		//driver.get("http://live.demoguru99.com/");
		// step2: vao trang dang nhap
		// click Account -> Log in
		driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Log In')]")).click();
		Thread.sleep(2000);
		// step 3: nhap email invalid
		driver.findElement(By.id("email")).sendKeys("12345@12345");
		
		//pass để trống
		WebElement passTextbox = driver.findElement(By.id("pass"));
		passTextbox.clear();
		
		// step 4: Click login
		driver.findElement(By.xpath(".//*[@id='send2']")).click();
		Thread.sleep(1000);
		
		// step 5: verify error message xuat hien tai 2 field
		String emailValidateText = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals("Please enter a valid email address. For example johndoe@domain.com.", emailValidateText);
		
		String passValidateText = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		
		Assert.assertEquals("This is a required field.", passValidateText);
		
	}
	
	
	public void TC_03_LogInPassIncorrectd() throws Exception {
		
		// step1: log in trang
		//driver.get("http://live.demoguru99.com/");
		// step2: vao trang dang nhap
		// click Account -> Log in
		driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Log In')]")).click();
		Thread.sleep(2000);
		// step 3: nhap email valid
		driver.findElement(By.id("email")).sendKeys("testingautomation@gmail.com");
		
		//pass để incorrect
		driver.findElement(By.id("pass")).sendKeys("123");
		
		
		// step 4: Click login
		driver.findElement(By.xpath(".//*[@id='send2']")).click();
		Thread.sleep(1000);
		
		// step 5: verify error message xuat hien incorrect pass
		String passlIncorrectText = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals("Please enter 6 or more characters without leading or trailing spaces.", passlIncorrectText);
		
		
	}
	
	
	public void TC_05_LogInValidEmailAndPass() throws Exception {
		
		// step1: log in trang
		//driver.get("http://live.demoguru99.com/");
		// step2: vao trang dang nhap
		// click Account -> Log in
		driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Log In')]")).click();
		Thread.sleep(2000);
		// step 3: nhap email valid
		driver.findElement(By.id("email")).sendKeys("quynhdangt@gmail.com");
		
		//pass valid
		driver.findElement(By.id("pass")).sendKeys("123456");
		
		
		// step 4: Click login
		driver.findElement(By.xpath(".//*[@id='send2']")).click();
		Thread.sleep(1000);
		
		// step 5: verify toi duoc trang Dashboard
		// Thay dc Title Dashboard
		String WelcomeText = driver.findElement(By.xpath("//div[@class='page-title']/h1[contains(.,'My Dashboard')]")).getText();
		Assert.assertEquals(WelcomeText, "MY DASHBOARD");
		// lấy được Welcome message
		
		//Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg' and (contains(string(),'Thank you for registering with Main Website Store.'))]")).getText(), actual);
		Assert.assertEquals(driver.findElement(By.xpath( "//div[@class='welcome-msg']/p/strong")).getText(), "Hello, quynh thi dang!");
		
		String contactInfo= driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInfo.contains("quynh thi dang"));
		Assert.assertTrue(contactInfo.contains("quynhdangt@gmail.com"));
		
	}
	@Test
	public void TC_06_CreateNewAccount() throws Exception {

		// step1: log in trang
		//driver.get("http://live.demoguru99.com/");
		// step2: vao trang dang nhap
		// click Account -> Log in
		driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
		driver.findElement(By.cssSelector("a[title*='Register']")).click();
		Thread.sleep(1000);
		// step 3: nhap info valid
		driver.findElement(By.cssSelector("input#firstname")).sendKeys("testfirstname");
		
		driver.findElement(By.cssSelector("input#middlename")).sendKeys("testmiddlename");
		
		driver.findElement(By.cssSelector("input#lastname")).sendKeys("testlastname");
		
		//nhaop email random
		driver.findElement(By.cssSelector("#email_address")).sendKeys(Email);
		
		driver.findElement(By.cssSelector("#password")).sendKeys("123456");
		driver.findElement(By.cssSelector("#confirmation")).sendKeys("123456");
		
		Thread.sleep(500);
		// step 4: Click register
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		Thread.sleep(500);
		// step 5: verify toi duoc trang Dashboard
		// Thay dc Title Dashboard
		
		
		//Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg' and (contains(string(),'Thank you for registering with Main Website Store.'))]")).getText(), actual);
		Assert.assertEquals(driver.findElement(By.xpath( "//li/span[contains(text(),'Thank you for registering with Main Website Store.')]")).getText(), "Thank you for registering with Main Website Store.");
		Thread.sleep(500);
		
		driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		Thread.sleep(6000);
		
		//quay ve Home Page
		driver.findElement(By.xpath("//title[text()='Home page']")).isDisplayed();
	}

	


	@AfterClass
	public void afterClass() {
		// quit browser
		driver.quit();
	}

}
