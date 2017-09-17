package valli.test.browse.amazon;

import java.io.File;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory
{

	public static WebDriver getDriver(String driver) throws IllegalArgumentException
	{
		/*
		 * This place should be used to decide which browser suhould be used.
		 * ChromeDriver is chosen now.
		 *
		 * Notes:
		 * [1] Need to enable protected mode for IE browser
		 * https://stackoverflow.com/questions/31134408/unable-to-find-element-on-closed-window-on-ie-11-with-selenium
		 */

	    if (driver.equals("chrome"))
		{
			return new ChromeDriver();
		}
		if (driver.equals("firefox"))
		{
			return new FirefoxDriver(new FirefoxOptions());
		}
		if (driver.equals("ie"))
		{
			return new InternetExplorerDriver();
		}
		throw new IllegalArgumentException("Unsupported driver type");

	}

	public static boolean checkSystemProperties(String driver) throws IllegalArgumentException
	{

	      String driverPath = null;
	      if (driver.equals("firefox"))
	      {
	          driverPath = System.getProperty("webdriver.gecko.driver");
	      }
	      else if (driver.equals("chrome")|| driver.equals("ie"))
	      {
	          driverPath = System.getProperty("webdriver." + driver + ".driver");
	      }
	      else
	      {
	          throw new IllegalArgumentException("Unsupported driver type");
	      }

          if (null == driverPath
                  || driverPath.equals("")
                  || !(new File(driverPath).exists()))
          {
              return false;
          }

          // All is well! Get... Set... Go!
          return true;

	}

}
