package com.gmailproject.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignInTest {
	
	WebDriver driver;	
	
	@BeforeMethod
	public void setup(){
		System.setProperty("webdriver.chrome.driver", "C:/Users/shr_m/workspace/SeleniumWebdriver/chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http://gmail.com");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		
		
	}
	//@Test(priority=1)
	public void verifyGmailLoginPageTest(){
		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Create account')]")).isDisplayed());
		
		
	}
	//@Test(priority=2)
	public void gmailSignInTest() throws InterruptedException{
		driver.findElement(By.id("identifierId")).clear();
		driver.findElement(By.id("identifierId")).sendKeys("shrija.jain1@gmail.com");
		driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
		//WebDriverWait wait=new WebDriverWait(driver,5);
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("CwaK9")));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
		
		Thread.sleep(3000);
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Next')]")));
		driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("m1s9cd23");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
		//verify user succesfully logged in
		Thread.sleep(3000);
		
		
	
	
		Boolean displayed=driver.findElement(By.xpath("//a[contains(text(),'Inbox')]")).isDisplayed();
         Assert.assertTrue(displayed,"user has not logged in gmail successfully");
	}
	
	@Test
	public void gmailSendAndReceiveEmailTest() throws InterruptedException{
		//signin into gmail
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Create account')]")).isDisplayed());	
		driver.findElement(By.id("identifierId")).clear();
		driver.findElement(By.id("identifierId")).sendKeys("shrija.jain1@gmail.com");
		driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("m1s9cd23");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
		Thread.sleep(3000);
		//click compose
		driver.findElement(By.xpath("//div[contains(text(),'Compose')]")).click();
		//fill in recipient name
		driver.findElement(By.name("to")).clear();
		driver.findElement(By.name("to")).sendKeys("shrija.jain1@gmail.com");
		
		//fill in subject
		WebElement subjectTextArea=driver.findElement(By.name("subjectbox"));
		subjectTextArea.clear();
		final String subject= "Test Email";
		subjectTextArea.sendKeys(subject);
		
		//fill in body
		WebElement bodyTextArea=driver.findElement(By.xpath("//div[@aria-label='Message Body']"));
		bodyTextArea.clear();
		final String body="Hello All! This is Test email";
		bodyTextArea.sendKeys(body);
		//click send button
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[contains(@aria-label,'Send') and @role='button']")).click();
		
		Thread.sleep(3000);
		
		//click inbox
		WebElement inbox=driver.findElement(By.xpath("//a[@title='Inbox']"));
		inbox.click();
		//click email
		WebElement newEmail=driver.findElement(By.xpath("//tr[@class='zA zE']"));
		newEmail.click();
		//verify received email subject and body is correct
		WebElement subjectArea=driver.findElement(By.className("hP"));
		subjectArea.getText();
		System.out.println(subjectArea.getText());
		Assert.assertEquals(subject, subjectArea.getText(),"Email subject should be  same");
		
		WebElement bodyArea=driver.findElement(By.xpath("//div[@class='a3s aXjCH ']//div[@dir='ltr']"));
		bodyArea.getText();
		
		//System.out.println(bodyArea.getText());
		Assert.assertEquals(body,bodyArea.getText(),"Email body should be  same");
		//sign out from gmail
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='gb_Ba gbii']")));
		WebElement profileBtn=driver.findElement(By.xpath("//span[@class='gb_Ba gbii']"));
		profileBtn.click();
		WebElement signOutBtn=driver.findElement(By.xpath("//a[contains(text(),'Sign out')]"));
		signOutBtn.click();
	}
	
	
	//@AfterMethod
	//public void teardown(){
	//	driver.quit();
	//}
}
