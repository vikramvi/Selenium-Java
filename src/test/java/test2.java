

import org.junit.After;
import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class test2{
	
	private WebDriver webDriver;
	
	@Before
	public void beforeMethod() throws Exception {
		webDriver = new FirefoxDriver();
	}
	
	@After
	public void afterMethod() throws Exception {
		webDriver.quit();
	}
	
	@Test
	public void testHomePageTitle(){
		webDriver.get("http://spree.newcircle.com");
		Assert.assertEquals("Spree Demo Site", webDriver.getTitle());
	}
}