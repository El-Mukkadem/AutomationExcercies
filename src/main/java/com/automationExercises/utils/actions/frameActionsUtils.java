package com.automationExercises.utils.actions;

import com.automationExercises.utils.logs.logsManager;
import com.automationExercises.utils.waitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class frameActionsUtils {
    private final WebDriver driver;
    private final waitUtils waitUtils;

    public frameActionsUtils(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new waitUtils(driver);
    }

    //Switch to frames by index
    public void switchByIndex(int index){
        waitUtils.fluentWait().until(d ->
        {
            try{
                d.switchTo().frame(index);
                logsManager.info("Switching to frame by index: "+ index);
                return true;
            }catch (Exception e){
                return false;
            }
        });
    }

    //Switch to frame by Name or ID
    public void switchByNameOrId(String nameOrId){
        waitUtils.fluentWait().until(d ->
        {
            try{
                d.switchTo().frame(nameOrId);
                logsManager.info("Switching to frame by nameOrId: "+ nameOrId);
                return true;
            }catch (Exception e){
                return false;
            }
        }
        );
    }

    //Switch to frame by element
    public void switchByElement(By frameLocator){
        waitUtils.fluentWait().until(d ->
        {
            try{
                d.switchTo().frame(d.findElement(frameLocator));
                logsManager.info("Switching to frame by element: "+ frameLocator);
                return true;
            }catch (Exception e){
                return false;
            }
        }
        );
    }

    //Switch to default content
    public void switchToDefault(){
        waitUtils.fluentWait().until(d ->
        {
            try{
                d.switchTo().defaultContent();
                logsManager.info("Switching to default content");
                return true;
            }catch (Exception e){
                return false;
            }
        }
        );
    }
}
