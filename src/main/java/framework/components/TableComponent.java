package framework.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

/**
 * Component đại diện cho một Bảng dữ liệu (Data Grid/Table).
 * Tuân thủ quy tắc Dynamic Resolution: Quét lại DOM mỗi khi được gọi, không cache list WebElement.
 */
public class TableComponent extends BaseComponent {

    private final By rowLocator = By.cssSelector("tbody tr");
    private final By cellLocator = By.cssSelector("td");
    private final By headerLocator = By.cssSelector("thead th");

    public TableComponent(By parentLocator) {
        super(parentLocator);
    }

    /**
     * Lấy text của một ô dựa vào số thứ tự dòng và cột (index bắt đầu từ 0)
     */
    public String getCellText(int rowIndex, int colIndex) {
        List<WebElement> rows = getChildElements(rowLocator);
        if (rowIndex >= rows.size()) {
            throw new IllegalArgumentException("Dòng " + rowIndex + " không tồn tại trong bảng.");
        }
        
        List<WebElement> cells = rows.get(rowIndex).findElements(cellLocator);
        if (colIndex >= cells.size()) {
            throw new IllegalArgumentException("Cột " + colIndex + " không tồn tại.");
        }
        
        return cells.get(colIndex).getText();
    }

    /**
     * Ví dụ thực tế: Lấy trạng thái của một đơn hàng dựa vào Tên/ID nằm ở cột đầu tiên.
     */
    public String getStatusByOrderName(String orderName, int statusColumnIndex) {
        // Thay vì duyệt List<WebElement> gây nguy cơ StaleElementReferenceException, ta dùng Dynamic XPath
        By dynamicCellLocator = By.xpath(String.format(".//tr[td[normalize-space(text())='%s']]/td[%d]", orderName, statusColumnIndex + 1));
        return getChildElement(dynamicCellLocator).getText();
    }
}