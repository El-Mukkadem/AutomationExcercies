package com.automationExercises.utils.Report;

import com.automationExercises.utils.OSManager;
import com.automationExercises.utils.logs.logsManager;
import com.automationExercises.utils.logs.timeManager;
import com.automationExercises.utils.terminalUtils;
import org.apache.commons.io.FileUtils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.automationExercises.utils.Report.allureConstants.HISTORY_FOLDER;
import static com.automationExercises.utils.Report.allureConstants.RESULTS_HISTORY_FOLDER;
import static com.automationExercises.utils.dataReader.propertyReader.getProperty;

public class allureReportGenerator {
    //Generate Allure report
    //--single-file - generate single file report
    public static void generateReports(boolean isSingleFile) {
        Path outputFolder = isSingleFile ? allureConstants.REPORT_PATH : allureConstants.FULL_REPORT_PATH;
        // allure generate -o reports --single-file --clean
        List<String> command = new ArrayList<>(List.of(
                allureBinaryManager.getExecutable().toString(),
                "generate",
                allureConstants.RESULTS_FOLDER.toString(),
                "-o", outputFolder.toString(),
                "--clean"
        ));
        if (isSingleFile) command.add("--single-file");
        terminalUtils.executeTerminalCommand(command.toArray(new String[0]));
    }

    //rename report file to AllureReport_timestamp.html
    public static String renameReport() {
        String newFileName = allureConstants.REPORT_PREFIX + timeManager.getTimeStamp() + allureConstants.REPORT_EXTENSION; // AllureReport_20250720_211230.html
        com.automationExercises.utils.fileUtils.renameFile(allureConstants.REPORT_PATH.resolve(allureConstants.INDEX_HTML).toString(), newFileName);
        return newFileName;
    }

    //open Allure report in browser
    public static void openReport(String reportFileName) {
        if (!getProperty("executionType").toLowerCase().contains("local")) return;

        Path reportPath = allureConstants.REPORT_PATH.resolve(reportFileName);
        switch (OSManager.getOperatingSystem()) {
            case WINDOWS -> terminalUtils.executeTerminalCommand("cmd.exe", "/c", "start", reportPath.toString());
            case MAC, LINUX -> terminalUtils.executeTerminalCommand("open", reportPath.toString());
            default -> logsManager.warn("Opening Allure Report is not supported on this OS.");
        }
    }

    //copy history folder to results folder
    public static void copyHistory() {
        try {
            FileUtils.copyDirectory(HISTORY_FOLDER.toFile(), RESULTS_HISTORY_FOLDER.toFile());
        } catch (Exception e) {
            logsManager.error("Error copying history files", e.getMessage());
        }
    }
}
