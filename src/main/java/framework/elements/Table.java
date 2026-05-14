package framework.elements;

import framework.driver.DriverManager;
import framework.sync.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Table {
    private final By locator;

    public Table(By locator) {
        this.locator = locator;
    }

    private WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    public int getRowCount() {
        return WaitManager.waitForVisible(getDriver(), locator).findElements(By.tagName("tr")).size();
    }

    public String getCellText(int rowIndex, int columnIndex) {
        return WaitManager.waitFor(getDriver(), d -> {
            List<WebElement> rows = WaitManager.waitForVisible(d, locator).findElements(By.tagName("tr"));
            if (rowIndex < 0 || rowIndex >= rows.size()) {
                throw new IndexOutOfBoundsException("Row index is out of range: " + rowIndex);
            }
            List<WebElement> cells = rows.get(rowIndex).findElements(By.tagName("td"));
            return cells.get(columnIndex).getText();
        });
    }
}
