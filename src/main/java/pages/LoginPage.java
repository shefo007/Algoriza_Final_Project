package pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class LoginPage {

    private final SHAFT.GUI.WebDriver driver;
    private final static By mobileEmailField = By.id("identifier");
    private final static By continueButton = By.xpath("//button/span[contains(text(), 'Continue')]");
    private final static By passwordField = By.id("password");
    private final static By signInBtn = By.xpath("//button/span[contains(text(),'Sign In')]");

    public LoginPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage signIn(String mobileEmail, String password) {
        driver.element().type(mobileEmailField, mobileEmail);
        driver.element().click(continueButton);
        driver.element().type(passwordField, password);
        driver.element().click(signInBtn);
        return this;
    }


}
