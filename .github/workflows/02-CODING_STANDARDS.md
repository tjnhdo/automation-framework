# 02. Coding Standards & Strategies (Tiêu chuẩn Coding)

## 🇺🇸 English

### 1. Locator Strategy
Always prefer semantic and stable locators. Avoid CSS/XPath tied to the layout structure.
**Priority Order:**
1. `data-testid` or `data-cy` (Highest priority, request from Devs if missing)
2. `id`
3. Stable `css` (e.g., `input[name='email']`)
4. `xpath` (Use ONLY for complex traversals like finding parent elements)

### 2. Wait Strategy
- **NEVER** use `Thread.sleep()`.
- Do not use implicit waits.
- **Always** use `WaitManager.waitFor(...)` dynamically inside action methods.

### 3. Why NO PageFactory (`@FindBy`)?
We strictly forbid the use of Selenium's `PageFactory` and `@FindBy` annotations.
- **Reason 1 (Stale Elements):** PageFactory proxies cache elements. If the DOM updates via React/Angular, the cache breaks, causing `StaleElementReferenceException`.
- **Reason 2 (Performance):** It attempts to locate elements prematurely in some configurations.
- **Our Solution:** Store only `By` locators. The framework resolves the `WebElement` via `WaitManager` at the exact millisecond you click or type.

### 4. Assertion Guidelines
- Assertions (`Assert.assertEquals`, etc.) belong in **Test Classes**, NEVER in Page Objects.
- Page Objects return data (`String`, `boolean`), Tests verify it.
- Always provide a meaningful failure message in the assertion.

---

## 🇻🇳 Tiếng Việt

### 1. Chiến lược Locator
Luôn ưu tiên các locator có ý nghĩa và ổn định. Tránh dùng CSS/XPath phụ thuộc vào cấu trúc giao diện.
**Thứ tự ưu tiên:**
1. `data-testid` hoặc `data-cy` (Ưu tiên tuyệt đối, hãy yêu cầu Dev thêm vào nếu thiếu)
2. `id`
3. `css` ổn định (VD: `input[name='email']`)
4. `xpath` (CHỈ dùng khi bắt buộc phải duyệt cây DOM phức tạp như tìm element cha)

### 2. Chiến lược Chờ (Wait Strategy)
- **KHÔNG BAO GIỜ** sử dụng `Thread.sleep()`.
- Không sử dụng Implicit Wait.
- **Luôn luôn** sử dụng `WaitManager.waitFor(...)` trực tiếp bên trong các hàm hành động.

### 3. Tại sao KHÔNG dùng PageFactory (`@FindBy`)?
Dự án nghiêm cấm sử dụng `PageFactory` và annotation `@FindBy` của Selenium.
- **Lý do 1 (Stale Elements):** PageFactory sử dụng proxy để cache (lưu tạm) element. Nếu giao diện cập nhật (React/Vue render lại), bản cache bị hỏng và gây ra lỗi `StaleElementReferenceException`.
- **Lý do 2 (Hiệu năng):** Nó có thể cố gắng tìm element quá sớm trước khi trang kịp tải xong.
- **Giải pháp của chúng ta:** Chỉ lưu trữ biến `By` locator. Framework sẽ tự động tìm `WebElement` thông qua `WaitManager` vào đúng mili-giây mà bạn thực hiện click/type.

### 4. Quy tắc Kiểm chứng (Assertion Guidelines)
- Các lệnh kiểm chứng (`Assert.assertEquals`, v.v.) chỉ được đặt trong **Test Class**, KHÔNG BAO GIỜ đặt trong Page Object.
- Page Object chỉ làm nhiệm vụ trả về dữ liệu (`String`, `boolean`), Test Class mới làm nhiệm vụ xác minh đúng sai.
- Luôn điền câu thông báo lỗi (failure message) rõ ràng vào hàm assert.