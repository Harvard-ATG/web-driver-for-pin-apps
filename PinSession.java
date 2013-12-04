import java.lang.String;
import java.util.regex.*;
import java.lang.Thread;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
/**********************
 * The PinSession class manages the logic of logging into Harvard PIN applications. 
 * @author Shannon Rice <srice@fas.harvard.edu>
 * This is to be made available to the Harvard QA/development community
 *
 ***********************/
public class PinSession {

    // four fields
    private String username;
    private String password;
    private String huidOrXid; //I will set this dynamically, depending on whether it is an 8-digit ID or another pattern. (The assumption is that no one will use XIDs that are 8-digits.)
    private String url; //The PIN URL
    private WebDriver driver; 
        
   /****************************************************************************************************************************************************
    * This is the constructor method to handle the logic of Authenticating via an HIUD or XID with a PIN enabled web application
    * @param uname - This is the actual HUID or XID.   (I take this as a command line arg to avoid hardcoding any confidential HUIDs.)
    * @param pword - This is the password that corresponds to HIUD or XID.  (I take this as a command line arg to avoid hardcoding any confidential passwords.)
    * @param URL  - The URL of the page on the PIN server that is the front to the web application.
    * 
    ****************************************************************************************************************************************************/
    public PinSession(String uname, String pword, String URL ) {
        username = uname; 
        password = pword; 
        
        Pattern p = Pattern.compile("\\d{8}"); //if username is 8 digits long, assume it is an HUID,otherwise an XID.
		Matcher m = p.matcher(uname);
        if (m.matches()){
        	huidOrXid = "HUID";
        }else {
        	huidOrXid = "XID";
        }
        	
        url = URL;
        
        //Currently only handles Firefox driver.  TODO: Later add parameter, so that other drivers are supported
        driver = new FirefoxDriver();
    }
    
    public WebDriver login(){
    	String sourceType = new String(); //this will help us determine whether to select XID or HUID from radio buttons 
    	
        driver.navigate().to(url);
    	// Find the text input element by its name (in this case XID option)
        
        if (huidOrXid.equalsIgnoreCase("HUID")){
        	
        	sourceType = new String("compositeAuthenticationSourceType1");  
        	
        } else if (huidOrXid.equalsIgnoreCase("XID")){
        	
        	sourceType = new String("compositeAuthenticationSourceType2");
        }
        
        //These elements are all dependent on the HTML in the PIN login page
        WebElement element1 = driver.findElement(By.id(sourceType));
        WebElement element2 = driver.findElement(By.id("username"));
        WebElement element3 = driver.findElement(By.id("password"));       
        WebElement element4 = driver.findElement(By.name("_eventId_submit"));
        		
        // Submit credentials 
        element1.click();
        element2.sendKeys(username);
        element3.sendKeys(password);
        element4.click();
        
        return driver;
    }

    
    /*******************************************************************
     * This is a simple static method to cause the control flow to pause. 
     * @param time in milliseconds
     ******************************************************************/
    public static void sleep(int time){
        try {
            Thread.sleep(time);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
