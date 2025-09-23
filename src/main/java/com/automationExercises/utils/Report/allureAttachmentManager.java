package com.automationExercises.utils.Report;
import com.automationExercises.utils.logs.logsManager;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;


public class allureAttachmentManager {

        // attachScreenshot, attachLogs, attachRecords methods would go here
        public static void attachScreenshot(String name, String path) {
                try {
                        Path screenshot = Path.of(path);
                        if (Files.exists(screenshot)) {
                                Allure.addAttachment(name, Files.newInputStream(screenshot));
                        } else {
                                logsManager.error("Screenshot not found: " + path);
                        }
                } catch (Exception e) {
                        logsManager.error("Error attaching screenshot", e.getMessage());
                }
        }

        public static void attachLogs() {
                try {
                        LogManager.shutdown();
                        File logFile = new File(logsManager.LOGS_PATH +"logs.log");
                        ((LoggerContext) LogManager.getContext(false)).reconfigure();
                        if (logFile.exists()) {
                                Allure.attachment("logs.log", Files.readString(logFile.toPath()));
                        }
                } catch (Exception e) {
                        logsManager.error("Error attaching logs", e.getMessage());
                }
        }



}
