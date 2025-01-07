package com_SCM_utilities_file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com_SCM_utilities_database.DatabaseHelper;

public class ExcelHelper {
	 // Logger for logging errors or information
		private static final Logger logger = LogManager.getLogger(DatabaseHelper.class);
		private static Workbook wb=null;
		
		/**
		 * Reads data from excel sheet 
		 * @param excelPath path of the excel
		 * @param sheetName  give the sheet name
		 * @param rowNo   To fetch the data from particular row Number
		 * @param cellNo  To fetch the data from particular cell Number
		 * @return it will return the data based on the given sheetName , row and cell number.
		 * @throws Exception
		 * @throws IOException
		 */
		public String getDataFromExcelSheet(String excelPath,String sheetName,int rowNo,int cellNo) throws Exception, IOException
		{
			String data=null;
			try (FileInputStream fis=new FileInputStream(excelPath)){
				 wb = WorkbookFactory.create(fis);
				 data=wb.getSheet(sheetName).getRow(rowNo).getCell(cellNo).getStringCellValue();
				
			}
			catch (Exception e) {
				logger.error("Error reading data from excel",e);
			}
			return data;
		}
		/**
		 * Writing the data to excel
		 * @param excelPath
		 * @param sheetName
		 * @param data
		 * @throws Exception
		 */
		public void writeBackDataToExcel(String excelPath,String sheetName,ArrayList<String[]> data) throws Exception
		{
			try(FileInputStream fis=new FileInputStream(excelPath)) {
				wb=WorkbookFactory.create(fis);
				  Sheet sheet = wb.getSheet(sheetName);
				   int rowNo=0;
				   for(String [] rowData:data)
				   {
					      Row row = sheet.createRow(rowNo++);
					      int cellNo=0;
					      for(String cellData : rowData)
					      {
					    	      Cell cell = row.createCell(cellNo++);
					    	      cell.setCellValue(cellData);
					      }
				   }
				   try(FileOutputStream fos=new FileOutputStream(excelPath))
				   {
					   wb.write(fos);
				   }
			}
			catch (Exception e) {
				logger.error("Error writing the data to excel", e);
			}
		}
		/**
		 * Fetches the row count from excel sheet
		 * @param excelpath
		 * @param sheetName
		 * @return
		 * @throws Exception
		 */
		public int getRowCountFromExcel(String excelpath, String sheetName) throws Exception
		{
			int rowCount=0;
			try(FileInputStream fis=new FileInputStream(excelpath))
			{
				wb=WorkbookFactory.create(fis);
				rowCount=wb.getSheet(sheetName).getLastRowNum();
			}
			catch (Exception e) {
				logger.error("Error getting the rowcount from excel",e);
			}
			return rowCount;
		}
		/**
		 * Closes the Excel workbook.
		 */
		public void closeExcel()
		{
			try {
				if(wb!=null)
				{
					wb.close();
				}
			}
			catch (IOException e) {
				logger.error("Error closing the excel file",e);
			}
		}
}
