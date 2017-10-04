// package com.TrustTaxQA.TestData;
//
// import java.io.Closeable;
// import java.io.FileInputStream;
// import java.io.FileReader;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.Properties;
//
// import org.apache.poi.xssf.usermodel.XSSFSheet;
// import org.apache.poi.xssf.usermodel.XSSFWorkbook;
// import org.json.simple.JSONArray;
// import org.json.simple.JSONObject;
// import org.json.simple.parser.JSONParser;
//
// import com.TrustTaxQA.BaseClass;
// import com.relevantcodes.extentreports.LogStatus;
//
// public class ReadExcelData{
// Properties properties;
//// public ReadExcelData(Properties properties)
//// {
//// this.properties=properties;
//// }
//
//
// public void returnExcelData() throws Exception
// {
// XSSFWorkbook wb = null;
// String content = null;
// try {
//
// HashMap<String,HashMap<String,String>> first=new
// HashMap<String,HashMap<String,String>>();
// HashMap<String,String> second = null;
//
// FileInputStream fis = null;
// // System.out.println(properties.getProperty("TestDataFilePath"));
// //System.out.println(properties.getProperty("TestDataSheetName"));
// boolean TC_IDFound = false;
// boolean columnHeaderFound = false;
// int foundRowNo = 0;
// int foundColumnN0 = 0;
//
// try {
// fis = new
// FileInputStream("C:\\Eclipse\\com.TrustTaxQA\\com.TrustTaxQA\\TestData\\AttendeeAdminUpdated.xlsx");
// } catch (Exception e) {
// // TODO Auto-generated catch block
// throw new Exception(e.getMessage());
// }
// try {
// wb = new XSSFWorkbook(fis);
// } catch (Exception e) {
// // TODO Auto-generated catch block
// throw new Exception(e.getMessage());
// }
// // XSSFSheet sh = wb.getSheet("General_Data");
// XSSFSheet sh = wb.getSheet("SmokeSheet");
// // find the total no of rows
// int usedRows = sh.getLastRowNum() + 1;
// // Checking the TC-ID RowNo//Assume TC_Id always in First column
// for (int i = 1; i < usedRows; i++) {
//
// second=new HashMap<String, String>();
//
// for(int j=0;j<sh.getRow(i).getLastCellNum();j++)
// {
// second.put(sh.getRow(0).getCell(j).getStringCellValue(),sh.getRow(i).getCell(j).getStringCellValue());
// }
//
//
// first.put(second.get("TC_ID"),second);
// }
//
// HashMap<String,String> req=first.get("TC-2");
// System.out.println(req.get("UserName"));
// System.out.println(req.get("PassWord"));
// }
//
// catch(Exception e)
// {
// throw new Exception(e.getMessage());
// }
// finally {
// ((Closeable) wb).close();
//
// }
//
// }}
//
////
////
////
//// }
