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

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Reports {
	// this class is for Reporting
	public Reports() {
		// TODO Auto-generated constructor stub
	}

	private static java.lang.String ReportFolder;
	private static java.lang.String HTMLReporter;
	private static ExtentReports extentObj;
	private static File strSceenShotFolder;
	private java.lang.String logFilePath;
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger("Logger");
	private static java.lang.String resultFolder;

	/*
	 * 
	 * Start report method is for to create a Report folder structure like
	 * Screenshot Folder,Reports Folder,Results HTML Report
	 */
	public static void StartReport() throws Exception {
		try {
			// Loading Log4J configuration file
			PropertyConfigurator.configure("Log4j.properties");
			// Capturing time stamp to append in the Result HTML file
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss a");
			Date date = new Date();
			java.lang.String startReportTimeStamp = dateFormat.format(date).replace(' ', '_').replace(':', '-');
			// Creating Reports Folder only for first time
			ReportFolder = System.getProperty("user.dir") + "\\Reports\\";
			File files = new File(ReportFolder);
			if (files.mkdirs()) {
				logger.info("Reports Folder directory is created!");
			}
			// Appending HTML Report inside the Reports folder directory
			HTMLReporter = ReportFolder + "Summary" + "_" + startReportTimeStamp + ".HTML";
			extentObj = new ExtentReports(HTMLReporter, true);
			// creating screenshots folder inside a Reports directory
			strSceenShotFolder = new File(ReportFolder + "\\ScreenShots\\");
			if (strSceenShotFolder.mkdirs()) {

				logger.info("Screenshot Folder directory is created!");
			}
			
		} catch (Exception e) {
			logger.info("Exception is occured at StartReport method" + "->" + " " + e.getMessage());

			throw new Exception(e.getMessage());
		}

	}

	// This method is for to start the test case
	public static ExtentTest startTestCase(java.lang.String testName) throws Exception {
		ExtentTest extentTest;
		try {
			extentTest = extentObj.startTest(testName);
		} catch (Exception e) {
			logger.info("Exception is occured at startTestCase method" + "->" + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
		return extentTest;
	}

	// This code is for to log the extent reports(Possible options could be
	// PASS,FAIL,Error)
	public static void ReportLogs(ExtentTest extentTest, WebDriver driver, LogStatus status, java.lang.String stepName,
			java.lang.String stepDescription, Boolean screenShotRequired) throws Exception {
		try {
			if (status.equals(LogStatus.FAIL)) {
				extentTest.log(status, stepName, stepDescription);
				throw new Exception("Test case is failed");
			}
			if (status.equals(LogStatus.ERROR)) {
				extentTest.log(status, stepName, stepDescription);
				TakeScreenshot(extentTest, driver, status, stepName);
			}
			if (!status.equals(LogStatus.ERROR)) {
				extentTest.log(status, stepName, stepDescription);
			}
			if (screenShotRequired) {
				TakeScreenshot(extentTest, driver, status, stepName);
			}

		} catch (Exception e) {
			logger.info("Exception is occured at ReportLogs method" + "->" + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	// This code is for to end the each test
	// Note: Every test case should have StartTest() and EndTest() methods
	public static void EndTestCase(ExtentTest extentTest) throws Exception {
		try {
			extentObj.endTest(extentTest);
		} catch (Exception e) {
			logger.info("Exception is occured at EndTestCase method" + "->" + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	// This code is for to end the suite(Flush the content into html reports)
	public static void EndReport() throws Exception {

		try {
			extentObj.flush();
			extentObj.close();
		} catch (Exception e) {
			logger.info("Exception is occured at EndReport method" + "->" + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	// This code is for to take the screenshot if required
	public static void TakeScreenshot(ExtentTest extentTest, WebDriver driver, LogStatus status,
			java.lang.String stepName) throws Exception {
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
			// Attaching screen shot in Html Report
			extentTest.log(status, "Snapshot below: " + extentTest.addScreenCapture(dest));
		} catch (Exception e) {
			logger.info("Exception is occured at TakeScreenshot method" + "->" + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	// This code is for to Log the content using Log4J
	public static void logger(java.lang.String message) throws Exception {
		try {
			logger.info(message);
		} catch (Exception e) {
			logger.info("Exception is occured at logger method" + "->" + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	// This code is for copy the result files into 'Results' directory for
	// Emailing purpose
	public static void copyFile() throws Exception {
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
