package com.automationExercises.driver;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class SafariFactory extends AbstractDriver {

    private SafariOptions getOptions () {
        SafariOptions Options = new SafariOptions ();
        Options.setAcceptInsecureCerts(true);
        Options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        return Options;
    }

    @Override
    public WebDriver createDriver () {return new SafariDriver(getOptions());}
}
