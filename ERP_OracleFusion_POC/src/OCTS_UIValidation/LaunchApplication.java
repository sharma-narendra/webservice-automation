package OCTS_UIValidation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class LaunchApplication {

	public static String URL;
	public static String Username;
	public static String Pwd;

	 public static WebDriver driver;

	  public LaunchApplication(){

	   // driver = new ChromeDriver();
		//  System.setProperty("webdriver.ie.driver", "C:\\Automation_OCTS\\Driver\\IEDriverServer.exe");
		//  driver =new InternetExplorerDriver();
	  }
	  public WebDriver getdriver(){
	 	      return driver;
	    }

	public static void Invokeapplication(WebDriver driver)

	{
		ExcelUtils dd;
		try {
			dd = new ExcelUtils ("C:\\Automation_OCTS\\Data\\LoginDetails.xlsx","LaunchApplication");
			URL = dd.getCellDataasstring(1, 0);
			Username = dd.getCellDataasstring(1, 1);
			Pwd = dd.getCellDataasstring(1, 2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     //System.setProperty("webdriver.chrome.driver","C:\\Users\\445762\\Downloads\\jar_files\\selenium-chrome-driver-2.35.0.jar");


		//try {

			// System.setProperty("webdriver.chrome.driver","D:\\ERP_OracleFusion_POC\\chromedriver.exe");

			//Thread.sleep(300);
		//} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.navigate().to(URL);
        System.out.println( driver.getCurrentUrl());
        driver.manage().window().maximize();
        driver.findElement(By.id("userid")).sendKeys(Username);
        driver.findElement(By.id("password")).sendKeys(Pwd);
        driver.findElement(By.id("btnActive")).click();
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//	Invokeapplication();


			   }



	}

