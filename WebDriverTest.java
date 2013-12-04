
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

public class WebDriverTest  {
    public static void main(String[] args) {
    	
    	//Get XID or HUID supplied by user as first command line argument
    	String username = args[0];	
    	//Get password supplied by user as second command line argument
    	String password = args[1];
    	
    	//This is a Selenium provided class.  
        WebDriver driver;
        
        //Use the PinLogin Class and authenticate 
        PinSession p  = new PinSession(username, 
        		                   password,
        		                   DefinedConstants.MYHARVARD_URL
        		                   );

        //Take the steps of logging in, and get a handle on the session driver. Will be redirected to start page of application 
        driver = p.login();
        /***************************************************************
         * This is where the actual application specific testing begins 
         ***************************************************************/
        
        //Need to pause here to give page time to reload, otherwise we won't be able to select element
        PinSession.sleep(2000);
   
        /***Get text box for keyword search in Course Planner*******/
        WebElement element1 = driver.findElement(By.name("keywordsValue"));
        PinSession.sleep(2000);
        
        //Do search in course planner on keyword byzantine, send to that web element
        element1.sendKeys("byzantine");
        PinSession.sleep(2000);
        
        //Don't forget to hit 'submit'
        element1.submit();
        PinSession.sleep(2000);
        // selenium.captureScreenshot("/Users/shannonrice/myharvard_scnshot.png");
       	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
          try {
              // Now you can do whatever you need to do with it, for example copy somewhere
              FileUtils.copyFile(scrFile, new File("/Users/shannonrice/myharvard_scnshot.png"));
          } catch (IOException ex) {
              //Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, ex);
          }
           
          PinSession.sleep(2000); 

        
          //Close the browser
          driver.quit();
    }
}