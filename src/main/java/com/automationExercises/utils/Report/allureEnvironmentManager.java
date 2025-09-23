package com.automationExercises.utils.Report;

import com.automationExercises.utils.logs.logsManager;
import com.google.common.collect.ImmutableMap;

import java.io.File;

import static com.automationExercises.utils.dataReader.propertyReader.getProperty;
import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class allureEnvironmentManager {
    public static void setEnvironmentVariables() {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("OS", getProperty("os.name"))
                        .put("Java version:", getProperty("java.runtime.version"))
                        .put("Browser", getProperty("browserType"))
                        .put("Execution Type", getProperty("executionType"))
                        .put("URL", getProperty("baseUrlWeb"))
                        .build(), String.valueOf(allureConstants.RESULTS_FOLDER) + File.separator
        );
        logsManager.info("Allure environment variables set.");
        allureBinaryManager.downloadAndExtract();
    }
}
