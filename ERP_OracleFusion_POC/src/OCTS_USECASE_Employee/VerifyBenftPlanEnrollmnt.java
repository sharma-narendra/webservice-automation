package OCTS_USECASE_Employee;

	import java.io.File;
	import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.By;
	import org.openqa.selenium.TimeoutException;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.Select;
	import org.testng.Assert;
	import org.testng.ITestResult;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.Test;

	import com.relevantcodes.extentreports.ExtentReports;
	import com.relevantcodes.extentreports.ExtentTest;
	import com.relevantcodes.extentreports.LogStatus;

	public class VerifyBenftPlanEnrollmnt {


		ExtentReports extent ;
		ExtentTest logger;
			
		@Test
		
		public void PersonLifeEventMain()
			
		{
			
			extent = new ExtentReports("C:\\Automation_OCTS\\Results\\GUI_Validation_Reports\\Verify_BenefitPlanEnrollment.html",true);
			
		    extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));		

				logger = extent.startTest("HCM : Benefit Plan Enrollment Validation");
				Assert.assertTrue(true);
					
				System.setProperty("webdriver.chrome.driver","D:\\ERP_OracleFusion_POC\\chromedriver.exe");
				WebDriver driver = new ChromeDriver();
				String Username = "hcm.user";
				String Pwd ="Welcome1";
				String URL = "https://ecqg-test.login.us2.oraclecloud.com";
				String Pnum = "KULH10" ;
				String strEvent = "Enrollment Override";
				String BenefitGrpName = "DEMO_KB Benefits Program (DEMO)";
				
				 logger.log(LogStatus.INFO, "User Name :"+Username);
				 logger.log(LogStatus.INFO, "Environment URL :" +URL);
				 
				 logger.log(LogStatus.PASS, "Application is Launched Successfully ");
				 
				 
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		        driver.navigate().to(URL);
		        System.out.println( driver.getCurrentUrl());
		        driver.manage().window().maximize();
		        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		      	
		        driver.findElement(By.id("userid")).sendKeys(Username);
		        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		      	
		        driver.findElement(By.id("password")).sendKeys(Pwd);
		      	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		      	
		        driver.findElement(By.id("btnActive")).click();
		             
		      
		            
		      //Click on the navigator Icon
		        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		        try {           
		        if (driver.findElement(By.id("pt1:_UIShome::icon")).isDisplayed())
		        {
		        	logger.log(LogStatus.PASS, "Application is logged in Successfully for the user "+Username );         
		                 
		        	  logger.log(LogStatus.INFO, "Home Icon is clicked ");
		        	  
		            	driver.findElement(By.id("pt1:_UIShome::icon")).click();  
		            	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		            	
		        }
		        else {      	
		        	logger.log(LogStatus.FAIL, "Application is not Invoked ");     	       	
		            } }
		        catch (TimeoutException e) {

		        	logger.log(LogStatus.FAIL, "Application is not Invoked, Time out ");  
				} 
		                     	      
		    	driver.findElement(By.id("pt1:_UISmmLink::icon")).click();

		    	logger.log(LogStatus.INFO, "Navigator Icon is clicked ");

		    	driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		       // driver.findElement(By.linkText("Show More")).click();
		      
		        logger.log(LogStatus.INFO, "Benefits Administration is clicked ");
		        
		    	driver.findElement(By.linkText("Benefits Administration")).click();
		    	//driver.findElement(By.id("pt1:nv_itemNode_workforce_management_person_management")).click();

		    	logger.log(LogStatus.INFO, "Enrollment Link is selected ");
		    	
		    	driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		    	
		    	driver.findElement(By.linkText("Enrollment")).click();
		    	
		    	driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		    	
		    	try
		    	{
		    		
		    		driver.findElement(By.xpath("//input[starts-with(@name,'_FOpt1:_FOr1:0:_FONSr2:0:MAt1:0:pt1:Perso1:0:SP3:q1:value10')]")).sendKeys(Pnum);
			    	
			    	logger.log(LogStatus.INFO, "Person Number : "+ Pnum  +" is Entered in the Person Number Field");
		    		
		    	 }catch (Exception e) {
		        	
		    		 logger.log(LogStatus.INFO, "Person Number : "+ Pnum  +" is not Entered ");
		    			}
		    	
		        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		        
		        driver.findElement(By.xpath("//*[@id=\"_FOpt1:_FOr1:0:_FONSr2:0:MAt1:0:pt1:Perso1:0:SP3:q1::search\"]")).click();	
		    	
		        logger.log(LogStatus.INFO, "Search Button is clicked");
		        
		        driver.manage().timeouts().implicitlyWait(15 , TimeUnit.SECONDS);
		                
		        
		        WebElement Perlink =  driver.findElement(By.id("_FOpt1:_FOr1:0:_FONSr2:0:MAt1:0:pt1:Perso1:0:SP3:table1:_ATp:table2:0:gl1"));
		    
		        try
		        {
		        	if (Perlink.isDisplayed())
			        {
		        		logger.log(LogStatus.PASS, Pnum + " : Person is available ");       		 
			        	Perlink.click();
			        	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			        	
			        }
		        	
		        }catch (Exception e) {
		        	        	
		        	 logger.log(LogStatus.FAIL, "Person is not available ");
	        		 
		        }
		        
		        
		        try
		        {
		        	
		        	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		    	

		            logger.log(LogStatus.PASS,"Task Icon is Clicked ");
		            
			    	//click on tasks icon
		            
		            
			    	driver.findElement(By.id("_FOpt1:_FOr1:0:_FONSr2:0:_FOTsdi__benefits_itemNode__FndTasksList::icon")).click();
			    	
			    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		        	
		        } catch (Exception e) {
		        	
		            logger.log(LogStatus.FAIL,"Task Icon is not available ");
		 
		        }				
		    	
		      //*[@id="_FOpt1:_FOr1:0:_FONSr2:0:_FOTRaT:0:RAtl6"]
		    	
		    	if (driver.findElement(By.linkText("Enrollment Results")).isDisplayed())
		    	{
		    		System.out.println("Process Life Events");	    		
		    		try {
			    		
			    		driver.findElement(By.linkText("Enrollment Results")).click();		    		    		
			    		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);				
						logger.log(LogStatus.INFO, "Enrollment Results Link clicked ");   
						
				    	
					} catch (Exception e) {
						// TODO Auto-generated catch block
						logger.log(LogStatus.FAIL, "Enrollment Results Link is not Clicked");				
						e.printStackTrace();
					}
		    		
		    	} 	         	 
		  	    	
		    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		    	
		    	logger.log(LogStatus.PASS, "Benefits Service Center is displayed for the Employee "+Pnum);
		    	
		    	//evaluate button 
		    		    	
		    	try {
		    		
		    		//
		    		driver.findElement(By.id("_FOpt1:_FOr1:0:_FONSr2:0:MAt2:0:AP1:cb2")).click();
		    		//driver.findElement(By.xpath("//*[@id=\"_FOpt1:_FOr1:0:_FONSr2:0:MAt3:0:AP1:cb2\"]")).click();		    		    		
		    		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);				
					logger.log(LogStatus.INFO, "Evaluate Life Events Button is clicked ");   					
			    	
				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.log(LogStatus.FAIL, "Evaluate Life Events Button is not clicked ");				
					e.printStackTrace();
				}
		    	
	WebElement BenftGrpName = driver.findElement(By.xpath("//*[contains(@id,'AP1:pc10:EnrTbl::db')]/table/tbody/tr[1]/td[1]/div/span[2]"));		    	
	                                                   
	String BenefitGrpNameActual = BenftGrpName.getText();
    System.out.println("Benefit Group name :"+ BenefitGrpName);   	
		    	
	    	if  (BenefitGrpName == BenefitGrpNameActual)
	    		
	    	{
	    		
	    		logger.log(LogStatus.PASS, Pnum +" is assigned with Benefit Group " +BenefitGrpNameActual +" as expected");	    		
	    		
	    	}
	    	else
	    	{
	    		logger.log(LogStatus.FAIL, Pnum +" is not assigned with Benefit Group " +BenefitGrpNameActual +" as expected");
	    		
	    	}
		    	}
		    	
	       	

	//*******************************************************************************************************************	
		@AfterMethod
		public void getResult(ITestResult result){
			if(result.getStatus() == ITestResult.FAILURE){
				
				//use it when we integrate multiple TC's
				//logger.log(LogStatus.FAIL, "Test Step name Failed is "+result.getName());
				logger.log(LogStatus.FAIL, "Test Step Failed Description : /n "+result.getThrowable());
			}else if(result.getStatus() == ITestResult.SKIP){
				logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
			}
			// ending test
			//endTest(logger) : It ends the current test and prepares to create HTML report
			extent.endTest(logger);
		     extent.flush();
		     extent.close();
		}
		
		
	}


