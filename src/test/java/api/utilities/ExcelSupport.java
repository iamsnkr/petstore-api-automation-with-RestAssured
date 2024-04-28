package api.utilities;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Reporter;

public class ExcelSupport {
	
	private String filePath; 
	private FileInputStream fis; 
	private Workbook workbook; 
	
	public ExcelSupport(String filePath)
	{			
		this.filePath = filePath;
		try 
		{
			fis = new FileInputStream(filePath);
			ZipSecureFile.setMinInflateRatio(-5.0d); 
			workbook = WorkbookFactory.create(fis);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	
	public String getFilePath()
	{
		return filePath;
	}
	
	public int getRowCount(String sheetName)
	{
		try
		{
			return workbook.getSheet(sheetName).getPhysicalNumberOfRows();
		}
		catch(NullPointerException e)
		{
			return 0;
		}
	}
	
	public int getCellCount(String sheetName, int rowNumber)
	{
		try
		{
		return workbook.getSheet(sheetName).getRow(rowNumber-1).getPhysicalNumberOfCells();
		}
		catch(NullPointerException e)
		{
			return 0;
		}
	}
	
	public String getCellData(String sheetName, int columnNumber, int rowNumber)
	{
		try
		{
	        DataFormatter dataFormatter = new DataFormatter();
	        return dataFormatter.formatCellValue(workbook.getSheet(sheetName).getRow(rowNumber - 1).getCell(columnNumber - 1));
		}
		catch(NullPointerException e)
		{
			return "";
		}
	}
	
	public void setCellData(String sheetName, int columnNumber, int rowNumber, String cellValue)
	{
		Cell cell;
		Row row;
		try
		{
		cell = workbook.getSheet(sheetName).getRow(rowNumber-1).createCell(columnNumber-1);
		}
		catch(NullPointerException e)
		{
			row = workbook.getSheet(sheetName).createRow(rowNumber-1);
			cell = row.createCell(columnNumber-1);
		}
		
		cell.setCellValue(cellValue);
		try 
		{
		FileOutputStream fso = new FileOutputStream(filePath);
		workbook.write(fso);
		fso.close();
		}
		catch(FileNotFoundException e){
			Reporter.log("<b>Created test data is not saved</b><br>");	
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public int getRowNumber(String sheetName, int columnNumber, String cellValue)
	{
		Iterator<Row> itr = workbook.getSheet(sheetName).rowIterator();
		while(itr.hasNext())
		{
			Row row =itr.next();
			try{
			if (row.getCell(columnNumber-1).toString().equals(cellValue))
			{
				return (row.getRowNum()+1);
			}
			}
			catch(NullPointerException e)
			{
				continue;
			}
		}
			
		return -1;
	}
	
	public int getColumnNumber(String sheetName, int rowNumber, String cellValue)
	{
		try
		{
		Row row = workbook.getSheet(sheetName).getRow(rowNumber-1);
		
		Iterator<Cell> itr = row.cellIterator();
		while(itr.hasNext())
		{
			Cell cell =itr.next();
			if (cell.toString().equals(cellValue))
			{
				return (cell.getColumnIndex()+1);
			}
			
		}
		return -1;
		}
		catch(NullPointerException e)
		{
			return -1;
		}
	}
	
	
	

	
}
