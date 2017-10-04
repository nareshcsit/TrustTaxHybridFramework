package com.TrustTaxQA.TestData;

import java.io.FileReader;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.TrustTaxQA.BaseClass;
import com.TrustTaxQA.Config;
import com.TrustTaxQA.SmokeTest;
import com.TrustTaxQA.Logger.Reports;

public class ReadJson{

	private Config conf;
	private Reports ReportObj;
	public ReadJson(Config conf,Reports ReportObj) {
		// TODO Auto-generated constructor stub
		this.conf=conf;
		this.ReportObj=ReportObj;
	}
	 
	// Properties properties;
	public JSONObject returnJsonContent() throws Exception {

		JSONObject jsonObject;
		try {
			JSONParser parser = new JSONParser();
			// Object obj=parser.parse(new
			// FileReader("D:\\Users\\Shiridi\\Desktop\\TestData\\TestData.json"));
			//if (properties.getProperty("TestDataFilePath") == null) {
			if (conf.getJsonFilePath()== null) {
				ReportObj.logger("Json File value is passing as null,Please correct it !!");
				throw new Exception("Json File value is passing as null,Please correct it !!");
			}
			Object obj = parser.parse(new FileReader(conf.getJsonFilePath()));
			jsonObject = (JSONObject) obj;
			if (jsonObject == null) {
				ReportObj.logger("Json Object is returning null value,Please correct it !!");
				throw new Exception("Json Object is returning null value,Please correct it !!");
			}
		} catch (Exception e) {
			ReportObj.logger(
					"Exception is occured at ReadJson class,please correct it !!" + "->" + " " + e.getMessage());
			throw new Exception(e.getMessage());

			// throw new Exception("")
		}
		return jsonObject;

	}
}
