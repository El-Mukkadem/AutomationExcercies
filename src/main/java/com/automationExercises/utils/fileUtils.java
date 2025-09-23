package com.automationExercises.utils;

import com.automationExercises.utils.dataReader.propertyReader;
import com.automationExercises.utils.logs.logsManager;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import static org.aspectj.util.FileUtil.copyFile;

public class fileUtils {
    private static final String USER_DIR = propertyReader.getProperty("user.dir");

    private fileUtils() {
        // private constructor to prevent instantiation
    }

    //Renaming a file
    public static void renameFile(String oldName, String newName) {
        try {
            var targetFile = new File(oldName);
            String targetDirectory = targetFile.getParentFile().getAbsolutePath();
            File newFile = new File(targetDirectory + File.separator + newName);
            if (!targetFile.getPath().equals(newFile.getPath())) {
                copyFile(targetFile, newFile);
                org.apache.commons.io.FileUtils.deleteQuietly(targetFile);
                logsManager.info("Target File Path: \"" + oldName + "\", file was renamed to \"" + newName + "\".");
            } else {
                logsManager.info(("Target File Path: \"" + oldName + "\", already has the desired name \"" + newName + "\"."));
            }
        } catch (IOException e) {
            logsManager.error(e.getMessage());
        }
    }


    //Creating a directory if it doesn't exist
    public static void createDirectory(String path) {
        try {
            File file = new File(USER_DIR + path);
            if (!file.exists()) {
                file.mkdirs();
                logsManager.info("Created Directory: " + path);
            }
        } catch (Exception e) {
            logsManager.error("Failed to create directory " + path, e.getMessage());
        }}

    //Cleaning a directory (deleting all files inside it)
    public static void deleteDirectory (File file){
            try {
                FileUtils.deleteDirectory(file);
            } catch (Exception e) {
                logsManager.error("Failed to delete directory " + file.getAbsolutePath(), e.getMessage());
            }
        }


//force delete
public static void forceDelete(File file) {
    try {
        org.apache.commons.io.FileUtils.forceDeleteOnExit(file);
        logsManager.info("File deleted: " + file.getAbsolutePath());
    } catch (IOException e) {
        logsManager.error("Failed to delete file: " + file.getAbsolutePath(), e.getMessage());
    }
    }

    //check if the file exists
    public static boolean isFileExists( String fileName) {
        String filePath = USER_DIR + "/src/test/resources/downloads/" ;
        File file = new File(filePath+ fileName);
        return file.exists();
    }

}
