package OCTS_USECASE_Employee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Common_Utility.*;
import OCTS_Automation_Main_Modules.*;



@SuppressWarnings("unused")
public class TimeRecordGroupEmployeeValidation extends ReporterBaseTest{

	static String TestDataPath = "C:\\Automation_OCTS\\Output\\TimeRecordGroupTestData.xlsx";

	int flag = 0;
	String Param1, Param2,Param3,Param4,Param5, Param6;

	public void getFirstAndLastName() {


		try {
		Xls_Reader objReader= new Xls_Reader(TestDataPath);
		 Param1 = objReader.getCellData("Header", "PersonNumber",2);
		 Param2 =objReader.getCellData("Header", "AssignmentNumber",2);
		 Param3 = objReader.getCellData("Header", "TcStartTime",2);
		 Param4 = objReader.getCellData("Header", "TcStopTime",2);
	//	 Param5 =objReader.getCellData("Header", "AssignmentNumber",2);
		// Param6 = objReader.getCellData("Header", "PayrollId",2);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!Param2.equals(""))
		{
			//System.out.println("Assigned Payroll : "+ expectedEmpName + "" + " added to the employee successfully.");
			Common_Utility.ReporterBaseTest.test.log(Status.PASS, "Time Record Group : for the period Start Time "+Param3+" - Stop Time "+Param4+"   have been updated  to the Employee: "+Param1+" Assignment: "+Param2+" successfully.."+"\n");
			//Logger.logInfo(AssignedPayrollEmployeeValidation.class, "Assigned Payroll : "+ expectedEmpName + "" + " added to the employee successfully.."+"\n");
		}else

		{
		//	System.out.println("Assigned Payroll : "+ expectedEmpName + "" + " not added to the employee.");
			//Common_Utility.ReporterBaseTest.test.log(Status.FAIL, "Assigned Payroll : "+ expectedEmpName + "" + " not added to the employee." +"\n");
		//	Logger.logError(AssignedPayrollEmployeeValidation.class, "Assigned Payroll : "+ expectedEmpName + "" + " not added to the employee." +"\n");

			Common_Utility.ReporterBaseTest.test.log(Status.FAIL, "Data not available in the datasheet");

		}

	}

	@Test
	public void timeRecordGroupEmployeeValidation() {
		TimeRecordGroupEmployeeValidation empVal = new TimeRecordGroupEmployeeValidation();
		//System.out.println(workerTestDataPath);
		test=extent.createTest("Time Record Group association with Employee Validation");
		System.out.println("\n Time Record Group association with Employee Validation \n");


		if(HCM_TimeRecordGroupWebservice_MainClass.timeRecordStatus) {
			empVal.getFirstAndLastName();
			}else {
				Common_Utility.ReporterBaseTest.test.log(Status.FAIL, "Time Record Group not assigned with Employee successfully");
			}

	}

	//Commenting the below code; if required to test this code alone then uncomment the code
/*	public static void main(String[] args) {
		PersonDisabilityEmployeeValidation empVal = new PersonDisabilityEmployeeValidation();
		empVal.getFirstAndLastName();
	}
*/



}