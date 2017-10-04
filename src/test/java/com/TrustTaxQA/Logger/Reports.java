package com.TrustTaxQA.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.lang.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardOpenOption;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.apache.xpath.operations.String;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.gargoylesoftware.htmlunit.WebConsole.Logger;
import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Reports {

	public Reports() {
		// TODO Auto-generated constructor stub
	}

	private java.lang.String ReportFolder;
	private java.lang.String HTMLReporter;
	private ExtentReports extentObj;
	private File strSceenShotFolder;
	private ExtentTest extentTest;
	private java.lang.String logFilePath;
	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger("Logger");
	private java.lang.String resultFolder;

	// PropertyConfigurator.configure("Log4j.properties");
	public void StartReport() throws Exception {
		try {
			// creating log4j
			PropertyConfigurator.configure("Log4j.properties");
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss a");
			Date date = new Date();
			java.lang.String startReportTimeStamp = dateFormat.format(date).replace(' ', '_').replace(':', '-');
			ReportFolder = System.getProperty("user.dir") + "\\Reports\\";
			File files = new File(ReportFolder);
			// Creating Report foldr directory
			if (files.mkdirs()) {
				logger.info("Report Folder directory is created!");
			}
			// Appending HTML Report inside the Reports folder directory
			HTMLReporter = ReportFolder + "Summary" + "_" + startReportTimeStamp + ".HTML";
			extentObj = new ExtentReports(HTMLReporter, true);
			// creating screenshots folder inside a Report directory
			strSceenShotFolder = new File(ReportFolder + "\\ScreenShots\\");
			if (strSceenShotFolder.mkdirs()) {

				logger.info("Screenshot Folder directory is created!");
			}
			// Create ResultFolder
			resultFolder = System.getProperty("user.dir") + "\\Results\\";
			new File(resultFolder);
		} catch (Exception e) {
			logger.info("Exception is occured at StartReport method" + "->" + " " + e.getMessage());

			throw new Exception(e.getMessage());
		}

	}

	// This cod is for to start the each testcse
	public void startTestCase(java.lang.String testName) throws Exception {
		try {
			extentTest = extentObj.startTest(testName);
			// throw new Exception("throwing new excption");
		} catch (Exception e) {
			logger.info("Exception is occured at startTestCase method" + "->" + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	// This ode is for to log th extent reports
	public void ReportLogs(WebDriver driver, LogStatus status, java.lang.String stepName,
			java.lang.String stepDescription, Boolean screenShotRequired) throws Exception {

		try {

			// Writting content into HTML report

			if (status.equals(LogStatus.FAIL)) {
				extentTest.log(status, stepName, stepDescription);

				throw new Exception("Test case is failed");
			}
			if (status.equals(LogStatus.ERROR)) {
				extentTest.log(status, stepName, stepDescription);
				TakeScreenshot(driver, status, stepName);
				// throw new Exception("Thi test is failed");
			}
			if (!status.equals(LogStatus.ERROR)) {

				extentTest.log(status, stepName, stepDescription);
			}

			// Based on screenshot description,scrren shot will saved
			if (screenShotRequired) {
				TakeScreenshot(driver, status, stepName);
			}

		} catch (Exception e) {
			logger.info("Exception is occured at ReportLogs method" + "->" + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	// This code is for to end the each test case
	public void EndTestCase() throws Exception {
		try {
			extentObj.endTest(extentTest);
		} catch (Exception e) {
			logger.info("Exception is occured at EndTestCase method" + "->" + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	// This code is for to end the suite
	public void EndReport() throws Exception {
		// Flush the html rep[orts
		try {
			extentObj.flush();
			// Close the Extend session
			extentObj.close();
		} catch (Exception e) {
			logger.info("Exception is occured at EndReport method" + "->" + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	// This code is for to take the screenshot if required
	public void TakeScreenshot(WebDriver driver, LogStatus status, java.lang.String stepName) throws Exception {
		try {
			// Capturing screenshot time stamp
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss a");
			Date date = new Date();
			java.lang.String screenShotTimeStamp = dateFormat.format(date).replace(' ', '_').replace(':', '-');
			// Capturing screenshot
			File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			java.lang.String dest = strSceenShotFolder + "\\" + stepName + "_" + screenShotTimeStamp + ".png";
			File destination = new File(dest);
			FileUtils.copyFile(scr, destination);
			// Attaching scren shot in Html Report
			extentTest.log(status, "Snapshot below: " + extentTest.addScreenCapture(dest));
		} catch (Exception e) {
			logger.info("Exception is occured at TakeScreenshot method" + "->" + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public void logger(java.lang.String message) throws Exception {
		try {

			logger.info(message);
		} catch (Exception e) {
			logger.info("Exception is occured at logger method" + "->" + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public void copyFile() throws Exception {
		try {
			File scr = new File(HTMLReporter);
			File dest = new File("./Results/JenkinsEmail.HTML");

			FileUtils.copyFile(scr, dest);

		} catch (IOException e) {
			logger.info("Exception is occured at copyFile" + "->" + " " + e.getMessage());

			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}

	}
}
