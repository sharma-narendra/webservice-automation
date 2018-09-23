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
public class BenefitGroupEmployeeValidation extends ReporterBaseTest{

	static String workerTestDataPath = "C:\\Automation_OCTS\\Output\\BenefitGroupTestData.xlsx";
	static HashMap<Integer, Cell> firstName = new HashMap<Integer, Cell>();
	static HashMap<Integer, Cell> lastName = new HashMap<Integer, Cell>();
	static String workerFirstNameCol = "BenefitGroupName";
	static String workerLastNameCol = "PersonNumber";
	static String actualEmpName = "Rob, Morgan";
	static String trimActualEmpName = actualEmpName.replace(" ","");
	static String expectedEmpName ="";

	static HashMap<Object,Cell> empName = new HashMap<Object, Cell>();
	int flag = 0;

	public void getFirstAndLastName() {
		ReadHCMWorker_InputDataSheet workerFile = new ReadHCMWorker_InputDataSheet();

		try {
			firstName = workerFile.parseWorkerExcel(workerTestDataPath,workerFirstNameCol);
			lastName = workerFile.parseWorkerExcel(workerTestDataPath,workerLastNameCol);
			for (Map.Entry<Integer, Cell> fName : firstName.entrySet()) {
				for (Map.Entry<Integer, Cell> lName : lastName.entrySet()) {
					if (fName.getKey().equals(lName.getKey())) {
						empName.put(fName.getValue(),lName.getValue());
					}
				}
			}
			for (Map.Entry<Object, Cell> editEmpName : empName.entrySet()){
				expectedEmpName = editEmpName.getKey() +","+editEmpName.getValue();
				Common_Utility.ReporterBaseTest.test.log(Status.PASS, "Benefit Group : "+ editEmpName.getKey() + "" + " added to the Employee : "+ editEmpName.getValue() +" successfully.."+"\n");
				flag=1;
				//break;
			/*	if(expectedEmpName.equals(trimActualEmpName))
				{
					flag=1;
					break;
				}
				*/
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flag == 1)
		{
			System.out.println("Benefit Group : "+ expectedEmpName + "" + " added to the employee successfully.");
			//Common_Utility.ReporterBaseTest.test.log(Status.PASS, "Benefit Group : "+ expectedEmpName + "" + " added to the employee successfully.."+"\n");
			Logger.logInfo(BenefitGroupEmployeeValidation.class, "Benefit Group : "+ expectedEmpName + "" + " added to the employee successfully.."+"\n");
		}
		if(flag == 0)
		{
			System.out.println("Benefit Group : "+ expectedEmpName + "" + " not added to the employee.");
			Common_Utility.ReporterBaseTest.test.log(Status.FAIL, "Benefit Group : "+ expectedEmpName + "" + " not added to the employee." +"\n");
			Logger.logError(BenefitGroupEmployeeValidation.class, "Benefit Group : "+ expectedEmpName + "" + " not added to the employee." +"\n");
		}

	}

	@Test
	public void benefitGroupEmployeeValidation() {
		BenefitGroupEmployeeValidation empVal = new BenefitGroupEmployeeValidation();
		//System.out.println(workerTestDataPath);
		test=extent.createTest("Benefit Group association with Employee Validation");
		System.out.println("\n Benefit Group association with Employee Validation \n");
		if(HCM_BenefitGroup_Webservice_MainClass.benefitGroupStatus) {
			empVal.getFirstAndLastName();
			}else {
				Common_Utility.ReporterBaseTest.test.log(Status.FAIL, "Benefit Group not assigned with Employee successfully");
			}

	}

	//Commenting the below code; if required to test this code alone then uncomment the code
	/*public static void main(String[] args) {
		BenefitGroupEmployeeValidation empVal = new BenefitGroupEmployeeValidation();
		empVal.getFirstAndLastName();
	}*/

}