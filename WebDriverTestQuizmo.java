
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebDriverTestQuizmo  {
    public static void main(String[] args) {
    	
    	
    	String username = args[0];	//Get XID or HUID supplied by user as first command line argumen
    	String password = args[1];  //Get password supplied by user as second command line argument

        WebDriver driver; //This is a Selenium provided class.
        
        //Use the PinLogin Class and authenticate 
        PinSession p  = new PinSession(username, password, DefinedConstants.QUIZMO2_URL);
        		                   
        //Take the steps of logging in, and get a handle on the session driver. Will be redirected to start page of application 
        driver = p.login();
        
        /***************************************************************
         * This is where the actual application specific testing begins 
         ***************************************************************/
        driver.get("http://isites.harvard.edu/icb/icb.do?keyword=k28781&topicId=icb.topic1212426&pageContentId=icb.pagecontent1227642&pageid=icb.page571185&panel=icb.pagecontent1227642%3Ar%2Fquiz%2Ftake%2F37#quizmo-icb.topic1212426");

        /***Page 1 of quiz Essay question*******/
        testPage1Essay(DefinedConstants.PROBLEMATIC_CHARACTERS, driver);
        
        /***Page 2 of quiz - multiple choice question*******/
        testPage2ChooseOne ("//input[@value='350']", driver);
        
        /***Page 3 of quiz - True False question*******/
        testPage3TrueFalse("//input[@value='353']", driver);

        /***Page 4 of quiz - Multiple choice question*******/  
        testPage4MultiChoice(driver);
        
        /***Page 5 of quiz - Numeric choice question*******/    
        testPage5Numeric("9", driver);
 
        /***Page 6 of quiz - Fill in the blank quesiton *******/ 
        testPage6Fillin(driver);
         
        /****** Submit quiz *****/
        WebElement submit_button = driver.findElement(By.id("quiz-submit-btn"));
        submit_button.click();

        /**** confirm submit ******/
        PinSession.sleep(2000); 
        WebElement submit_confirm_button = driver.findElement(By.id("quiz-submit-confirm-btn"));
        submit_confirm_button.click();
        
        /** Close the browser ***/
         // driver.quit();
    }
    /***
     * This is a method that takes in the test to test. 
     * 
     * @param essay
     * @param d
     */
    private static void testPage1Essay (String essay, WebDriver d){
        PinSession.sleep(2000);
        WebElement page1 = d.findElement(By.id("essay-text"));
        System.out.println(essay);
        page1.sendKeys(essay);
        PinSession.sleep(2000);
        WebElement next_button = d.findElement(By.name("next"));
        next_button.click();
    	return;
    }
    /**
     * 
     * @param d
     */
    private static void testPage2ChooseOne (String x_path, WebDriver d){
        PinSession.sleep(4000); 
        //Need to use Xpath for radio button (or iterateover each)
        WebElement radioButton = d.findElement(By.xpath(x_path));
        radioButton.click();
        PinSession.sleep(1000); 
        WebElement next_button = d.findElement(By.name("next"));
        next_button.click();
    	return;
    }
    
    /**
     * TODO MODIFY SO THAT IT TAKES A LIST of XPATH values as a param
     * @param x_path
     * @param d
     */
    private static void testPage3TrueFalse(String x_path, WebDriver d){
        PinSession.sleep(2000);
        WebElement radioButtonTF = d.findElement(By.xpath("//input[@value='353']"));
        radioButtonTF.click();
        PinSession.sleep(1000); 
        WebElement next_button = d.findElement(By.name("next"));
        next_button.click();
        return;   
    }
    
    private static void testPage4MultiChoice (WebDriver d){
    	
        PinSession.sleep(2000);
        WebElement checkbox1 = d.findElement(By.xpath("//input[@value='355']")); 
        checkbox1.click();
        WebElement checkbox2 = d.findElement(By.xpath("//input[@value='357']")); 
        checkbox2.click();
        WebElement next_button = d.findElement(By.name("next"));
        next_button.click();
        return;
     }
    
    private static void testPage5Numeric (String num, WebDriver d){
	    PinSession.sleep(2000);
	    WebElement element_pg_5 = d.findElement(By.id("numerical-text"));
	    element_pg_5.sendKeys(num);
	    PinSession.sleep(1000);   
        WebElement next_button = d.findElement(By.name("next"));
        next_button.click();

    
    }
    private static void testPage6Fillin(WebDriver d){
	    PinSession.sleep(2000);
        WebElement page6_blank_1 = d.findElement(By.xpath("//input[@class='input-small fillin-text'][1]"));
        WebElement page6_blank2 = d.findElement(By.xpath("//input[@class='input-small fillin-text'][2]"));
        WebElement page6_blank3 = d.findElement(By.xpath("//input[@class='input-small fillin-text'][3]"));
        page6_blank_1.sendKeys("sunshine");
        page6_blank2.sendKeys("harp");
        page6_blank3.sendKeys("voice");
        PinSession.sleep(2000); 
        //next is to hit submit
        return;
    }
    
    
}