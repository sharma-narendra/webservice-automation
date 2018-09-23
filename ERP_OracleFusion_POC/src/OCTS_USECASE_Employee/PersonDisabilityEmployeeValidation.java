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
public class PersonDisabilityEmployeeValidation extends ReporterBaseTest{

	static String workerTestDataPath = "C:\\Automation_OCTS\\Output\\PersonDisabilityTestData.xlsx";
	static String personDisabilityDatTestDataPath ="C:\\Automation_OCTS\\Data\\PersonDisability.dat";
	static HashMap<Integer, Cell> firstName = new HashMap<Integer, Cell>();
	static HashMap<Integer, Cell> lastName = new HashMap<Integer, Cell>();
	static String workerFirstNameCol = "DisabilityCode";
	static String workerLastNameCol = "PersonNumber";
	static String actualEmpName = "Rob, Morgan";
	static String trimActualEmpName = actualEmpName.replace(" ","");
	static String expectedEmpName ="";
	String txt2excel;

	static HashMap<Object,Cell> empName = new HashMap<Object, Cell>();
	int flag = 0;

	public void getFirstAndLastName() {
	//	TextToExcel tte = new TextToExcel();
	//	txt2excel = tte.readTextFile(personDisabilityDatTestDataPath);


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
				Common_Utility.ReporterBaseTest.test.log(Status.PASS, "Disability  : "+ editEmpName.getKey() + "" + "  added to the Employee : "+ editEmpName.getValue() +" successfully.."+"\n");
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
			System.out.println("Disability : "+ expectedEmpName + "" + " added to the employee successfully.");
			//Common_Utility.ReporterBaseTest.test.log(Status.PASS, "Benefit Group : "+ expectedEmpName + "" + " added to the employee successfully.."+"\n");
			Logger.logInfo(PersonDisabilityEmployeeValidation.class, "Disability : "+ expectedEmpName + "" + " added to the employee successfully.."+"\n");
		}
		if(flag == 0)
		{
			System.out.println("Disability : "+ expectedEmpName + "" + " not added to the employee.");
			Common_Utility.ReporterBaseTest.test.log(Status.FAIL, "Disability : "+ expectedEmpName + "" + " not added to the employee." +"\n");
			Logger.logError(PersonDisabilityEmployeeValidation.class, "Disability : "+ expectedEmpName + "" + " not added to the employee." +"\n");
		}

	}

	@Test
	public void personDisabilityEmployeeValidation() {
		PersonDisabilityEmployeeValidation empVal = new PersonDisabilityEmployeeValidation();
		System.out.println(workerTestDataPath);
		test=extent.createTest("Disability association with Employee Validation");
		System.out.println("\n Disability association with Employee Validation \n");
		if(HCM_PersonDisability_Webservice_MainClass.personDisabilityStatus) {
			empVal.getFirstAndLastName();
			}else {
				Common_Utility.ReporterBaseTest.test.log(Status.FAIL, "Disability not assigned to Employee successfully");
			}
		//empVal.getFirstAndLastName();
	}

	//Commenting the below code; if required to test this code alone then uncomment the code
/*	public static void main(String[] args) {
		PersonDisabilityEmployeeValidation empVal = new PersonDisabilityEmployeeValidation();
		empVal.getFirstAndLastName();
	}
*/



}