package com.automationExercises.mediaManager;

import com.automationExercises.utils.Report.allureAttachmentManager;
import com.automationExercises.utils.logs.logsManager;
import com.automationExercises.utils.logs.timeManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;


    public class screenshotManager {

        public static final String SCREENSHOTS_PATH = "test-output/screenshots/";

        //take full page screenshot
        public static void takeFullPageScreenshot(WebDriver driver, String screenshotName) {
            try {
                // Capture screenshot using TakesScreenshot
                File screenshotSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

                // Save screenshot to a file if needed
                File screenshotFile = new File(SCREENSHOTS_PATH + screenshotName + "-" + timeManager.getTimeStamp() + ".png");
                FileUtils.copyFile(screenshotSrc, screenshotFile);
                allureAttachmentManager.attachScreenshot(screenshotName,screenshotFile.getAbsolutePath());
                logsManager.info("Capturing Screenshot Succeeded");
            } catch (Exception e) {
                logsManager.error("Failed to Capture Screenshot " + e.getMessage());
            }
        }

        //take screenshot of a specific element
        public static void takeElementScreenshot(WebDriver driver, By elementSelector) {
            try {
                // Highlight the element if needed (not implemented here)
                // Capture screenshot using TakesScreenshot
                String ariaName = driver.findElement(elementSelector).getAccessibleName();
                File screenshotSrc = driver.findElement(elementSelector).getScreenshotAs(OutputType.FILE);

                // Save screenshot to a file if needed
                File screenshotFile = new File(SCREENSHOTS_PATH + ariaName + "-" + timeManager.getTimeStamp() + ".png");
                FileUtils.copyFile(screenshotSrc, screenshotFile);
                // TODO: Attach the screenshot to Allure if needed

                logsManager.info("Capturing Screenshot Succeeded");
            } catch (Exception e) {
                logsManager.error("Failed to Capture Element Screenshot" , e.getMessage());
            }
        }
    }

