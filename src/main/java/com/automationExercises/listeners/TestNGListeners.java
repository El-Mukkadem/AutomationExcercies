package com.automationExercises.listeners;

import com.automationExercises.driver.WebDriverProvider;
import com.automationExercises.mediaManager.screenshotManager;
import com.automationExercises.utils.Report.allureAttachmentManager;
import com.automationExercises.utils.Report.allureConstants;
import com.automationExercises.utils.Report.allureEnvironmentManager;
import com.automationExercises.utils.Report.allureReportGenerator;
import com.automationExercises.utils.dataReader.propertyReader;
import com.automationExercises.utils.fileUtils;
import com.automationExercises.utils.logs.logsManager;
import com.automationExercises.validations.validation;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;

public class TestNGListeners implements ISuiteListener, IExecutionListener, IInvokedMethodListener, ITestListener {
    public void onStart(ISuite suite) {
        suite.getXmlSuite().setName("Automation Exercise");
    }
    public void onExecutionStart() {
        logsManager.info("Test Execution started");
        cleanTestOutputDirectories();
        logsManager.info("Directories cleaned");
        createTestOutputDirectories();
        logsManager.info("Directories created");
        propertyReader.loadProperties();
        logsManager.info("Properties loaded");
        allureEnvironmentManager.setEnvironmentVariables();
        logsManager.info("Allure environment set");
    }

    public void onExecutionFinish() {
        allureReportGenerator.copyHistory();
        allureReportGenerator.generateReports(false);
        allureReportGenerator.generateReports(true);
        allureReportGenerator.openReport(allureReportGenerator.renameReport());
        logsManager.info("Test Execution Finished");
    }



    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        WebDriver driver = null;
        if (method.isTestMethod())
        {
                if (testResult.getInstance() instanceof WebDriverProvider provider)
                    driver = provider.getDriver(); //initialize driver from WebDriverProvider
                switch (testResult.getStatus()){
                    case ITestResult.SUCCESS -> screenshotManager.takeFullPageScreenshot(driver,"passed-" + testResult.getName());
                    case ITestResult.FAILURE -> screenshotManager.takeFullPageScreenshot(driver,"failed-" + testResult.getName());
                    case ITestResult.SKIP -> screenshotManager.takeFullPageScreenshot(driver,"skipped-" + testResult.getName());
                }


            validation.assertAll(testResult);

            allureAttachmentManager.attachLogs();

        }
    }


    public void onTestSuccess(ITestResult result) {
        logsManager.info("Test Case " + result.getName() + " passed");
    }

    public void onTestFailure(ITestResult result) {
        logsManager.info("Test Case " + result.getName() + " failed");
    }

    public void onTestSkipped(ITestResult result) {
        logsManager.info("Test Case " + result.getName() + " skipped");
    }


    // cleaning and creating dirs (logs, screenshots, recordings,allure-results)
    private void cleanTestOutputDirectories() {
        // Implement logic to clean test output directories
        fileUtils.deleteDirectory(allureConstants.RESULTS_FOLDER.toFile());
        fileUtils.deleteDirectory(new File(screenshotManager.SCREENSHOTS_PATH));
        fileUtils.deleteDirectory(new File("src/test/resources/downloads/"));
        fileUtils.forceDelete(new File(logsManager.LOGS_PATH + "logs.log"));
    }

    private void createTestOutputDirectories() {
        // Implement logic to create test output directories
        fileUtils.createDirectory(screenshotManager.SCREENSHOTS_PATH);
        fileUtils.createDirectory("src/test/resources/downloads/");

    }
}
