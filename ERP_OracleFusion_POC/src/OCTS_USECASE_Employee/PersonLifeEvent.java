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

import com.aventstack.extentreports.Status;


import Common_Utility.ReporterBaseTest;
import Common_Utility.Xls_Reader;

public class PersonLifeEvent extends ReporterBaseTest{


	Xls_Reader XL ;

	@Test
	public void PersonLifeEventvalidation() {

		PersonLifeEvent Per = new PersonLifeEvent();

		test=extent.createTest("Person Life Event - Employee");
		System.out.println("\n Person Life Event ");
		Per.PersonLifeEventMain();
	}


	public void PersonLifeEventMain()

	{

		XL = new Xls_Reader("C:\\Automation_OCTS\\Data\\LoginDetails.xlsx");

		String Username = XL.getCellData("LaunchApplication", "UserName",4);
		String Pwd =XL.getCellData("LaunchApplication", "Password",4);
		String URL = XL.getCellData("LaunchApplication", "URL",4);
	    String Pnum = null;

			System.setProperty("webdriver.chrome.driver","C:\\Automation_OCTS\\Driver\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();

			 Common_Utility.ReporterBaseTest.test.log(Status.INFO, "User Name :"+Username);
			 Common_Utility.ReporterBaseTest.test.log(Status.INFO, "Environment URL :" +URL);

			 Common_Utility.ReporterBaseTest.test.log(Status.PASS, "Application is Launched Successfully "+"\n");

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
        	Common_Utility.ReporterBaseTest.test.log(Status.PASS, "Application is logged in Successfully for the user "+Username );
        	Common_Utility.ReporterBaseTest.test.log(Status.INFO, "Home Icon is clicked "+"\n");
        	driver.findElement(By.id("pt1:_UIShome::icon")).click();
        	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        else {
        	Common_Utility.ReporterBaseTest.test.log(Status.FAIL, "Application is not Invoked "+"\n");
            } }
        catch (TimeoutException e) {

        	Common_Utility.ReporterBaseTest.test.log(Status.FAIL, "Application is not Invoked, Time out "+"\n");
		}

	    	driver.findElement(By.id("pt1:_UISmmLink::icon")).click();
	    	Common_Utility.ReporterBaseTest.test.log(Status.INFO, "Navigator Icon is clicked "+"\n");
	    	driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
	       // driver.findElement(By.linkText("Show More")).click();

	        Common_Utility.ReporterBaseTest.test.log(Status.INFO, "Benefits Administration is clicked "+"\n");
	    	driver.findElement(By.linkText("Benefits Administration")).click();
	    	//driver.findElement(By.id("pt1:nv_itemNode_workforce_management_person_management")).click();

	    	Common_Utility.ReporterBaseTest.test.log(Status.INFO, "Enrollment Link is selected "+"\n");
	    	driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
	    	driver.findElement(By.linkText("Enrollment")).click();
	    	driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

         try {

				XL = new Xls_Reader("C:\\Automation_OCTS\\Data\\InputDataSheet\\HCM_GUI_PersonLifeEvent.xlsx");
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
			AddLifeEvent(driver,Pnum);
			driver.close();

		}

	}catch(Exception e)
	{

		e.printStackTrace();
	}
         driver.quit();


 }

	public void AddLifeEvent(WebDriver driver,String Pnum)
	{

		 String strEvent = "Enrollment Override";
		try
    	{

    		driver.findElement(By.xpath("//input[starts-with(@name,'_FOpt1:_FOr1:0:_FONSr2:0:MAt1:0:pt1:Perso1:0:SP3:q1:value10')]")).sendKeys(Pnum);
	    	Common_Utility.ReporterBaseTest.test.log(Status.INFO, "Person Number : "+ Pnum  +" is Entered in the Person Number Field"+"\n");

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
        		Common_Utility.ReporterBaseTest.test.log(Status.PASS, Pnum + " : Person is available "+"\n");
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
	    	driver.findElement(By.id("_FOpt1:_FOr1:0:_FONSr2:0:_FOTsdi__benefits_itemNode__FndTasksList::icon")).click();
	    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        } catch (Exception e) {

            Common_Utility.ReporterBaseTest.test.log(Status.FAIL,"Task Icon is not available "+"\n");

        }

    	if (driver.findElement(By.linkText("Person Life Events")).isDisplayed())
    	{
    		System.out.println("Person Life Events"+"\n");
    		try {

	    		driver.findElement(By.linkText("Person Life Events")).click();
	    		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				Common_Utility.ReporterBaseTest.test.log(Status.INFO, "Person Life Events Link  is clicked "+"\n");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				Common_Utility.ReporterBaseTest.test.log(Status.FAIL, "Person Life Events Link is not Clicked"+"\n");
				e.printStackTrace();
			}

    	}

    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    	//WebElement PotLifEventTab = driver.findElement(By.id("_FOpt1:_FOr1:0:_FONSr2:0:MAt2:0:AP1:showDetailItem2::disAcr"));

        WebElement PotLifEventLink = driver.findElement(By.linkText("Potential Life Events"));

    	if (PotLifEventLink.isDisplayed())
    	{
    		try {

    			PotLifEventLink.click();
	    		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				Common_Utility.ReporterBaseTest.test.log(Status.INFO, "Potential Life Events is clicked "+"\n");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				Common_Utility.ReporterBaseTest.test.log(Status.FAIL, "Potential Life Events is not clicked"+"\n");
				e.printStackTrace();
			}

    	}

    	WebElement CreateIcon =  driver.findElement(By.id("_FOpt1:_FOr1:0:_FONSr2:0:MAt2:0:AP1:AT2:_ATp:create::icon"));

    	if (CreateIcon.isDisplayed())
    	{
    		try
    		{
    			CreateIcon.click();
    		    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    			Common_Utility.ReporterBaseTest.test.log(Status.PASS, "Create Icon is not Clicked"+"\n");
    		}
    		catch (Exception e) {
				// TODO Auto-generated catch block
				Common_Utility.ReporterBaseTest.test.log(Status.FAIL, "Create Icon is not Clicked"+"\n");
				e.printStackTrace();
			}

    	}

WebElement eventList = driver.findElement(By.xpath("//*[contains(@id,'soc3::content')]"));
	Select event = new Select(eventList);

    	try
    	{
    	 event.selectByVisibleText(strEvent);
 	       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Common_Utility.ReporterBaseTest.test.log(Status.PASS, strEvent + " is assigned to the Employee "+"\n");

    	} catch (Exception e) {
			// TODO Auto-generated catch block
			Common_Utility.ReporterBaseTest.test.log(Status.FAIL,  strEvent + "is not selected from the List " );
			e.printStackTrace();
		}


   WebElement Savebtn =  driver.findElement(By.xpath(" //*[contains(@id,'AP1:commandButton2')]"));

   if (Savebtn.isDisplayed())
   {
      try
      {
       	  Savebtn.click();
	      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	      Common_Utility.ReporterBaseTest.test.log(Status.PASS, "Person Life Event "+ strEvent+" is added to the Employee " +Pnum +" successfully"+"\n");

      }catch (Exception e) {
			// TODO Auto-generated catch block
			Common_Utility.ReporterBaseTest.test.log(Status.FAIL, "Person Life Event is not added"+"\n");
			e.printStackTrace();
		}

    }


   	WebElement OkButton =  driver.findElement(By.xpath("//*[contains(@id,'ok')]"));

	if (OkButton.isDisplayed())
	{
		try
		{
			OkButton.click();
			Common_Utility.ReporterBaseTest.test.log(Status.PASS, "OK Button is not Clicked"+"\n");
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			Common_Utility.ReporterBaseTest.test.log(Status.FAIL, "OK Button is not Clicked"+"\n");
			e.printStackTrace();
		}

	}


	}



}
