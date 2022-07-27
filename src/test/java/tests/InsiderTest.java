package tests;

import actions.Methods;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InsiderPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class InsiderTest extends TestBaseRapor {
    @Test
    public void test() throws InterruptedException {
        extentTest = extentReports.createTest("Insider UI Test", "Task01");
        Methods methods = new Methods();
        Driver.getDriver().get(ConfigReader.getProperty("insiderURL"));
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        extentTest.info("https://useinsider.com/ page opened");

        InsiderPage insiderPage = new InsiderPage();
        insiderPage.cookieButton.click();
        jse.executeScript("arguments[0].click()", insiderPage.moreButton);
        insiderPage.careersButton.click();
        assertTrue(insiderPage.teamsWebElement.isDisplayed());
        extentTest.pass("Teams opened");

        assertTrue(insiderPage.locationsWebelement.isDisplayed());
        extentTest.pass("Locations opened");

        jse.executeScript("arguments[0].click()", insiderPage.seeAllTeamsButton);
        jse.executeScript("arguments[0].click()", insiderPage.quantityAssuranceButton);
        jse.executeScript("arguments[0].click()", insiderPage.seeAllQaJobsButton);

        methods.locationsSelect();
        extentTest.pass("Istanbul, Turkey chosen");

        methods.departmentSelect();
        extentTest.pass("Quality Assurance chosen");

        methods.applyButton();
        methods.windowHandles();
        assertTrue(Driver.getDriver().getCurrentUrl().contains("https://jobs.lever.co/useinsider"));
        extentTest.pass("Lever Application opened");

    }


}
