package StepDefinition;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class searchTest {
	
	WebDriver driver = null;
	
	@Given("User opening browser and navigate to Url")
	public void user_opening_browser_and_navigate_to_url(){
		
		//Project Directory
		String projectDir = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectDir + "/Drivers/chromeDriver/chromedriver.exe");
		
		driver = new ChromeDriver();
		
		//Maximize Browser
		driver.manage().window().maximize();
		
		//Timeout
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);

		System.out.println("Successfully Launch Browser");
		
		//Navigate to Url
		driver.get("https://verint.com");
		
		System.out.println("Successfully Navigate to Url");
		
	}
	
	@Then("User click searchButton")
	public void user_click_searchButton(){
		driver.findElement(By.xpath("//li[@class='site-header__item site-header__item--search site-header__item--desktop']")).click();
//		driver.findElement(By.xpath("//div[@class='rxbodyfield']//div")).click();
		
	}
	
	@And("User input to text field")
	public void user_input_to_text_field(){
		
		WebElement searchField = driver.findElement(By.id("search-input--desktop"));
		searchField.sendKeys("Customer Solution");
		searchField.sendKeys(Keys.ENTER);
		System.out.println("User Successfully Input Customer Solution");
	}
	
	@Then("Validate the result")
	public void validate_the_result() throws InterruptedException{
		Thread.sleep(3000);
		Actions act = new Actions(driver);
		if(driver.getPageSource().contains("Customer Solution")){
			System.out.println("Customer Solution is on Page");
			driver.close();
			driver.quit();
		}else{
			System.out.println("Customer Solution is Not on Page");
			driver.close();
			driver.quit();
		}
	}

}
