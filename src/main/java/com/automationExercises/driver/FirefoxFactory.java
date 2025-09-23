package com.automationExercises.driver;

import com.automationExercises.utils.dataReader.propertyReader;
import com.automationExercises.utils.logs.logsManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;

public class FirefoxFactory extends AbstractDriver {

    private FirefoxOptions getOptions () {
        FirefoxOptions Options = new FirefoxOptions ();
        Options.addArguments("--remote-allow-origins=*");
        Options.addArguments("--disable-notifications");
        Options.addArguments("--disable-popup-blocking");
        Options.addArguments("--disable-infobars");
        Options.addArguments("--disable-extensions");
        Options.addArguments("--disable-gpu");
        Options.addArguments("--start-maximized");
        if(propertyReader.getProperty("excutionType").equalsIgnoreCase("localHeadless")||
                propertyReader.getProperty("excutionType").equalsIgnoreCase("remotre")){
            Options.addArguments("--headless=new");}
        Options.setAcceptInsecureCerts(true);
        Options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        return Options;
    }

    @Override
    public WebDriver createDriver () {

        if(propertyReader.getProperty("excutionType").equalsIgnoreCase("local")||
                propertyReader.getProperty("excutionType").equalsIgnoreCase("localHeadless")){
            return new FirefoxDriver(getOptions());
        } else if (propertyReader.getProperty("excutionType").equalsIgnoreCase("remote")) {
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



