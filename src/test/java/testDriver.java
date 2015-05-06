import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class testDriver {

	@Test
	public void startSite(){
		 String url = "https://lovemusic.se";
		 WebDriver driver = new ChromeDriver();
		 driver.get(url);
		 String title = driver.getTitle();
		 System.out.println("The title of " + url + " is: " + title);
		 driver.quit();
	}
	
}
