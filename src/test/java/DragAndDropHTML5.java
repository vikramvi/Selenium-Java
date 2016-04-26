import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DragAndDropHTML5 {
	
		private WebDriver webDriver;
		
		@Before
		public void beforeMethod() throws Exception {
			//webDriver = new FirefoxDriver(); //Works
			webDriver = new ChromeDriver();    //Works
		}
		
		@After
		public void afterMethod() throws Exception {
			webDriver.quit();
		}
	
		//http://stackoverflow.com/questions/33849040/drag-and-drop-testing-in-selenium-on-html5-using-java?lq=1
        //http://stackoverflow.com/questions/29381233/how-to-simulate-html5-drag-and-drop-in-selenium-webdriver
        @Test
		public void testDragAndDrop() throws InterruptedException, IOException{ 
        	try{
        	        String basePath = new File("").getAbsolutePath();
		            
		        	//System.setProperty("webdriver.Firefox.driver", "Path_executable");
			        //WebDriver driver= new FirefoxDriver();
			        
        	        webDriver.get("http://html5demos.com/drag#");
			
			        //http://stackoverflow.com/questions/29381233/how-to-simulate-html5-drag-and-drop-in-selenium-webdriver
			        //https://gist.github.com/rcorreia/2362544
			        final String JQUERY_LOAD_SCRIPT = (basePath + "/src/test/resources/jquery_load_helper.js");
			        String jQueryLoader = readFile(JQUERY_LOAD_SCRIPT);
			        
			        webDriver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
			        
			        JavascriptExecutor js = (JavascriptExecutor) webDriver;
			        js.executeAsyncScript(
			                jQueryLoader /* , http://localhost:8080/jquery-1.7.2.js */);
			
			        // ready to rock
			        js.executeScript("jQuery(function($) { " + " $('input[name=\"q\"]').val('bada-bing').closest('form').submit(); "
			                + " }); ");
			
			
			
			      //http://stackoverflow.com/questions/29381233/how-to-simulate-html5-drag-and-drop-in-selenium-webdriver
			      //"where jquery_load_helper.js contains:"  
			      String filePath = basePath + "/src/test/resources/drag_and_drop_helper.js";
			      
			      
			      //JQuery can ONLY work with id and css , xpath does NOT work with it.
			      //String source =  "//section[@id='wrapper']/article/ul/li[4]/a"; 
			      String source = "section#wrapper article ul li:nth-child(4) a";
			      String target =  "section#wrapper article div"; //#bin";
			      
			      StringBuffer buffer = new StringBuffer();
			      String line;
			      BufferedReader br = new BufferedReader(new FileReader(filePath));
			      while((line = br.readLine())!=null)
			          buffer.append(line);
			
			      String javaScript = buffer.toString();
			
			      javaScript = javaScript + "window.jQuery('" + source + "').simulateDragDrop({ dropTarget: '" + target + "'});";
			      ((JavascriptExecutor)webDriver).executeScript(javaScript);
			      
			      
			      Thread.sleep(1000);
			      source = "section#wrapper article ul li:nth-child(2) a";
			      javaScript = javaScript + "$('" + source + "').simulateDragDrop({ dropTarget: '" + target + "'});";
			      ((JavascriptExecutor)webDriver).executeScript(javaScript);
			      
			      Thread.sleep(1000);
			      source = "section#wrapper article ul li:nth-child(1) a";
			      javaScript = javaScript + "$('" + source + "').simulateDragDrop({ dropTarget: '" + target + "'});";
			      ((JavascriptExecutor)webDriver).executeScript(javaScript);
			      
			      Thread.sleep(1000);
        	}catch(Exception e){
        		e.printStackTrace();
        	}

}

    private static String readFile(String file) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(file);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
    }
    
}

