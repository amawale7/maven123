package AutomationDemoSite;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

public class AutomationDemoSitePage 
{

	WebDriver driver;
	
	@Test
	public void openBrowser()
	{
		BrowserFactory.startBrowser("chrome", "http://www.newtours.demoaut.com/");
	}
	
	@Test
	public void registerTest() throws Exception
	{
		Locator pagefactory=PageFactory.initElements(driver, Locator.class);
		pagefactory.register();
	}
	
	@AfterSuite
	public void closeBrowser()
	{
		driver.quit();
	}
}
