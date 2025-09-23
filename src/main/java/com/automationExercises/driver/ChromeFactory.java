package com.automationExercises.driver;

import com.automationExercises.utils.dataReader.propertyReader;
import com.automationExercises.utils.logs.logsManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class ChromeFactory extends AbstractDriver {

    private ChromeOptions getOptions () {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--start-maximized");
        Map<String, Object> prefs = new HashMap<>();
        String userDir = System.getProperty("user.dir");
        String downloadPath = userDir + "\\src\\test\\resources\\downloads";
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.default_directory",downloadPath);
        options.setExperimentalOption("prefs", prefs);
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        options.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        options.setCapability(CapabilityType.ENABLE_DOWNLOADS, true);
        options.setAcceptInsecureCerts(true);
        switch (propertyReader.getProperty("executionType"))
        {
            case "LocalHeadless" -> options.addArguments("--headless=new");
            case  "Remote" ->
            {
                options.addArguments("--disable-gpu");
                options.addArguments("--disable-extensions");
                options.addArguments("--headless=new");
            }
        }
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        return options;
    }

    @Override
    public WebDriver createDriver () {
        if(propertyReader.getProperty("executionType").equalsIgnoreCase("local")||
                propertyReader.getProperty("executionType").equalsIgnoreCase("localHeadless")){
            return new ChromeDriver(getOptions());    
        } else if (propertyReader.getProperty("executionType").equalsIgnoreCase("remote")) {
            try{
            return new RemoteWebDriver(
                    new URI("http://"+ propertyReader.getProperty("remoteHost") + ":"+ propertyReader.getProperty("remotePort") + "/wd/hub").toURL(),getOptions()
        );} catch (Exception e){
            logsManager.error("Failed to create remote driver: " + e.getMessage());
            throw new RuntimeException("Failed to create remote driver", e);
        }}
        else  {
            logsManager.error("Invalid Execution Type"+ propertyReader.getProperty("excutionType"));
            throw new IllegalStateException("Invalid Execution Type"+ propertyReader.getProperty("excutionType"));        }
    }
}
