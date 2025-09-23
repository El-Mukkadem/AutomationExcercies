package com.automationExercises.utils;

import com.automationExercises.utils.dataReader.propertyReader;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class waitUtils {
    private WebDriver driver;

    public waitUtils(WebDriver driver) {
        this.driver = driver;
    }

    public FluentWait<WebDriver> fluentWait() {
        return new FluentWait<>(driver)
        .withTimeout(Duration.ofSeconds(Long.parseLong(propertyReader.getProperty("DEFAULT_WAIT"))))
                .pollingEvery(Duration.ofMillis(100))
                .ignoreAll(getException());
    }

    private ArrayList<Class<? extends Exception>> getException () {
        ArrayList<Class<? extends Exception>> exceptions = new ArrayList<>();
        exceptions.add(NullPointerException.class);
        exceptions.add(NoSuchElementException.class);
        exceptions.add(StaleElementReferenceException.class);
        exceptions.add(ElementNotInteractableException.class);
        exceptions.add(ElementClickInterceptedException.class);
        return exceptions;
    }
}
