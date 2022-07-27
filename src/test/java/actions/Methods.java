package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import pages.InsiderPage;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Methods {
    InsiderPage insiderPage = new InsiderPage();

    public void locationsSelect() {
        Select locationsSelect = new Select(insiderPage.locationSelect);
        locationsSelect.selectByVisibleText("Istanbul, Turkey");
        List<WebElement> locationsSelectElement = locationsSelect.getOptions();
        int locationControl = 0;
        for (WebElement each : locationsSelectElement) {
            if (each.getText().equals("Istanbul, Turkey"))
                locationControl = 1;
        }
        assertEquals(locationControl, 1, "No value in Istanbul, Turkey");
    }

    public void departmentSelect() {
        Select departmentSelect = new Select(insiderPage.departmentSelect);
        List<WebElement> departmentSelectElement = departmentSelect.getOptions();
        int departmentControl = 0;
        for (WebElement each : departmentSelectElement) {
            if (each.getText().equals("Quality Assurance"))
                departmentControl = 1;
        }
        assertEquals(departmentControl, 1, "No value in Quality Assurance");
        departmentSelect.selectByVisibleText("Quality Assurance");
    }

    public void windowHandles() {
        Set<String> windowHandles = Driver.getDriver().getWindowHandles();
        String newWindowHandles = "";
        for (String each : windowHandles) {
            if (each != Driver.getDriver().getWindowHandle())
                newWindowHandles = each;
        }
        Driver.getDriver().switchTo().window(newWindowHandles);
    }

    public void applyButton() {
        Actions actions = new Actions(Driver.getDriver());
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        ReusableMethods.waitFor(1);
        applyButtonControl();
    }

    public void applyButtonControl() {
        Actions actions = new Actions(Driver.getDriver());
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        for (int i = 1; i < 3; i++) {
            WebElement applyButton = Driver.getDriver().
                    findElement(By.xpath("(//a[@class='btn btn-navy rounded pt-2 pr-5 pb-2 pl-5'])[" + i + "]"));
            actions.moveToElement(applyButton).perform();
            assertTrue(applyButton.isDisplayed());
        }
        WebElement applyButton = Driver.getDriver().
                findElement(By.xpath("(//a[@class='btn btn-navy rounded pt-2 pr-5 pb-2 pl-5'])[3]"));
        jse.executeScript("arguments[0].click()", applyButton);
    }
}
