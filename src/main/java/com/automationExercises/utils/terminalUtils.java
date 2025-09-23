package com.automationExercises.utils;

import com.automationExercises.utils.logs.logsManager;

import java.io.IOException;

public class terminalUtils {
    public static void executeTerminalCommand(String... commandParts) {
        try {
            Process process = Runtime.getRuntime().exec(commandParts); //allure generate -o reports --single-file --clean
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                logsManager.error("Command failed with exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            logsManager.error("Failed to execute terminal command: " + String.join(" ", commandParts), e.getMessage());
        }
    }
}
