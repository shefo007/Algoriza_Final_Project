package testPackage;

import basetest.BaseTest;
import org.testng.annotations.Test;
import pages.*;

import static basetest.DriverManager.getThreadDriver;

public class TestScenario extends BaseTest {


    @Test
    public void E2ETest() {
        new HomePage(getThreadDriver())
                .selectTapItems(taps.getTestData("laptops"));
        new TapItemsPage(getThreadDriver())
                .selectItem(itemsData.getTestData("laptops.lap1"));
        new ItemDetailsPage(getThreadDriver())
                .clickAddToCartBtn()
                .selectTapItems(taps.getTestData("smartwatches"));
        new TapItemsPage(getThreadDriver())
                .selectItem(itemsData.getTestData("smartwatches.smart1.name"));
        new ItemDetailsPage(getThreadDriver())
                .chooseConnectivity(itemsData.getTestData("smartwatches.smart1.connectivity"))
                .chooseColor(itemsData.getTestData("smartwatches.smart1.color"))
                .chooseSize(itemsData.getTestData("smartwatches.smart1.size"))
                .clickPlusBtn()
                .clickAddToCartBtn()
                .clickViewCartBtn();
        new CartPage(getThreadDriver())
                .removeItemFromCart(itemsData.getTestData("laptops.lap1"))
                .clickAgreeConfirmation()
                .clickCheckoutBtn();
        new CheckoutPage(getThreadDriver())
                .verifyCheckoutTitle();     // verify page title

    }

}
