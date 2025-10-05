package basetest;

import com.shaft.driver.SHAFT;
import org.testng.annotations.*;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;

import static basetest.DriverManager.*;


public class BaseTest {

    protected static SHAFT.TestData.JSON userData;
    protected static SHAFT.TestData.JSON itemsData;
    protected static SHAFT.TestData.JSON regionData;
    protected static SHAFT.TestData.JSON taps;

    @BeforeClass(alwaysRun = true)
    public void beforeEveryClass() {
        userData = new SHAFT.TestData.JSON("users.json");
        itemsData = new SHAFT.TestData.JSON("items.json");
        regionData = new SHAFT.TestData.JSON("region.json");
        taps = new SHAFT.TestData.JSON("taps.json");
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        setThreadDriver(); //set driver to thread
        // get driver from thread navigate to URL
        getThreadDriver().browser().navigateToURL(SHAFT.Properties.web.baseURL());

        //Login before the test method
        new HomePage(getThreadDriver())
                .clickSignIn();
        new LoginPage(getThreadDriver())
                .signIn(userData.getTestData("user1.mobileNumber"),
                        userData.getTestData("user1.password"));
        new HomePage(getThreadDriver())
                .chooseRegion(regionData.getTestData("uae"));
    }


    @AfterMethod(alwaysRun = true)
    public void cleanEnvironment() {
        // getThreadDriver after E2ETest is in CheckoutPage
        // Here I Navigate back to CartPage to leave the cart empty
        // so that I can run the same test again
        getThreadDriver().browser().navigateBack();
        new CartPage(getThreadDriver())
                .removeAllItemsFromCart();
    }


    @AfterClass(alwaysRun = true)
    public void quit() {
        quitDriver();
    }

}
