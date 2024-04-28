package api.utilities;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Reporter;

public class ExcelReader extends ExcelSupport 
{
	public final static int testNameColumn =1;
	
	public final static int testDataStartColumn=2;

	public ExcelReader(String filePath) {
		super(filePath);
	}
	
	public Object[][] getTestCaseDataSets(String sheetName, String testName)
	{
		int testRowNumber = getRowNumber(sheetName, testNameColumn, testName);
		int testDataStartRow = testRowNumber +1;
		
		int testDataRows = 0;
		for(int i=testDataStartRow; getCellData(sheetName, testNameColumn, i).equals(testName); i++ )
		{
			testDataRows++;
		}
		
		int testDataCols = getCellCount(sheetName, testRowNumber) - testDataStartColumn+1;
		
		Object[][] testCaseDataSets = new Object[testDataRows][testDataCols];
		
			for(int i=0;i<testDataRows;i++)
		{
			for(int j=0;j<testDataCols;j++)
			{
				testCaseDataSets[i][j] = getCellData(sheetName, testDataStartColumn+j, testDataStartRow+i);
			}
		}
		
		return testCaseDataSets;
		
	}
	
	public void setdatainExcelCells(String sheetName, ArrayList<String> languages){
		
		int rows =  getRowCount(sheetName)+1;
		for(int i =1; i <= languages.size(); i++)
		setCellData(sheetName, i, rows, languages.get(i-1));
		
	}

	public Object[][] getTestCaseDataSetsAsHashMap(String sheetName, String testName) {
		int testRowNumber = getRowNumber(sheetName, testNameColumn, testName);
		int testDataStartRow = testRowNumber; //+ 1;

		int testDataRows = 0;
		for (int i = testDataStartRow; getCellData(sheetName, testNameColumn, i).equals(testName); i++) {
			testDataRows++;
		}

		int testDataCols = getCellCount(sheetName, testRowNumber) - testDataStartColumn + 1;

		List<Map<String, String>> data = new ArrayList<Map<String, String>>();

		for (int i = 1; i < testDataRows; i++) {
			Map<String, String> rowData = new HashMap<String, String>();
			for (int j = 0; j < testDataCols; j++) {

				String headers = getCellData(sheetName, testDataStartColumn + j, testDataStartRow);
				String cellValue = getCellData(sheetName, testDataStartColumn + j, testDataStartRow + i);
				rowData.put(headers, cellValue);
			}
			data.add(rowData);
			System.out.println("rowData"+rowData);
			//System.out.println("size"+rowData.size());
		}
		
		

		Object[][] testData = new Object[data.size()][1];
		for(int i=0; i<data.size(); i++) {
			testData[i][0] = data.get(i);
		}
		return testData;
		//return data;

	}

	
	

}
