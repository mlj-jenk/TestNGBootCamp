package categoryTestNG;

//Test 1: Validate a user is able to add a category and once 

//the category is added it should display.

//Test 2: Validate a user is not able to add a duplicated category. 
//If it does then the following message will display: "The category you"+ " want"
//+ " to add already exists: <duplicated category name>."

//Test 3: Validate the month drop down has all the months (jan, feb, mar ...)
//in the Due Date dropdown section.



import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class validateCategoryAdd {
	
	WebDriver driver;
	String browser = null;
	String url = null;
	
@BeforeTest	
public void readConfig() {
	
	Properties prop = new Properties();
	
	try {
		InputStream input = new FileInputStream("\\src\\main\\java\\configBC\\configbc.properties");
	    prop.load(input);
	    prop.get("browser");
	    
	}catch(IOException e) {
		e.printStackTrace();
	}
}
	
	
	//Navigate to the website.

public void initializeDriver() {
    
    System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe"); 
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	
	driver.get("http://techfios.com/test/101/");
	WebDriverWait wait = new WebDriverWait(driver, 5);
	driver.findElement(By.xpath("/*[@id=\"extra\"]/input[2],'Add Catagorey')")).click();
}
			
	

	//Test 1: Validate a user is able to add a category and once 
	//the category is added it should display.
	
    @Test(priority=1)
	public void enterNewCategory() throws Throwable {
	driver.findElement(By.xpath("//*[@id=\"extra\"]/input[1]")).sendKeys("AddingCategoryRegion");
	
	WebElement dup = driver.findElement(By.id("//*[@id=\"extra\"]/select[1], 'Category')"));
	
	driver.get("http://techfios.com/test/101/");
	Select cat = new Select(driver.findElement(By.id("Category")));
	cat.selectByVisibleText("AddingCategoryRegion");
	cat.deselectByIndex(1);
	System.out.println("Here is the list with new category added: " +cat);
	
	}     
	
  //Test 2: Validate a user is not able to add a duplicated category. 
  //If it does then the following message will display: "The category you"+ " want"
  //+ " to add already exists: <duplicated category name>."
    
    @Test(priority=2)
	public void NotAbleToAddNewCategory() throws Throwable {
	driver.findElement(By.xpath("//*[@id=\"extra\"]/input[1]")).sendKeys("TESTNG999");
	
	WebElement dup = driver.findElement(By.id("//*[@id=\"extra\"]/select[1], 'Category')"));
	
	
	Select notadd = new Select(driver.findElement(By.id("Category")));
	notadd.selectByVisibleText("TESTNG999");
	notadd.selectByIndex(2);
	System.out.println("The category you want to add already exist: " +notadd);
	
	}
	
  //Test 3: Validate the month drop down has all the months (jan, feb, mar ...)
  //in the Due Date dropdown section.
  
	@Test(priority=3)
	public void ValidateMonthDropDownHasAllMonthsListed() {
	
	driver.findElement(By.xpath("//*[@id=\"extra\"]/select[3]")).click();  

    Boolean verifyTitleIsPresent=driver.getTitle().equalsIgnoreCase
    		("Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec");
    assertTrue(verifyTitleIsPresent);
    
    Select dropDown = new Select(driver.findElement(By.id("due_month")));
    WebElement e = (WebElement) dropDown.getOptions();
    dropDown.selectByVisibleText("Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec");
    System.out.println(e.getText());
    
    
	}

@After
public void teardown() throws InterruptedException {
Thread.sleep(2000); 
driver.close(); 
driver.quit(); }

}
//String[] exp = {"Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec"};
//WebElement dropdown = driver.findElement(By.id("due_month"));  
//Select select = new Select(dropdown);  
        
   
 



