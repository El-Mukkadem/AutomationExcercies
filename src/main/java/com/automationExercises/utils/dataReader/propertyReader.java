package com.automationExercises.utils.dataReader;

import com.automationExercises.utils.logs.logsManager;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collection;
import java.util.Properties;

public class propertyReader {
    // Load Properties function to load all data exists on .properties files into system properties
    public static Properties loadProperties() {
        try {
            Properties properties = new Properties();
            Collection<File> propertiesFiles;
            propertiesFiles = FileUtils.listFiles(new File("src/main/resources"), new String[]{"properties"}, true); //get all files with extension properties
            propertiesFiles.forEach(file -> {
                try {
                    properties.load(FileUtils.openInputStream(file));
                } catch (Exception e) {
                logsManager.error("Error loading properties file",file.getName(),e.getMessage());
                }
                properties.putAll(System.getProperties());
                System.getProperties().putAll(properties);
            });
            return properties;
        } catch (Exception e) {
                logsManager.error("Error loading properties file",e.getMessage());
                return null;
        }
    }

    public static String getProperty(String key) {
        try {
            return System.getProperty(key);
        } catch (Exception e) {
            logsManager.error("Error property for key",key,e.getMessage());
            return "";
        }
    }
}