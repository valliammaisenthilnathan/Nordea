package valli.test.browse.amazon;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class TestAmazon {
  
  WebDriver driver_m = null;
  
  @BeforeClass
  public void setupClass()
  {
      /*
       * Get default properties
       */
      Props.setPropertySource(PropertyFactory.PropertySource.Default);
      
      /*
       * Make sure driver choice is set in the system property.
       */
      String driverStr = System.getProperty("test.conf.webdriver");
      Assert.assertNotNull(driverStr);  
      Assert.assertNotEquals(driverStr, "");
      
      /*
       * Make sure chosen webdriver is available
       * and usable.
       */
      Assert.assertTrue("Preconfitions for driver not met", DriverFactory.checkSystemProperties(driverStr));
      
  }
  
  @BeforeMethod
  public void setup() throws IllegalArgumentException, Exception
  {
      driver_m = DriverFactory.getDriver(System.getProperty("test.conf.webdriver"));
      driver_m.manage().timeouts().implicitlyWait(Props.getLongValue("test.conf.implicitwait"), TimeUnit.MILLISECONDS);
  }
  
  @AfterMethod
  public void teardown()
  {
      if (null != driver_m)
      {
          driver_m.quit();
          driver_m = null;
      }
  }
  
  @Parameters({"test.data.homepage", "test.data.checktext"})
  @Test
  public void test_Nikkon_camera_001(String homepage, String checkText) throws IllegalArgumentException, Exception {
      
      /*
       * Navigate to the homepage
       */
      driver_m.get(homepage);
      
      /*
       * Step 1: Search Nikon
       */
      driver_m.findElement(By.id("twotabsearchtextbox")).sendKeys("Nikon");
      driver_m.findElement(By.id("twotabsearchtextbox")).submit();
      
      /*
       * Step 2: Sort the products
       */
      new Select(driver_m.findElement(By.id("sort"))).selectByValue("price-desc-rank");
      Utils.hold(1000);
      
      /*
       * Step 3: Choose the row.
       */
      final int rowNumber = 2;
      String productId = "result_" + (rowNumber-1);
      WebElement ele = driver_m.findElement(By.id(productId));
      
      ele.findElement(By.className("a-link-normal")).click();
      
      Utils.hold(10000);
      
      String msg = "Text does not contain " + checkText;
      Assert.assertTrue(msg, driver_m.findElement(By.id("productTitle")).getText().contains(checkText));
      
  }

}
