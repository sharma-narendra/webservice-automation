package Common_Utility;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import OCTS_UI.FXMLController;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class TextToExcel {

	public static void main(String[] args) throws IOException

	{
		TextToExcel rd = new TextToExcel();
		rd.readTextFile(null);
	}

	@SuppressWarnings("unused")
	public String readTextFile(String File1) {
		String Filepath = File1;// provide your text file path here
		String Line = null;
		String FileNameOutputTestData="";
		FileOutputStream rfile = null;
		XSSFWorkbook rworkbook = null;
		XSSFSheet rworksheet = null;
		//FXMLController fxmlControlObj=new FXMLController();
		if(FXMLController.flowBU.equals("HCM_Worker")) {
			FileNameOutputTestData="C:\\Automation_OCTS\\Output\\WorkerTestData.xlsx";
		//rfile = new FileOutputStream("C:\\Automation_OCTS\\Output\\WorkerTestData.xlsx");// provide your result file
																						// path here,where text file
																						// split data need to be
		}else if(FXMLController.flowBU.equals("HCM_BenefitGroup_Employee")) {
			FileNameOutputTestData="C:\\Automation_OCTS\\Output\\BenefitGroupTestData.xlsx";
		//	rfile = new FileOutputStream("C:\\Automation_OCTS\\Output\\BenefitGroupTestData.xlsx");
		}else if(FXMLController.flowBU.equals("HCM_ScheduleAssignment")){
			FileNameOutputTestData="C:\\Automation_OCTS\\Output\\ScheduleAssignment.xlsx";

		}else if(FXMLController.flowBU.equals("HCM_Salary")){
			FileNameOutputTestData="C:\\Automation_OCTS\\Output\\Salary.xlsx";

		}

		/*else if(FXMLController.flowBU.equals("HCM_PersonDisability")) {
			FileNameOutputTestData="C:\\Automation_OCTS\\Output\\PersonDisabilityTestData.xlsx";
		}*/


		try {
			BufferedReader txtreader = new BufferedReader(new FileReader(Filepath));
			BufferedReader txtreader1 = new BufferedReader(new FileReader(Filepath));




			rfile = new FileOutputStream(FileNameOutputTestData);

			// stored
			rworkbook = new XSSFWorkbook();
			rworksheet = rworkbook.createSheet("Header");
			String delimiter = "\\|";
			int noOfRecords = 0;
			int k = 0;
			int j = 0;
			int temp = 0;

			outerloop: while ((Line = txtreader.readLine()) != null) {

				String[] splitWords = null;
				splitWords = Line.split(delimiter);

				for (int i = 0; i < splitWords.length; i++)
					if (splitWords[i].equals("METADATA") && noOfRecords != 0) {

						break outerloop;
					}
				XSSFRow rowdata = rworksheet.createRow(noOfRecords++);
				System.out.println(noOfRecords);
			}
			txtreader.close();

			while ((Line = txtreader1.readLine()) != null) {
				String[] splitWords = null;

				splitWords = Line.split(delimiter);
				if (k % noOfRecords == 0) {
					k = 0;
					temp = temp + splitWords.length;
				}

				for (int i = 0; i < splitWords.length; i++) {
					j = i + temp - splitWords.length;
					System.out.print(splitWords[i]);
					// row = rworksheet.getRow(k);
					XSSFCell cell = rworksheet.getRow(k).createCell(j);
					cell.setCellValue(splitWords[i]);

				}

				k++;
			}
			rworkbook.write(rfile);
			rfile.close();
			txtreader1.close();
		} catch (IOException e) {
			return "Error: " + e;
		}
		// txtreader1.close();
		// rfile.flush();
		return "Excel File Created Succesfully from Input Test Data (.dat) file";
	}

}
