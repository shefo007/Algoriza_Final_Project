package pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Optional;

public class CartPage extends HomePage{

    private final SHAFT.GUI.WebDriver driver;
    private final static By itemCard = By.xpath("//div[@class='divide-y']");
    private final static By removeBtn = By.xpath(".//span[contains(text(),'Remove')]/ancestor::span[@role='button']");
    private final static By agreeBtn = By.xpath("//button[contains(text(),'Agree')]");
    private final static By checkoutBtn = By.xpath("//a[normalize-space(text())='Checkout']");

    public CartPage(SHAFT.GUI.WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    public CartPage removeItemFromCart(String itemName) {
        JavascriptExecutor js = (JavascriptExecutor) driver.getDriver();
        try {
            driver.element().waitUntil(ExpectedConditions.visibilityOfAllElementsLocatedBy(itemCard));
            List<WebElement> items = driver.getDriver().findElements(itemCard);
            Optional<WebElement> targetItem = items.stream()
                    .filter(item -> item.findElement(By.xpath(".//a/p"))
                            .getText().contains(itemName)).findFirst();
            if (targetItem.isPresent()) {
                WebElement element = targetItem.get();
                try {
                    driver.element().waitUntil(ExpectedConditions.visibilityOf(element));
                    element.findElement(removeBtn).click();
                } catch (ElementNotInteractableException e) {
                    js.executeScript("arguments[0].click();", element);
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        return this;
    }

    public CartPage clickAgreeConfirmation() {
        driver.element().waitUntil(ExpectedConditions.visibilityOfElementLocated(agreeBtn));
        driver.element().click(agreeBtn);
        return this;
    }

    public CartPage clickCheckoutBtn() {
        try {
            driver.element().waitUntil(ExpectedConditions.visibilityOfElementLocated(checkoutBtn));
            driver.element().click(checkoutBtn);
        } catch (ElementNotInteractableException e) {
            System.out.println(e.getMessage());
        }
        return this;
    }


    // this method to remove all the items from the cart to leave cart empty but from cart page
    public CartPage removeAllItemsFromCart() {
        List<WebElement> items = driver.getDriver().findElements(itemCard);

        for (WebElement item : items) {
            driver.element().waitUntil(ExpectedConditions.visibilityOfAllElementsLocatedBy(itemCard));
            item.findElement(removeBtn).click();
            clickAgreeConfirmation();
        }

        return this;
    }

}
