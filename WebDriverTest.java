
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.ExpectedCondition;
//import org.openqa.selenium.support.ui.WebDriverWait;

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
        		                   password,//TODO: Put these long URLS in the Defined Constants file
        		                   "https://www.pin1.harvard.edu/cas/login?service=https%3A%2F%2Fwww.pin1.harvard.edu%2Fpin%2Fauthenticate%3F__authen_application%3DHARVARD_PORTAL_NEW%26authority%3DPIN%26target%3Dhttps%253A%252F%252Flogin.icommons.harvard.edu%252Fpinproxy%252Fpinlogin%253Fproxy_requested_url%253Dhttp%25253A%25252F%25252Fisites.harvard.edu%25252Ficb%25252Ficb.do%2525253Fkeyword%25253Dk28781%252526login%25253Dyes%2526_logout_callback_url%253Dhttp%25253A%25252F%25252Fisites.harvard.edu%25253A80%25252Ficb%25252Flogoutcallback.do"
        		                   //"https://www.pin1.harvard.edu/cas/login?service=https%3A%2F%2Fwww.pin1.harvard.edu%2Fpin%2Fauthenticate%3F__authen_application%3DHARVARD_PORTAL_NEW%26authority%3DPIN%26target%3Dhttps%253A%252F%252Flogin.icommons.harvard.edu%252Fpinproxy%252Fpinlogin%253Fproxy_requested_url%253Dhttp%25253A%25252F%25252Fmy.harvard.edu%25252Ficb%25252Ficb.do%2525253Fkeyword%25253Dmyharvard%2526_logout_callback_url%253Dhttp%25253A%25252F%25252Fmy.harvard.edu%25253A80%25252Ficb%25252Flogoutcallback.do"
        		                   );

        //Take the steps of logging in, and get a handle on the session driver. Will be redirected to start page of application 
        driver = p.login();
        /***************************************************************
         * This is where the actual application specific testing begins 
         ***************************************************************/
        
        //navigate to link to take quiz (first question of quiz)   //I could find the element, but in this case the first step is to click on a link              
        //driver.navigate().to("http://isites.harvard.edu/icb/icb.do?keyword=k28781&topicId=icb.topic1212426&pageContentId=icb.pagecontent1227642&pageid=icb.page571185&panel=icb.pagecontent1227642%3Ar%2Fquiz%2Ftake%2F37#quizmo-icb.topic1212426");
              

        //Need to pause here to give page time to reload, otherwise we won't be able to select element
        PinSession.sleep(2000);
    	// First question is an Essay, 
        /***FIRST PAGE OF QUIZ*******/
       /** WebElement element1 = driver.findElement(By.id("essay-text"));

        PinSession.sleep(2000);
        
        element1.sendKeys(DefinedConstants.MULTIBYTE_CHAR_TEXT);
        PinSession.sleep(3000); 
        WebElement element2 = driver.findElement(By.id("question_2"));
        PinSession.sleep(2000);
        element2.click();
        PinSession.sleep(2000);
        //***Second PAGE OF QUIZ (multiple choice) 
        //Correctly select item A, should give me 10 points
        WebElement element3 = driver.findElement(By.id("answer346"));
        WebElement element4 = driver.findElement(By.id("quiz-submit-btn"));  
        PinSession.sleep(2000);
        element3.click();
        element4.click();
        PinSession.sleep(2000);   **/
        // ***Third PAGE OF QUIZ (Fill in the blank)*******/
        
        //Close the browser
        //driver.quit();
    }
}