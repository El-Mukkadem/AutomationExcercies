package com.automationExercises.driver;

public enum browserSet {
    CHROME {
        @Override
        public AbstractDriver getDriver() {
            return new ChromeFactory();
        }
    },

    FIREFOX{
        @Override
        public AbstractDriver getDriver() {
            return new FirefoxFactory();
        }
    },
    SAFARI{
        @Override
        public AbstractDriver getDriver() {
            return new SafariFactory();
        }
    },
    EDGE{
        @Override
        public AbstractDriver getDriver() {
            return new EdgeFactory();
        }
    };
    public abstract AbstractDriver getDriver();

}
