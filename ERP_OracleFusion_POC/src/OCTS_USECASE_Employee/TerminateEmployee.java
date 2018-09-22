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

	/*
	public static void main(String[] args) {
		TerminateEmployee empVal = new TerminateEmployee();
		empVal.TerminateEmployeeMain();
	}
*/

	@Test
	public void terminateEmployeevalidation() {
		TerminateEmployee terminateEmp = new TerminateEmployee();

		test=extent.createTest("HCM - Terminate Employee");
		System.out.println("Termination Employee");
		terminateEmp.TerminateEmployeeMain();
	}


	public void TerminateEmployeeMain()

	{

		//	Assert.assertTrue(true);
		Xls_Reader XL ;
		XL = new Xls_Reader("C:\\Automation_OCTS\\Data\\LoginDetails.xlsx");

		String Username = XL.getCellData("LaunchApplication", "UserName", 4);
		String Pwd =XL.getCellData("LaunchApplication", "Password", 4);
		String URL = XL.getCellData("LaunchApplication", "URL", 4);
	    String Pnum = null;

			//System.setProperty("webdriver.chrome.driver","C:\\Automation_OCTS\\Driver\\chromedriver.exe");

	    System.setProperty("webdriver.chrome.driver","C:\\Automation_OCTS\\Driver\\chromedriver.exe");
	    WebDriver driver = new ChromeDriver();

			 Common_Utility.ReporterBaseTest.test.log(Status.INFO, "User Name :"+Username);
			 Common_Utility.ReporterBaseTest.test.log(Status.INFO, "Environment URL :" +URL);

			 Common_Utility.ReporterBaseTest.test.log(Status.PASS, "Application is Launched Successfully "+"\n");


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
	        	Common_Utility.ReporterBaseTest.test.log(Status.PASS, "Application is logged in Successfully for the user "+ Username );

	            	driver.findElement(By.id("pt1:_UIShome::icon")).click();
	            	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	          	Common_Utility.ReporterBaseTest.test.log(Status.INFO, "Home Icon is clicked "+"\n");
				}

	        else {
	        Common_Utility.ReporterBaseTest.test.log(Status.FAIL, "Application is not Invoked "+"\n");
	            } }
	        catch (TimeoutException e) {

	        	Common_Utility.ReporterBaseTest.test.log(Status.FAIL, "Application is not Invoked, Time out "+"\n");
			}

	    	driver.findElement(By.id("pt1:_UISmmLink::icon")).click();

	    	Common_Utility.ReporterBaseTest.test.log(Status.INFO, "Navigator Icon is clicked "+"\n");

         	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	       // driver.findElement(By.linkText("Show More")).click();

	         Common_Utility.ReporterBaseTest.test.log(Status.INFO, "My Client Group Icon is clicked "+"\n");

	    	driver.findElement(By.linkText("My Client Groups")).click();
	    	//driver.findElement(By.id("pt1:nv_itemNode_workforce_management_person_management")).click();

	    	Common_Utility.ReporterBaseTest.test.log(Status.INFO, "Person Management Link is clicked "+"\n");

	    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	    	driver.findElement(By.linkText("Person Management")).click();

	    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


	    	try {

				XL = new Xls_Reader("C:\\Automation_OCTS\\Data\\InputDataSheet\\HCM_GUI_TerminateEmp.xlsx");
				String ColName = "PersonNumber";
				String SheetName = "Sheet1";
				int Rows = XL.getRowCount(SheetName);
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				Common_Utility.ReporterBaseTest.test.log(Status.INFO,"No of Workers to be Terminated");

		for ( int i = 2; i <=Rows ; i++)
		{

			Pnum =	XL.getCellData(SheetName, ColName, i);
		    driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			System.out.println(Pnum);
			Common_Utility.ReporterBaseTest.test.log(Status.INFO,"**** Terminating Employee with Person Number : "+Pnum +"****" );
			TerminateAction(driver, Pnum);
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			driver.close();

		}

	}catch(Exception e)
	{

		e.printStackTrace();
	}

}

//********************************************************************************************
	public void TerminateAction( WebDriver driver,String Pnum) {


	try
    	{
    		driver.findElement(By.xpath("//input[starts-with(@name,'_FOpt1:_FOr1:0:_FONSr2:0:MAt1:0:pt1:Perso1:0:SP3:q1:value10')]")).sendKeys(Pnum);

	        Common_Utility.ReporterBaseTest.test.log(Status.INFO, "Person Number : "+ Pnum  +" is Entered "+"\n");

    	 }catch (Exception e) {

    	     Common_Utility.ReporterBaseTest.test.log(Status.INFO, "Person Number : "+ Pnum  +" is not Entered "+"\n");
    			}

        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//*[@id=\"_FOpt1:_FOr1:0:_FONSr2:0:MAt1:0:pt1:Perso1:0:SP3:q1::search\"]")).click();

        Common_Utility.ReporterBaseTest.test.log(Status.INFO, "Search Button is clicked"+"\n");

        driver.manage().timeouts().implicitlyWait(15 , TimeUnit.SECONDS);


        WebElement Perlink =  driver.findElement(By.id("_FOpt1:_FOr1:0:_FONSr2:0:MAt1:0:pt1:Perso1:0:SP3:table1:_ATp:table2:0:gl1"));

        try
        {
        	if (Perlink.isDisplayed())
	        {
            	Common_Utility.ReporterBaseTest.test.log(Status.PASS, "Person is available "+"\n");
	        	Perlink.click();
	        	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	        }

        }catch (Exception e) {

         Common_Utility.ReporterBaseTest.test.log(Status.FAIL, "Person is not available "+"\n");

        }


        try
        {

        	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            Common_Utility.ReporterBaseTest.test.log(Status.PASS,"Task Icon is Clicked "+"\n");

	    	//click on tasks icon
	    	driver.findElement(By.id("_FOpt1:_FOr1:0:_FONSr2:0:_FOTsdiHcmIntWaTasksId::icon")).click();

	    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        } catch (Exception e) {

          Common_Utility.ReporterBaseTest.test.log(Status.FAIL,"Task Icon is not available "+"\n");

        }


    	if (driver.findElement(By.xpath("//*[contains(@id,'_FOpt1:_FOr1:0:_FONSr2:0:_FOTRaT:0:RAtl22')]")).isDisplayed())
    	{
    		System.out.println("manage wrk relation ship link displayed"+"\n");
    		try {

	    		driver.findElement(By.linkText("Manage Work Relationship")).click();
	    		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	Common_Utility.ReporterBaseTest.test.log(Status.INFO, "Manage Work Relationship Link is Clicked"+"\n");

			} catch (Exception e) {
				// TODO Auto-generated catch block
		Common_Utility.ReporterBaseTest.test.log(Status.FAIL, "Manage Work Relationship Link is not Clicked"+"\n");
				e.printStackTrace();
			}

    	}

    	//click drop down icon

    	try {
            Common_Utility.ReporterBaseTest.test.log(Status.INFO, "Actions Link is Clicked"+"\n");
			driver.findElement(By.id("_FOpt1:_FOr1:0:_FONSr2:0:MAt2:0:pt1:r1:0:pt1:SP1:edit::popArea")).click();
			driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
            Common_Utility.ReporterBaseTest.test.log(Status.FAIL, "Actions Link is not Clicked"+"\n");
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
           Common_Utility.ReporterBaseTest.test.log(Status.PASS, "Terminate option is Selected"+"\n");
			driver.findElement(By.id("_FOpt1:_FOr1:0:_FONSr2:0:MAt2:1:r1:0:r1:0:pt1:ap1:tt1:submit")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch (Exception e) {

            Common_Utility.ReporterBaseTest.test.log(Status.FAIL, "Terminate option is not clicked"+"\n");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    //click Save

    	try {
    		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            Common_Utility.ReporterBaseTest.test.log(Status.INFO, "Resignation is saved"+"\n");

			driver.findElement(By.xpath("//*[starts-with(@id,'_FOpt1:_FOr1:0:_FONSr2:0:MAt2:1:r1:0:r1:0:pt1:ap1:tt1:save')]/table/tbody/tr")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    	} catch (Exception e) {
        Common_Utility.ReporterBaseTest.test.log(Status.FAIL, "Resignation is not saved"+"\n");

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    	WebElement Okbtn = driver.findElement(By.xpath("//*[starts-with(@id,'_FOpt1:_FOr1:0:_FONSr2:0:MAt2:1:r1:0:r1:0:pt1:ap1:tt1:okConfirmationDialog')]"));

    	Common_Utility.ReporterBaseTest.test.log(Status.PASS, "Employee Terminated  successfully.."+"\n"+"\n");
    	Logger.logInfo(TerminateEmployee.class, "Employee Terminated  successfully...."+"\n"+"\n");
    	//WebElement Okbtn = driver.findElement(By.id("_FOpt1:_FOr1:0:_FONSr2:0:MAt2:1:r1:0:r1:0:pt1:ap1:tt1:okConfirmationDialog"));

    	if (Okbtn.isDisplayed())
    	{
    		try
	    	{
	    		//driver.findElement(By.id("_FOpt1:_FOr1:0:_FONSr2:0:MAt2:1:r1:0:r1:0:pt1:ap1:tt1:okConfirmationDialog")).click();

    			Okbtn.click();

	    		//driver.findElement(By.xpath("//*[starts-with(@id,'_FOpt1:_FOr1:0:_FONSr2:0:MAt2:1:r1:0:r1:0:pt1:ap1:tt1:okConfirmationDialog')]")).click();
	 		Common_Utility.ReporterBaseTest.test.log(Status.PASS, "Employee is terminated"+"\n");
	    	}

	    	catch (Exception e) {

         	Common_Utility.ReporterBaseTest.test.log(Status.FAIL, "Employee is not terminated"+"\n");
	    	}

    	}


}






	}
