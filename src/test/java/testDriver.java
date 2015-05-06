import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class testDriver {

	@Test
	public void startSite(){
		 String url = "https://lovemusic.se";
		 WebDriver driver = new ChromeDriver();
		 driver.get(url);
		 String title = driver.getTitle();
		 
		 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(scrFile, new File("screenshot.png"));
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		 
		 System.out.println("The title of " + url + " is: " + title);
		 driver.quit();
	}
	
}
