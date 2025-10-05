package pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Optional;

// TapItemsPage maybe Laptops, Watches, Mobiles ... etc from taps it is an object
// and all the taps part of the home page, so that for example I can from Laptops tap go to smartwatches tap
// ,so I extend HomePage in TapItemsPage.

public class TapItemsPage extends HomePage {

    private final SHAFT.GUI.WebDriver driver;
    private final static By loadMoreBtnLocator = By.xpath("//button//span[normalize-space(text())='Load More']");
    private final static By itemCard = By.xpath("//a[@class='relative']");

    public TapItemsPage(SHAFT.GUI.WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public TapItemsPage selectItem(String item) {
        selectItemWithLoadMore(item);
        return this;
    }


    private void selectItemWithLoadMore(String itemName) {
        JavascriptExecutor js = (JavascriptExecutor) driver.getDriver();
        boolean elementFound = false;

        while (!elementFound) {
            try {
                // Get all currently loaded items
                driver.element().waitUntil(ExpectedConditions.visibilityOfAllElementsLocatedBy(itemCard));
                List<WebElement> items = driver.getDriver().findElements(itemCard);
                // Try to find the target item
                Optional<WebElement> targetItem = items.stream()
                        .filter(item -> item.findElement(By.cssSelector("p > span"))
                                .getText().equals(itemName)).findFirst();

                if (targetItem.isPresent()) {
                    WebElement element = targetItem.get();
                    try {
                        driver.element().waitUntil(ExpectedConditions.visibilityOf(element));
                        element.click();
                        elementFound = true;
                    } catch (ElementNotInteractableException e) {
                        js.executeScript("arguments[0].scrollIntoView(true);", element);
                        js.executeScript("arguments[0].click();", element);
                        elementFound = true;
                    }
                } else {
                    try {
                        driver.element().waitUntil(ExpectedConditions.elementToBeClickable(loadMoreBtnLocator));
                        driver.element().click(loadMoreBtnLocator);
                    } catch (ElementClickInterceptedException e) {
                        driver.element().clickUsingJavascript(loadMoreBtnLocator);
                    }
                }

            } catch (NoSuchElementException ex) {
                throw new RuntimeException("Load More button not found. Item not found: " + itemName, ex);
            }
        }
    }

}
