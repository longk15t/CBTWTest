package ctflearn.tests;

import ctflearn.models.Env;
import ctflearn.pages.BasePage;
import ctflearn.pages.DashboardPage;
import ctflearn.pages.LoginPage;
import ctflearn.pages.TopMenu;
import net.datafaker.Faker;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.Random;

import static org.testng.Assert.assertTrue;

public class BaseTest {
    public BasePage basePage;
    private LoginPage loginPage;
    protected DashboardPage dashboardPage;
    protected TopMenu topMenu;
    protected static final Faker faker = new Faker();
    protected static final Random rand = new Random();
    protected static Env env;

    @BeforeSuite
    public void beforeSuite() throws IOException {
        basePage = new BasePage();
        basePage.setUp("config.json");
        basePage.navigateToUrl();
        env = basePage.getEnvironment();
    }

    @BeforeTest
    public void beforeTest() {
        loginPage = basePage.navigateToLoginPage();
        assertTrue(loginPage.isLoginPageDisplayed(), "Login page is not displayed properly");
        dashboardPage = loginPage.login();
        assertTrue(dashboardPage.isDashboardPageDisplay(), "Dashboard page is not displayed properly");
        topMenu = dashboardPage.waitForTopMenuDisplay();
    }

    @AfterTest
    public void afterTest() {
        topMenu.logout();
    }

    @AfterSuite
    public void afterSuite() {
        basePage.teardown();
    }
}
