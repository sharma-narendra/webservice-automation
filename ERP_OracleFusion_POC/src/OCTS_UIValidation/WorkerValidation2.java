package OCTS_UIValidation;

import java.io.IOException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.aventstack.extentreports.Status;

import Common_Utility.Logger;
import OCTS_HCM_Automation_TestCases.EmployeeAssignmentValidation;

public class WorkerValidation2 extends LaunchApplication {


	public  void workerValidation() {

	  System.setProperty("webdriver.ie.driver", "C:\\Automation_OCTS\\Driver\\IEDriverServer.exe");
	  WebDriver driver =new InternetExplorerDriver();

		//LaunchApplication Obj = new LaunchApplication();

				//	driver = Obj.getdriver();
					LaunchApplication.Invokeapplication(driver);
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					//PersonNumbers2 = ReadPersonNumbers();

					// TODO Auto-generated method stub

				ValidateWorker(driver);

				}

private static void ValidateWorker(WebDriver driver) {
	// TODO Auto-generated method stub

	 String Pnumber = null;

	try {
		Pnumber = ReadLinenumbers1();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	//Click on the navigator Icon
    driver.manage().timeouts().implicitlyWait(300, TimeUnit.MILLISECONDS);

    driver.findElement(By.id("pt1:_UIShome::icon")).click();

    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

	driver.findElement(By.id("pt1:_UISmmLink::icon")).click();

	//driver.findElement(By.linkText("Navigator")).click();

	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

   // driver.findElement(By.linkText("Show More")).click();

    //click on Person Management Link
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	driver.findElement(By.linkText("My Client Groups")).click();
	//driver.findElement(By.id("pt1:nv_itemNode_workforce_management_person_management")).click();

	driver.findElement(By.linkText("Person Management")).click();
	//driver.findElement(By.id("pt1:nv_itemNode_workforce_management_person_management")).click();

	try {
		Thread.sleep(200);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


		try {
			Thread.sleep(200);
			//click on the search
			driver.findElement(By.id("_FOpt1:_FOr1:0:_FONSr2:0:MAt1:0:pt1:Perso1:0:SP3:q1:value10::content")).sendKeys(Pnumber);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Thread.sleep(200);
			driver.findElement(By.id("_FOpt1:_FOr1:0:_FONSr2:0:MAt1:0:pt1:Perso1:0:SP3:q1::search")).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement Table = driver.findElement(By.id("_FOpt1:_FOr1:0:_FONSr2:0:MAt1:0:pt1:Perso1:0:SP3:table1:_ATp:table2::db"));

		//List  rows = wd.findElements(By.xpath(".//*[@id='leftcontainer']/table/tbody/tr/td[1]"));

		List <WebElement> Rows = driver.findElements(By.xpath(".//*[@id='_FOpt1:_FOr1:0:_FONSr2:0:MAt1:0:pt1:Perso1:0:SP3:table1:_ATp:table2::db']/table/tbody/tr/td[1]"));


		if (Rows.isEmpty() != false)
		{

	    Common_Utility.ReporterBaseTest.test.log(Status.PASS, "Employee with Person Number #"+ Pnumber + " has been created ");
			}

		Logger.logError(EmployeeAssignmentValidation.class, "Employee with #"+ Pnumber + " has not been created \n");
			}





public static String ReadLinenumbers1() throws IOException

{
	 //String[] Numbers = null ;

	 //String[] PersonNumbers = null;
	 String  temp = null;


	Stream<String> Rows = Files.lines(new File("C:\\Automation_OCTS\\Data\\Worker.dat").toPath());

    int lines = (int) (Rows.count());

	BufferedReader in = new BufferedReader (new FileReader("C:\\Automation_OCTS\\Data\\Worker.dat"));

    String info = "";

    int startLine = 1;

    int endLine = lines ;


    // read prior junk

    for (int i = startLine; i < endLine; i++) { info = in.readLine(); }

    for (int i = startLine; i < endLine-1 ; i++) {

        info = in.readLine();
        String arrsplit  = java.util.Arrays.toString(info.split("\\|"));


        String [] PN = arrsplit.split(",");

       // System.out.println("i=" + i);

        temp = PN[PN.length-1].replaceAll("]","").trim();

        System.out.println(temp);

         //PersonNumbers[i-1] = PN[PN.length-1].replaceAll("]","").trim();

    }
    in.close();

	return temp;
}

}

