package com.automationExercises.utils.actions;

import com.automationExercises.utils.logs.logsManager;
import com.automationExercises.utils.waitUtils;
import org.openqa.selenium.WebDriver;

public class alertActions {
    private final WebDriver driver;
    private final waitUtils waitUtils;
    public alertActions(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new waitUtils(driver);
    }

    //Accept alert
    public void acceptAlert() {
        waitUtils.fluentWait().until(d ->
        {
            try{
                d.switchTo().alert().accept();
                return true;
            } catch (Exception e) {
                logsManager.error("Error accepting alert alert: ",e.getMessage());
                return false;
            }
        });
    }

    //Dismiss alert
    public void dismissAlert() {
        waitUtils.fluentWait().until(d ->
        {
            try{
                d.switchTo().alert().dismiss();
                return true;
            }catch (Exception e){
                logsManager.error("Error dismissing alert alert: ",e.getMessage());
                return false;
            }
        }
        );
    }

    //Get text from alert
    public String getAlertText() {
        return waitUtils.fluentWait().until(d ->
        {
            try{
                String text = d.switchTo().alert().getText();
                return !text.isEmpty() ? text : null;
            }catch (Exception e){
                logsManager.error("Error getting alert text: ",e.getMessage());
                return null;
            }
        }
        );
    }

    //Set text in alert
    public void setAlertText(String text) {
        waitUtils.fluentWait().until(d ->
        {
            try{
                d.switchTo().alert().sendKeys(text);
                return true;
            }catch (Exception e){
                logsManager.error("Error setting alert text: ",e.getMessage());
                return false;
            }
        }
        );
    }
}
