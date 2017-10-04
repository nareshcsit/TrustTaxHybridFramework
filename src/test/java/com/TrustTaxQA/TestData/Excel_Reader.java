package com.TrustTaxQA.TestData;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.TrustTaxQA.BaseClass;

public class Excel_Reader {

	public XSSFWorkbook wb = null;
//	public FileInputStream fis = null;
	public XSSFSheet sh;
	String dataSets[][] = null;
	public Excel_Reader(XSSFWorkbook wb )
	{
		this.wb=wb;
	}
	public String[][] readExcleData(String excelSheetName) throws Exception {
		try{	
		sh = wb.getSheet(excelSheetName.trim());
		// find the total no of rows
		int usedRows = sh.getLastRowNum() + 1;
		int totalColumn = sh.getRow(0).getLastCellNum();
		// Create array of rows and columns
		dataSets = new String[usedRows][totalColumn];
		// Checking the TC-ID RowNo//Assume TC_Id always in First column
		for (int i = 0; i < usedRows; i++) {
			for (int j = 0; j < sh.getRow(i).getLastCellNum(); j++) {
				System.out.println(sh.getRow(i).getCell(j).getStringCellValue());
				dataSets[i][j] = sh.getRow(i).getCell(j).getStringCellValue();
			}
		}
			
		//return dataSets;
	}catch(Exception e)
		{
		throw new Exception(e.getMessage());
		}
		finally{
			sh=null;
			
		}
		return dataSets;
	
		
	
	 
}
	
}

