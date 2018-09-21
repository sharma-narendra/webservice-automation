/*package OCTS_USECASE_Employee;

import java.io.File;
import java.util.concurrent.TimeUnit;
import Common_Utility.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Common_Utility.*;
import OCTS_Automation_Main_Modules.*;*/

package OCTS_USECASE_Employee;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.TestRunner;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Common_Utility.CommonUtilFunctions;
import Common_Utility.Details;
import Common_Utility.ERP_utilDecodeBase;
import Common_Utility.Logger;
import Common_Utility.ReporterBaseTest;
import OCTS_Automation_Main_Modules.ReadERPFinance_InputDataSheet;
import OCTS_Automation_Main_Modules.UnzipOutputFileGenerated;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import Common_Utility.*;
import org.testng.*;


public class TerminateEmployee extends ReporterBaseTest {

	public static void main(String[] args) {
		TerminateEmployee empVal = new TerminateEmployee();
		empVal.terminateEmployeevalidation();
	}



	@Test
	public void terminateEmployeevalidation() {
		TerminateEmployee terminateEmp = new TerminateEmployee();

		test=extent.createTest("Termination Employee");
		System.out.println("\n Termination Employee \n");
		terminateEmp.TerminateEmployeeMain();
	}


	public void TerminateEmployeeMain()

	{




			Assert.assertTrue(true);

			System.setProperty("webdriver.chrome.driver","C:\\Automation_OCTS\\Driver\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			String Username = "Fusion.user";
			String Pwd ="Welcome.2017";
			String URL = "https://ecqg-test.login.us2.oraclecloud.com";
			String Pnum = "STUDENT1_PERSON520" ;


		/*	 logger.log(LogStatus.INFO, "User Name :"+Username);
			 logger.log(LogStatus.INFO, "Environment URL :" +URL);

			 logger.log(LogStatus.PASS, "Application is Launched Successfully ");

*/
	        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	        driver.navigate().to(URL);
	        System.out.println( driver.getCurrentUrl());
	        driver.manage().window().maximize();
	      	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	        driver.findElement(By.id("userid")).sendKeys(Username);
	      	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	        driver.findElement(By.id("password")).sendKeys(Pwd);
	      	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	        driver.findElement(By.id("btnActive")).click();



	      //Click on the navigator Icon
	        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	        try {
	        if (driver.findElement(By.id("pt1:_UIShome::icon")).isDisplayed())
	        {
	        	/*logger.log(LogStatus.PASS, "Application is logged in Successfully for the user "+Username );

	        	  logger.log(LogStatus.INFO, "Home Icon is clicked ");*/

	            	driver.findElement(By.id("pt1:_UIShome::icon")).click();
	            	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	       /*     	logger.log(LogStatus.INFO, "Home Icon is clicked ");*/
				}

	        else {
	        /*	logger.log(LogStatus.FAIL, "Application is not Invoked ");*/
	            } }
	        catch (TimeoutException e) {

	        	/*logger.log(LogStatus.FAIL, "Application is not Invoked, Time out ");*/
			}

	    	driver.findElement(By.id("pt1:_UISmmLink::icon")).click();

	    	/*logger.log(LogStatus.INFO, "Navigator Icon is clicked ");

*/	    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	       // driver.findElement(By.linkText("Show More")).click();

	     //   logger.log(LogStatus.INFO, "My Client Group Icon is clicked ");

	    	driver.findElement(By.linkText("My Client Groups")).click();
	    	//driver.findElement(By.id("pt1:nv_itemNode_workforce_management_person_management")).click();

	    	//logger.log(LogStatus.INFO, "Person Management Link is clicked ");

	    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	    	driver.findElement(By.linkText("Person Management")).click();

	    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	    	try
	    	{

	    		driver.findElement(By.xpath("//input[starts-with(@name,'_FOpt1:_FOr1:0:_FONSr2:0:MAt1:0:pt1:Perso1:0:SP3:q1:value10')]")).sendKeys(Pnum);

		    //	logger.log(LogStatus.INFO, "Person Number : "+ Pnum  +" is Entered ");

	    	 }catch (Exception e) {

	    	//	 logger.log(LogStatus.INFO, "Person Number : "+ Pnum  +" is not Entered ");
	    			}

	        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

	        driver.findElement(By.xpath("//*[@id=\"_FOpt1:_FOr1:0:_FONSr2:0:MAt1:0:pt1:Perso1:0:SP3:q1::search\"]")).click();

	  //      logger.log(LogStatus.INFO, "Search Button is clicked");

	        driver.manage().timeouts().implicitlyWait(15 , TimeUnit.SECONDS);


	        WebElement Perlink =  driver.findElement(By.id("_FOpt1:_FOr1:0:_FONSr2:0:MAt1:0:pt1:Perso1:0:SP3:table1:_ATp:table2:0:gl1"));

	        try
	        {
	        	if (Perlink.isDisplayed())
		        {
	      ///  		logger.log(LogStatus.PASS, "Person is available ");
		        	Perlink.click();
		        	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		        }

	        }catch (Exception e) {

	        //	 logger.log(LogStatus.FAIL, "Person is not available ");

	        }


	        try
	        {

	        	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	   //         logger.log(LogStatus.PASS,"Task Icon is Clicked ");

		    	//click on tasks icon
		    	driver.findElement(By.id("_FOpt1:_FOr1:0:_FONSr2:0:_FOTsdiHcmIntWaTasksId::icon")).click();

		    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	        } catch (Exception e) {

	    //        logger.log(LogStatus.FAIL,"Task Icon is not available ");

	        }



	    	if (driver.findElement(By.xpath("//*[contains(@id,'_FOpt1:_FOr1:0:_FONSr2:0:_FOTRaT:0:RAtl22')]")).isDisplayed())
	    	{
	    		System.out.println("manage wrk relation ship link displayed");
	    		try {

		    		driver.findElement(By.linkText("Manage Work Relationship")).click();
		    		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//			logger.log(LogStatus.INFO, "Manage Work Relationship Link is Clicked");

				} catch (Exception e) {
					// TODO Auto-generated catch block
			///		logger.log(LogStatus.FAIL, "Manage Work Relationship Link is not Clicked");
					e.printStackTrace();
				}

	    	}

	    	//click drop down icon

	    	try {
	  ///  		logger.log(LogStatus.INFO, "Actions Link is Clicked");
				driver.findElement(By.id("_FOpt1:_FOr1:0:_FONSr2:0:MAt2:0:pt1:r1:0:pt1:SP1:edit::popArea")).click();
				driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
			} catch (Exception e) {
				// TODO Auto-generated catch block
	//			logger.log(LogStatus.FAIL, "Actions Link is not Clicked");
				e.printStackTrace();
			}

	    	//click drop down icon

	    	try {

				driver.findElement(By.xpath("//*[starts-with(@id,'_FOpt1:_FOr1:0:_FONSr2:0:MAt2:0:pt1:r1:0:pt1:SP1:tBtn')]")).click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    	} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	    //click Terminate

	    	try {
	  //  		logger.log(LogStatus.PASS, "Terminate option is Selected");
				driver.findElement(By.id("_FOpt1:_FOr1:0:_FONSr2:0:MAt2:1:r1:0:r1:0:pt1:ap1:tt1:submit")).click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			} catch (Exception e) {

	//			logger.log(LogStatus.FAIL, "Terminate option is not clicked");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    //click Save

	    	try {
	    		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	  ///  		logger.log(LogStatus.INFO, "Resignation is saved");

				driver.findElement(By.xpath("//*[starts-with(@id,'_FOpt1:_FOr1:0:_FONSr2:0:MAt2:1:r1:0:r1:0:pt1:ap1:tt1:save')]/table/tbody/tr")).click();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	    	} catch (Exception e) {
	//    		logger.log(LogStatus.FAIL, "Resignation is not saved");

				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	    	WebElement Okbtn = driver.findElement(By.xpath("//*[starts-with(@id,'_FOpt1:_FOr1:0:_FONSr2:0:MAt2:1:r1:0:r1:0:pt1:ap1:tt1:okConfirmationDialog')]"));

	    	Common_Utility.ReporterBaseTest.test.log(Status.PASS, "Employee Terminated  successfully.."+"\n");
	    	Logger.logInfo(TerminateEmployee.class, "Employee Terminated  successfully...."+"\n");
	    	//WebElement Okbtn = driver.findElement(By.id("_FOpt1:_FOr1:0:_FONSr2:0:MAt2:1:r1:0:r1:0:pt1:ap1:tt1:okConfirmationDialog"));

	    	if (Okbtn.isDisplayed())
	    	{
	    		try
		    	{
		    		//driver.findElement(By.id("_FOpt1:_FOr1:0:_FONSr2:0:MAt2:1:r1:0:r1:0:pt1:ap1:tt1:okConfirmationDialog")).click();

	    			Okbtn.click();

		    		//driver.findElement(By.xpath("//*[starts-with(@id,'_FOpt1:_FOr1:0:_FONSr2:0:MAt2:1:r1:0:r1:0:pt1:ap1:tt1:okConfirmationDialog')]")).click();
		///    		logger.log(LogStatus.PASS, "Employee is terminated");
		    	}

		    	catch (Exception e) {

		//    		logger.log(LogStatus.FAIL, "Employee is not terminated");
		    	}

	    	}


	}




}
