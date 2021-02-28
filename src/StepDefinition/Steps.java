package StepDefinition;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.github.javafaker.Faker;

import cucumber.api.java.en.Given;		
import cucumber.api.java.en.Then;		
import cucumber.api.java.en.When;

public class Steps {
	
	WebDriver driver;			
	
    @Given("^Open Chrome and launch the application$")					
    public void open_Chrome_and_launch_the_application() throws Throwable							
    {		
       System.setProperty("webdriver.chrome.driver", "./src/drivers/chromedriver.exe");					
       driver= new ChromeDriver();					
       driver.manage().window().maximize();			
       driver.get("http://automationpractice.com/index.php");					
    }		

    @When("^Order a TShirt$")					
    public void order_a_TShirt() throws Throwable 							
    {		
       driver.findElement(By.id("search_query_top")).sendKeys("t-shirt");							
       driver.findElement(By.name("submit_search")).click();	       
       driver.findElement(By.className("product_img_link")).click();
       driver.findElement(By.name("Submit")).click();
       
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);       
       driver.findElement(By.xpath("//a[@title='Proceed to checkout']")).click();
             
    }		

    @Then("^Verify the Order$")					
    public void verify_the_Order() throws Throwable 							
    {		
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  
    	List<WebElement> listOfElements = driver.findElements(By.className("product-name"));
     	Assert.assertEquals(listOfElements.get(3).findElement(By.tagName("a")).getText(), "Faded Short Sleeve T-shirts");
    	
    }	
    
    @When("^Register with New user$")					
    public void register_with_New_user() throws Throwable 							
    {		
    	Faker faker = new Faker(new Locale("en-GB"));
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  
    	driver.findElement(By.className("cart_navigation")).findElement(By.tagName("a")).click();
    	
    	driver.findElement(By.id("email_create")).sendKeys("nkcdasjkasn@askjn.com");
    	driver.findElement(By.id("SubmitCreate")).click();
    	
    	
   	
    }
    
    @When("^Check for Registration Page$")					
    public void check_for_Registration_Page() throws Throwable 							
    {		
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  
       	Assert.assertEquals(driver.getTitle(), "Login - My Store");
    	
    }
	

}
