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
public class SalaryEmployeeValidation extends ReporterBaseTest{

	static String TestDataPath = "C:\\Automation_OCTS\\Output\\SalaryTestData.xlsx";

	int flag = 0;
	String Param1, Param2,Param3,Param4,Param5, Param6;

	public void getFirstAndLastName() {


		try {
		Xls_Reader objReader= new Xls_Reader(TestDataPath);
		 Param1 = objReader.getCellData("Header", "DateFrom",2);
		 Param2 =objReader.getCellData("Header", "DateTo",2);
		 Param3 = objReader.getCellData("Header", "SalaryAmount",2);
		// Param4 = objReader.getCellData("Header", "PayrollDefinitionCode",2);
		 Param5 =objReader.getCellData("Header", "AssignmentNumber",2);
		 Param6 = objReader.getCellData("Header", "SalaryId",2);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!Param5.equals(""))
		{
			//System.out.println("Assigned Payroll : "+ expectedEmpName + "" + " added to the employee successfully.");
			Common_Utility.ReporterBaseTest.test.log(Status.PASS, "SalaryId: "+Param6+", with Salary amount  : "+Param3+" for Start Date: "+Param1+" and End Date: "+Param2+"   have been assigned  to the employee assignment "+Param5+" successfully.."+"\n");
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
	public void salaryEmployeeValidation() {
		SalaryEmployeeValidation empVal = new SalaryEmployeeValidation();
		//System.out.println(workerTestDataPath);
		test=extent.createTest("Salary association with Employee Validation");
		System.out.println("\n Salary association with Employee Validation \n");
	//	empVal.getFirstAndLastName();
		if(HCM_Salary_Webservice_MainClass.salaryStatus) {
			empVal.getFirstAndLastName();
			}else {
				Common_Utility.ReporterBaseTest.test.log(Status.FAIL, "Salary not assigned to Employee successfully");
			}
	}

	//Commenting the below code; if required to test this code alone then uncomment the code
/*	public static void main(String[] args) {
		PersonDisabilityEmployeeValidation empVal = new PersonDisabilityEmployeeValidation();
		empVal.getFirstAndLastName();
	}
*/



}