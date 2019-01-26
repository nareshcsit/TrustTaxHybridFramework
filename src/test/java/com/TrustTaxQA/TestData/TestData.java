package com.TrustTaxQA.TestData;

import java.io.FileReader;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.TrustTaxQA.BaseClass;
import com.TrustTaxQA.Config;

import com.TrustTaxQA.Logger.Reports;
//This class will return the 'JSON object' which will have complete JSON testData
public class TestData {
	// This method will return the JSON content inform of Object
	public static XSSFWorkbook wb = null;
	// public FileInputStream fis = null;
	public static  XSSFSheet sh;
	static String dataSets[][] = null;
	
	public  static JSONObject readTestData() throws Exception {
		JSONObject jsonContentObject;
		try {
			// Crating object for JSON Parser
			JSONParser parser = new JSONParser();
			if (Config.getJsonFilePath() == null) {
				throw new Exception("Json File value is passing as null,Please correct it !!");
			}
			// Json Parsing
			Object obj = parser.parse(new FileReader(Config.getJsonFilePath()));
			// Converting object to JSON object type
			jsonContentObject = (JSONObject) obj;
			if (jsonContentObject == null) {
				throw new Exception("Json Object is returning null value,Please correct it !!");
			}
		} catch (Exception e) {
			
			throw new Exception(e.getMessage());

		}
		return jsonContentObject;

	}
	
	public static String[][] readExcleData(XSSFWorkbook wb, String excelSheetName) throws Exception {
		try {
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

			// return dataSets;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			sh = null;

		}
		return dataSets;

	}
}
