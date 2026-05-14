# 01. Architecture & Core Concepts (Kiến trúc & Khái niệm lõi)

## 🇺🇸 English

### 1. Framework Philosophy
The core architecture is built around predictability. We do not use magic wrappers or hidden retries.
- **Stateless Utility:** Utilities like `WaitManager` are static and stateless.
- **Dynamic Resolution:** Elements are always re-resolved right before interaction to completely eliminate `StaleElementReferenceException`.
- **Thread-Safety First:** WebDriver instances are strictly managed via `ThreadLocal` in `DriverManager`.

### 2. Folder Structure
- `framework/base/`: Contains `BasePage`. This is the only place that should handle explicit interactions.
- `framework/components/`: Reusable UI widgets (e.g., `PaginationComponent`). They must NEVER hold a WebDriver instance.
- `framework/config/`: Environment and Browser configurations.
- `framework/driver/`: WebDriver factory and ThreadLocal management.
- `framework/reporting/`: Allure integration and Screenshot utilities.
- `framework/sync/`: The heart of synchronization (`WaitManager`).
- `tests/`: Where the actual TestNG test classes live.

### 3. Execution Flow
1. `BaseUiTest.setUp()`: Initializes `WebDriver` for the current thread via `DriverManager.initDriver()`.
2. **Test Method**: Instantiates Page Objects. Page Objects ONLY store `By` locators, not `WebElements`.
3. **Interaction**: Calling `page.click()` invokes `BasePage`, which safely waits and acts using a lambda function.
4. `TestListener.onTestFailure()`: If a test fails, it captures the Screenshot, URL, DOM source, and Browser Console Logs, attaching them to Allure.
5. `BaseUiTest.tearDown()`: Safely quits the driver.

### 4. Thread Safety Rule
**NEVER** pass `WebDriver` into a Page Object or Component constructor.
**NEVER** assign `WebDriver driver = ...` as a class-level variable in your tests or components.
Always use `DriverManager.getDriver()` dynamically.

---

## 🇻🇳 Tiếng Việt

### 1. Triết lý Kiến trúc
Kiến trúc cốt lõi được xây dựng dựa trên sự dễ đoán (predictability). Chúng tôi không sử dụng các lớp bọc ma thuật (magic wrappers) hay hệ thống retry ngầm.
- **Tiện ích phi trạng thái (Stateless):** Các class như `WaitManager` hoàn toàn là static và không lưu trữ trạng thái.
- **Phân giải động (Dynamic Resolution):** Các Element luôn được tìm lại (re-resolve) ngay trước khoảnh khắc tương tác. Điều này triệt tiêu 100% lỗi `StaleElementReferenceException`.
- **An toàn luồng (Thread-Safety First):** WebDriver được quản lý nghiêm ngặt qua `ThreadLocal` bên trong `DriverManager`.

### 2. Cấu trúc thư mục
- `framework/base/`: Chứa `BasePage`. Đây là nơi duy nhất trực tiếp tương tác với các hàm cơ bản (click, type).
- `framework/components/`: Chứa các Component dùng chung (VD: `PaginationComponent`). Các class này TUYỆT ĐỐI KHÔNG lưu trữ WebDriver.
- `framework/config/`: Cấu hình môi trường (URL) và Trình duyệt.
- `framework/driver/`: Nơi khởi tạo WebDriver và quản lý ThreadLocal.
- `framework/reporting/`: Tích hợp báo cáo Allure và chụp ảnh màn hình.
- `framework/sync/`: Trái tim của hệ thống đồng bộ hóa (`WaitManager`).
- `tests/`: Nơi chứa các class TestNG kiểm thử thực tế.

### 3. Luồng thực thi (Execution Flow)
1. `BaseUiTest.setUp()`: Khởi tạo `WebDriver` cho luồng hiện tại thông qua `DriverManager.initDriver()`.
2. **Test Method**: Khởi tạo các Page Object. Page Object CHỈ ĐƯỢC lưu `By` locators, không được lưu `WebElement`.
3. **Tương tác (Interaction)**: Gọi `page.click()` sẽ đi qua `BasePage`, tại đây nó được bọc trong một hàm lambda để chờ và click an toàn.
4. `TestListener.onTestFailure()`: Nếu test thất bại, hệ thống tự động chụp Ảnh màn hình, URL, Mã nguồn HTML (DOM) và Log trình duyệt rồi gắn vào báo cáo Allure.
5. `BaseUiTest.tearDown()`: Đóng trình duyệt an toàn.

### 4. Quy tắc An toàn luồng (Thread Safety)
**KHÔNG BAO GIỜ** truyền `WebDriver` vào constructor của Page Object hay Component.
**KHÔNG BAO GIỜ** gán `WebDriver driver = ...` thành biến instance ở cấp độ class.
Luôn luôn gọi trực tiếp `DriverManager.getDriver()` bên trong hàm khi cần sử dụng.