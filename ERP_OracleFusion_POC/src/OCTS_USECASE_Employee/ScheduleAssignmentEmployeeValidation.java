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
public class ScheduleAssignmentEmployeeValidation extends ReporterBaseTest{

	static String TestDataPath = "C:\\Automation_OCTS\\Output\\ScheduleAssignmentTestData.xlsx";

	int flag = 0;
	String Param1, Param2,Param3,Param4,Param5, Param6;

	public void getFirstAndLastName() {


		try {
		Xls_Reader objReader= new Xls_Reader(TestDataPath);
		 Param1 = objReader.getCellData("Header", "StartDate",2);
		 Param2 =objReader.getCellData("Header", "EndDate",2);
		// Param3 = objReader.getCellData("Header", "AssignedPayrollId",2);
		 Param4 = objReader.getCellData("Header", "ScheduleName",2);
		 Param5 =objReader.getCellData("Header", "AssignmentNumber",2);
		 Param6 = objReader.getCellData("Header", "ScheduleAssignmentId",2);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!Param6.equals(""))
		{
			//System.out.println("Assigned Payroll : "+ expectedEmpName + "" + " added to the employee successfully.");
			Common_Utility.ReporterBaseTest.test.log(Status.PASS, "ScheduleName : "+Param4+", with ScheduleAssignmentId : "+Param6+" with Start Date: "+Param1+" and End Date : "+Param2+" have been assigned  to the employee assignment "+Param5+" successfully.."+"\n");
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
	public void scheduleAssignmentEmployeeValidation() {
		ScheduleAssignmentEmployeeValidation empVal = new ScheduleAssignmentEmployeeValidation();
	//	System.out.println(workerTestDataPath);
		test=extent.createTest("Schedule Assignment association with Employee Validation");
		System.out.println("\n Schedule Assignment association with Employee Validation \n");
		if(HCM_ScheduleAssignment_Webservice_MainClass.schAssignmentStatus) {
			empVal.getFirstAndLastName();
			}else {
				Common_Utility.ReporterBaseTest.test.log(Status.FAIL, "Schedule Assignment not assigned with Employee successfully");
			}
	}

	//Commenting the below code; if required to test this code alone then uncomment the code
/*	public static void main(String[] args) {
		PersonDisabilityEmployeeValidation empVal = new PersonDisabilityEmployeeValidation();
		empVal.getFirstAndLastName();
	}
*/



}