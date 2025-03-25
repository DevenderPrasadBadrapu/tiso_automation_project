package com.tiso.place.order.cod;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PlaceOrderByCOD {

	WebDriver driver;
		
	@BeforeTest
	public  void launchBrowser() throws InterruptedException {
		
		//To run the automation in chrome browser
		WebDriverManager.chromedriver().setup();   //Uncomment this two lines and comment other browsers logic to execute the automation in chrome driver
		driver = new ChromeDriver();
		
		//To run the automation in firefox browser
		//WebDriverManager.firefoxdriver().setup();  //Uncomment this two lines and comment other browsers logic to execute the automation in firefox driver
		//driver = new FirefoxDriver();
		
		//To run the automation in edge browser
		//WebDriverManager.edgedriver().setup();  //Uncomment this two lines and comment other browsers logic to execute the automation in edge driver
		//driver = new EdgeDriver();
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		driver.get("https://pwiddy.interview.tisostudio.com/");
		

	}
	
	@Test(priority = 1)
	public void searchRestuarant() throws InterruptedException {//this method helps in searching Restaurant
		
		WebDriverWait waitone = new WebDriverWait(driver, Duration.ofSeconds(300));
		WebElement searchBar = waitone.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text']")));
		Thread.sleep(1000);
		WebElement searchField = driver.findElement(By.xpath("//input[@type='text']"));
		searchField.click();
		WebElement searchData = driver.findElement(By.xpath("//input[@type='text']"));
		searchData.sendKeys("West, Mayer and Wintheiser");
		Thread.sleep(3000);
		WebDriverWait waitTwo = new WebDriverWait(driver, Duration.ofSeconds(300));
		WebElement searchResults = waitone.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'West, Mayer and Wintheiser')]")));
		searchResults.click();
		WebDriverWait waitThree = new WebDriverWait(driver, Duration.ofSeconds(300));
		WebElement name = waitTwo.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'West, Mayer and Wintheiser')]")));
		
		String restaurantName = name.getText();
		System.out.println("Restuarant Name is: " + restaurantName);
		assertEquals("West, Mayer and Wintheiser", restaurantName);
	}
	
	@Test(priority = 2)
	public void addProductsToCart() throws InterruptedException {//This method helps in adding products to cart page
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
		WebElement productOne = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),'Rustic Metal Pizza')]/parent::div/descendant::button[contains(text(),'Add to Cart')]")));
		Thread.sleep(3000);
		scrollToElement(driver, productOne);
		productOne.click();
		Thread.sleep(3000);
		
		WebElement productTwo = driver.findElement(By.xpath("//h3[contains(text(),'Generic Marble Shirt')]/parent::div/descendant::button[contains(text(),'Add to Cart')]"));
		scrollToElement(driver, productTwo);
		productTwo.click();
		Thread.sleep(3000);
		
		WebElement productThree = driver.findElement(By.xpath("//h3[contains(text(),'Luxurious Granite Tuna')]/parent::div/descendant::button[contains(text(),'Add to Cart')]"));
		scrollToElement(driver, productThree);
		productThree.click();
		Thread.sleep(3000);
		WebElement cartIcon = driver.findElement(By.xpath("//a[@href='/cart']"));
		cartIcon.click();
		Thread.sleep(2000);
		WebElement btnProceedToCheckout = driver.findElement(By.xpath("//button[contains(text(),'Proceed to Checkout')]"));
		btnProceedToCheckout.click();
		
	}
	
	@Test(priority = 3)
	public void placeOrder() throws InterruptedException {//this method takes the to check out page and then placing order 
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
		WebElement checkOutPageHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Checkout')]")));
		
		WebElement cashOnDelivery = driver.findElement(By.xpath("//label[contains(text(),'Cash on Delivery')]"));
		cashOnDelivery.click();
		Thread.sleep(3000);
		
		WebElement streetAddress = driver.findElement(By.id("address"));
		streetAddress.click();
		streetAddress.sendKeys("LB Nagar" + Keys.CONTROL+"A");
		Thread.sleep(2000);
		
		WebElement city = driver.findElement(By.id("city"));
		city.click();
		city.sendKeys("Hyderabad" + Keys.CONTROL+"A");
		Thread.sleep(2000);
		
		
		WebElement zipCode = driver.findElement(By.id("zipCode"));
		zipCode.click();
		zipCode.sendKeys("500018" + Keys.CONTROL+"A");
		Thread.sleep(2000);
		
		WebElement phoneNumber = driver.findElement(By.id("phone"));
		phoneNumber.click();
		phoneNumber.sendKeys("8812456987" + Keys.CONTROL+"A");
		Thread.sleep(2000);
		
		WebElement btnPlaceOrder = driver.findElement(By.xpath("//button[contains(text(),'Place Order')]"));
		btnPlaceOrder.click();
		
		WebDriverWait waitTwo = new WebDriverWait(driver, Duration.ofSeconds(300));
		WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Thank you for your order!')]")));
		Thread.sleep(2000);
		String successMessage = message.getText();
		System.out.println("Success Message: " + successMessage);
		assertEquals("Thank you for your order!", successMessage);
	}
	
	public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);

}
	@AfterClass
	public void closeDriver() {
		driver.close();
	}
}
