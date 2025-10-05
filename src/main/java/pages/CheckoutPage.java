package pages;

import com.shaft.driver.SHAFT;

public class CheckoutPage {

    private final SHAFT.GUI.WebDriver driver;

    public CheckoutPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    public CheckoutPage verifyCheckoutTitle() {
        driver.browser().assertThat().title().contains("Checkout");
        return this;
    }
}
