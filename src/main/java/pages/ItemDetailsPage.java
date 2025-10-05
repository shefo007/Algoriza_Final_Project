package pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// this class contains all item details, condition, color, size (item options)
// , also it's part of the home page because it contains the main elements from HomePage,
// so I extend HomePage Class in ItemDetailsPage
public class ItemDetailsPage extends HomePage{

    private final SHAFT.GUI.WebDriver driver;
    private final static By addToCartBtn = By.xpath("//button[contains(text(),'Add To Cart')]");
    private final static By plusBtn = By.cssSelector("span.icon-plus");
    private final static By viewCartBtn = By.xpath("//a[@title='View Cart']");



    public ItemDetailsPage(SHAFT.GUI.WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // product may be out of stock
    public ItemDetailsPage clickAddToCartBtn() {
        try {
            driver.element().waitUntil(ExpectedConditions.visibilityOfElementLocated(addToCartBtn));
            driver.element().click(addToCartBtn);
        } catch (NoSuchElementException e) {
            System.out.println("Item out of stock" + e.getMessage());
        }
        return this;
    }

    // some products can't add more than one or there is only one product can't add more
    public ItemDetailsPage clickPlusBtn() {
        try {
            driver.element().waitUntil(ExpectedConditions.visibilityOfElementLocated(plusBtn));
            if (driver.getDriver().findElement(plusBtn).isEnabled()) {
                driver.element().click(plusBtn);
            }
        } catch (ElementNotInteractableException e) {
            System.out.println("May be only one item in stock" + e.getMessage());
        }
        return this;
    }

    //some product may be out of stock, so view cart btn may not be visible
    public ItemDetailsPage clickViewCartBtn() {
        try {
            driver.element().waitUntil(ExpectedConditions.visibilityOfElementLocated(viewCartBtn));
            driver.element().click(viewCartBtn);
        } catch (NoSuchElementException e) {
            driver.element().clickUsingJavascript(viewCartBtn);
            System.out.println(e.getMessage());
        }
        return this;
    }

    // choose all options at once
    public ItemDetailsPage addAllOptions(String... options) {

        List<String> optionsList = new ArrayList<>(Arrays.asList(options));

        for (String option : optionsList) {
            By optionLocator = By.xpath("//label[@title='"+option+"']/input");
            try {
                driver.element().waitUntil(ExpectedConditions.visibilityOfElementLocated(optionLocator));
                if (!driver.getDriver().findElement(optionLocator).isSelected()) {
                    driver.element().clickUsingJavascript(optionLocator);
                }
            } catch (NoSuchElementException e) {
                driver.element().verifyThat(optionLocator).doesNotExist().perform();
            }
        }
        return this;
    }

}
