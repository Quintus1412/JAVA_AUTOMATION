package Selenium_api;

import org.testng.annotations.Test;
import org.junit.Assert;

import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_02_XPATH_CSS_Part1 {

	// khai bao driver
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// khoi tao trinh duyet
		driver = new FirefoxDriver();
		// trả về GUID gồm 5 số: XXXX_XXXX_XXXX_XXXX_XXXX

		// cho page load thanh cong
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		// maximize browser len
		driver.manage().window().maximize();
		// Tới page cần thao tac
		driver.get("https://www.facebook.com/");

	}

	@Test
	public void TC_01_id() throws Exception {

		// Tìm 1 element
		WebElement emailTextbox = driver.findElement(By.id("email"));
		emailTextbox.clear();

		emailTextbox.sendKeys("quynh_kid_1412@yahoo.com");
		emailTextbox.isDisplayed();

		Thread.sleep(3000);

	}

	@Test
	public void TC_02_Class() throws Exception {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.className("validate-password")).sendKeys("123456");
		
		Thread.sleep(3000);

	}

	@Test
	public void TC_03_Name() throws Exception {

		driver.findElement(By.name("login[username]")).sendKeys("quynh_kid_1412@yahoo.com");
		Thread.sleep(3000);

	}
	
	@Test
	public void TC_04_TagName() throws Exception {
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Link number = " + links.size());

		  

	}

	@Test
	public void TC_05_LinkText() throws Exception {

		driver.findElement(By.linkText("MY ACCOUNT")).isDisplayed();

	}
	
	@Test
	public void TC_06_Partial_LinkText() throws Exception {

		driver.findElement(By.partialLinkText("MY ")).isDisplayed();
		driver.findElement(By.partialLinkText("ACCOUNT")).isDisplayed();
		Thread.sleep(3000);

	}

	@Test
	public void TC_07_Css() throws Exception {

		driver.findElement(By.cssSelector("#send2")).isDisplayed();
		driver.findElement(By.cssSelector("button[name='send']")).isDisplayed();
		driver.findElement(By.cssSelector("button[title='Login']")).isDisplayed();

	}

	@Test
	public void TC_08_Xpath() throws Exception {

		driver.findElement(By.xpath("//button[@name='send']")).isDisplayed();
		driver.findElement(By.xpath("//button[@title='Login']")).isDisplayed();

	}

	@AfterClass
	public void afterClass() {
		// quit browser
		driver.quit();
	}

}
