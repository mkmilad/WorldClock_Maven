package com.wc.qa.ExtentReportListener;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.collections.Maps;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class ExtentReporterNG implements IReporter {

	private ExtentReports extent;
	
	//public void generateReport(List<XmlSuit> xmlSuites, List<ISuite> suites, String outPutDirString) {
	
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outPutDirString) {
		extent = new ExtentReports(outPutDirString + File.separator + "Extent.html", true);
		
		for(ISuite suite : suites ) {
			Map<String, ISuiteResult> result = suite.getResults();
			//Maps<String, ISuiteResult> result = suite.getResults();
			
			for(ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();
				
				buildTestNodes(context.getPassedTests(), LogStatus.PASS);
				buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
				buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
				
				
			}
			
			
		}
		
		extent.flush(); // clear out all cachaes 
		extent.close();
	}
	
	private void buildTestNodes(IResultMap tests, LogStatus status ) {
		ExtentTest test;
	
		
		if(tests.size()> 0) {
			
			for(ITestResult result : tests.getAllResults()) {
				test = extent.startTest(result.getMethod().getMethodName());
				test.setStartedTime(getTime(result.getStartMillis()));
				test.setEndedTime(getTime(result.getEndMillis()));
				
				for(String group : result.getMethod().getGroups())
				test.assignCategory(group);
				
				if(result.getThrowable() != null) { // catch the exceptions 
					test.log(status, result.getThrowable());
				}
				else {
					test.log(status, "Test" + status.toString().toLowerCase() + "ed");
				}
				extent.endTest(null);
				
			}
		}
	}
	
	private Date getTime(long mills) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(mills); // calendar set in milli second
		return calendar.getTime();
	}
	

}
