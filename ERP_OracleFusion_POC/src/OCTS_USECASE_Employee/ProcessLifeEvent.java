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

	public class ProcessLifeEvent extends ReporterBaseTest {


		Xls_Reader XL ;

	@Test

public void PersonLifeEventvalidation() {

		ProcessLifeEvent Per = new ProcessLifeEvent();

		test=extent.createTest("Person Life Event - Employee"+"\n");
		System.out.println("\n Person Life Event "+"\n");
		Per.ProcessLifeEventMain();
	}


	public void ProcessLifeEventMain()

		{

		XL = new Xls_Reader("C:\\Automation_OCTS\\Data\\LoginDetails.xlsx");

		String Username = XL.getCellData("LaunchApplication", "UserName", 3);
		String Pwd =XL.getCellData("LaunchApplication", "Password", 3);
		String URL = XL.getCellData("LaunchApplication", "URL", 3);
	    String Pnum = null;


		System.setProperty("webdriver.chrome.driver","C:\\Automation_OCTS\\Driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		//String strEvent = "Enrollment Override";

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
				XL = new Xls_Reader("C:\\Automation_OCTS\\Data\\InputDataSheet\\BenefitGroupTestData.xlsx");
				String ColName = "PersonNumber";
				String SheetName = "Header";
				int Rows = XL.getRowCount(SheetName);
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				Common_Utility.ReporterBaseTest.test.log(Status.INFO,"No of Workers to be Processed with Life Event :" + (Rows-1));

				for ( int i = 2; i <=Rows ; i++)
				{

				Pnum =	XL.getCellData(SheetName, ColName, i);
			    driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
				System.out.println(Pnum);
				Common_Utility.ReporterBaseTest.test.log(Status.INFO,"**** Validating Employee with Person Number : "+Pnum +"****" );
				AddlifeEvent(driver,Pnum);
				}

		}catch(Exception e)
		{

			e.printStackTrace();
		}
			driver.quit();
	}

    public void AddlifeEvent(WebDriver driver, String Pnum)

    {
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

      //*[@id="_FOpt1:_FOr1:0:_FONSr2:0:_FOTRaT:0:RAtl6"]

    	if (driver.findElement(By.linkText("Process Life Event")).isDisplayed())
    	{
    		System.out.println("Process Life Events"+"\n");
    		try {

	    		driver.findElement(By.linkText("Process Life Event")).click();
	    		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				Common_Utility.ReporterBaseTest.test.log(Status.INFO, "Process Life Event Link  is clicked "+"\n");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				Common_Utility.ReporterBaseTest.test.log(Status.FAIL, "Process Life Event Link is not Clicked"+"\n");
				e.printStackTrace();
			}

    	}

    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    	Common_Utility.ReporterBaseTest.test.log(Status.PASS, "Benefits Service Center is displayed for the Employee "+Pnum);

    	//evaluate button

    	try {

    		//
    		driver.findElement(By.id("_FOpt1:_FOr1:0:_FONSr2:0:MAt2:0:AP1:cb2")).click();
    		//driver.findElement(By.xpath("//*[@id=\"_FOpt1:_FOr1:0:_FONSr2:0:MAt3:0:AP1:cb2\"]")).click();
    		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Common_Utility.ReporterBaseTest.test.log(Status.INFO, "Evaluate Life Events Button is clicked "+"\n");
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			driver.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Common_Utility.ReporterBaseTest.test.log(Status.FAIL, "Evaluate Life Events Button is not clicked "+"\n");
			e.printStackTrace();
		}



    }



	}

