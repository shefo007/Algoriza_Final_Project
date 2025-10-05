package pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {

    private final SHAFT.GUI.WebDriver driver;
    private final static By accountHover = By.xpath("(//span[contains(text(),'Account')])[1]");
    private final static By cartBtn = By.xpath("//div[@class='container relative']//span[contains(text(),'Cart')]");
    //    private final static By removeItemBtn = By.xpath("(//span[contains(@title,'Remove')]//ancestor::span)[1]");
    private final static By viewCartBtn = By.xpath("(//a[contains(text(),'View Cart')])[1]");
    private final static By regionHover = By.xpath("//div[@class='container relative']//button[not(contains(@type,'submit'))]/span[contains(text(),'INTL')]");

    public HomePage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    public HomePage clickSignIn() {
        driver.element().waitUntil(ExpectedConditions.visibilityOfElementLocated(accountHover));
        driver.element().select(accountHover, "Sign In");
        return this;
    }

    public HomePage chooseRegion(String region) {
        driver.element().waitUntil(ExpectedConditions.visibilityOfElementLocated(regionHover));
        driver.element().select(regionHover, region);
        return this;
    }

    public HomePage selectTapItems(String tapName) {
        By tapLocator = By.xpath("(//ul/li/a[@href='/"+ tapName.toLowerCase() +"'])[1]");
        try {
            driver.element().waitUntil(ExpectedConditions.elementToBeClickable(tapLocator));
            driver.element().click(tapLocator);
        } catch (NoSuchElementException e) {
            driver.element().clickUsingJavascript(tapLocator);
        }
        return this;
    }

}
