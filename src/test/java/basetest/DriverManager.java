package basetest;

import com.shaft.driver.SHAFT;

public class DriverManager {

    private static final ThreadLocal<SHAFT.GUI.WebDriver> threadDriver = new ThreadLocal<>();

    public static void setThreadDriver() {
        threadDriver.set(new SHAFT.GUI.WebDriver());
    }

    public static SHAFT.GUI.WebDriver getThreadDriver() {
        return threadDriver.get();
    }

    public static void quitDriver() {
        if (getThreadDriver() != null) {
            getThreadDriver().quit();
            threadDriver.remove();
        }
    }

}
