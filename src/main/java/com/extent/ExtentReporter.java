package com.extent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.stream.Stream;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.deviceDetails.DeviceDetails;
import com.driverInstance.DriverInstance;
import com.propertyfilereader.PropertyFileReader;
import com.utility.LoggingUtils;
import io.appium.java_client.AppiumDriver;

public class ExtentReporter implements ITestListener {

	private static String report;
	private static String platform;
	private static ThreadLocal<ExtentReports> extent = new ThreadLocal<>();
	private static ThreadLocal<ExtentHtmlReporter> htmlReport = new ThreadLocal<>();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	private static ThreadLocal<ExtentTest> childTest = new ThreadLocal<>();
	private static File src;
	private static String currentDate;
	private boolean runmode = true;
	private static String BrowserType;
	public static String filePath;
	public static String fileName;
	private static String AppVersion;

	/** The Constant logger. */
//	final static Logger logger = Logger.getLogger("rootLogger");
	static LoggingUtils logger = new LoggingUtils();

	@SuppressWarnings("static-access")
	public void setReport(String report) {
		this.report = report;
	}

	@SuppressWarnings("static-access")
	public String getReport() {
		return this.report;
	}

	@SuppressWarnings("static-access")
	public String getPlatform() {
		return this.platform;
	}

	@SuppressWarnings("static-access")
	public void setPlatform(String platform) {
		this.platform = platform;
	}

	@SuppressWarnings("static-access")
	public String getAppVersion() {
		return this.AppVersion;
	}

	@SuppressWarnings("static-access")
	public void setAppVersion(String versionName) {
		this.AppVersion = versionName;
	}

	public static AppiumDriver<WebElement> getDriver() {
		return DriverInstance.tlDriver.get();
	}

	private WebDriver getWebDriver() {
		return DriverInstance.tlWebDriver.get();
	}

	public void initExtentDriver() {
		if (getPlatform().equals("Web")) {
			src = ((TakesScreenshot) getWebDriver()).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
		} else if (getPlatform().equals("Android") || getPlatform().equals("PWA")) {
			src = ((TakesScreenshot) getDriver()).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
		} else if (getPlatform().equals("MPWA")) {
			src = ((TakesScreenshot) getDriver()).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
		}
	}

	@Override
	public void onStart(ITestContext context) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		currentDate = dateFormat.format(date).toString().replaceFirst(" ", "_").replaceAll("/", "_").replaceAll(":",
				"_");
		setReport(context.getName());
		setPlatform(context.getSuite().getName());
		appVersion();

		filePath = System.getProperty("user.dir") + "/Reports" + "/" + currentDate + "/" + getPlatform() + "/"
				+ context.getCurrentXmlTest().getParameter("userType") + "/" + context.getName() + "/"
				+ context.getCurrentXmlTest().getParameter("userType") + "_" + context.getName() + "_" + getAppVersion()
				+ "_" + getDate() + ".html";

		fileName = context.getCurrentXmlTest().getParameter("userType") + "_" + context.getName() + "_"
				+ getAppVersion() + "_" + getDate() + ".html";

		htmlReport.set(new ExtentHtmlReporter(filePath));
		htmlReport.get().loadXMLConfig(new File(System.getProperty("user.dir") + "/ReportsConfig.xml"), true);
		extent.set(new ExtentReports());
		extent.get().attachReporter(htmlReport.get());
		BrowserType = context.getCurrentXmlTest().getParameter("browserType");
		if (!getPlatform().equals("Web")) {
			DeviceDetails.getTheDeviceManufacturer();
			DeviceDetails.getTheDeviceOSVersion();
		}
	}

	@Override
	public void onTestStart(ITestResult result) {
		if ((Stream.of(result.getName(), "Suite").anyMatch(DriverInstance.getRunModule()::equals)
				&& DriverInstance.startTest) || result.getName().equals("Login")
				|| result.getName().equals("PWAWEBLogin")) {
			logger.info(":::::::::Test " + result.getName() + " Started::::::::");
			test.set(extent.get().createTest(result.getName(),DriverInstance.getENvironment()));
		} else {
			runmode = false;
			throw new SkipException("");
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		screencapture();
		childTest.get().log(Status.PASS, result.getName() + " is PASSED");
		logger.info("::::::::::Test " + result.getName() + " PASSED::::::::::");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		screencapture();
		childTest.get().log(Status.FAIL, result.getName() + " is FAILED");
		logger.info("::::::::::Test " + result.getName() + " FAILED::::::::::");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		if (runmode) {
			HeaderChildNode(result.getTestName());
			childTest.get().log(Status.SKIP, result.getName() + " is SKIPPED");
			logger.info("::::::::::Test " + result.getName() + " SKIPPED::::::::::");
		}
	}

	public void HeaderChildNode(String header) {
		if (test.get() != null)
			childTest.set(test.get().createNode(header));
	}

	public void extentLogger(String stepName, String details) {
		childTest.get().log(Status.INFO, details);
	}
	
	public void extentLoggerPass(String stepName, String details) {
		childTest.get().log(Status.PASS, details);
	}

	public void extentLoggerFail(String stepName, String details) {
		childTest.get().log(Status.FAIL, details);
		screencapture();
	}

	public void extentLoggerWarning(String stepName, String details) {
		childTest.get().log(Status.WARNING, details);
	}

	@Override
	public void onFinish(ITestContext context) {
		if (!getPlatform().equals("Web")) {
			extent.get().setSystemInfo("Device Info ", DeviceDetails.DeviceInfo(context.getName()));
			extent.get().setSystemInfo("App version : ", getAppVersion().replace("Build", ""));
		} else if (getPlatform().equals("Web")) {
			extent.get().setSystemInfo("Browser Name ", BrowserType);
//			extent.get().setSystemInfo("Browser Version ", BrowserType);
		}
		extent.get().flush();
//		MailReport.EmailReport();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult context) {
	}

	public static String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String name = dateFormat.format(date).toString().replaceFirst(" ", "_").replaceAll("/", "_").replaceAll(":","_");
		return name;
	}

	public void screencapture() {
		try {
			initExtentDriver();
			org.apache.commons.io.FileUtils.copyFile(src,
					new File(System.getProperty("user.dir") + "/Reports" + "/" + currentDate + "/" + getPlatform() + "/"
							+ Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
									.getParameter("userType")
							+ "/" + getReport() + "/Screenshots/" + getReport() + "_" + getDate() + ".jpg"));
			childTest.get().addScreenCaptureFromBase64String(base64Encode(src));
			logger.log(src, "Attachment");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String base64Encode(File file) {
		if (file == null || !file.isFile()) {
			return null;
		}
		try {
			@SuppressWarnings("resource")
			FileInputStream fileInputStreamReader = new FileInputStream(file);
			byte[] bytes = new byte[(int) file.length()];
			if (fileInputStreamReader.read(bytes) != -1) {
				return "data:image/png;base64," + new String(Base64.getEncoder().encode(bytes), "UTF-8");
			}
			return null;
		} catch (Throwable e) {
			return null;
		}
	}

	public void appVersion() {
		if (getPlatform().equals("Android")) {
			PropertyFileReader handler = new PropertyFileReader("properties/AppPackageActivity.properties");
			setAppVersion("Build " + DeviceDetails.getAppVersion(handler.getproperty("zeePackage")).trim()
					.replace("versionName=", ""));
			logger.info(getAppVersion());
		} else {
			setAppVersion("");
		}
	}

}
