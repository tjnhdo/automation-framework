# 04. Debugging & CI Troubleshooting (Debug & Xử lý lỗi CI)

## 🇺🇸 English

### 1. The CI Pipeline
Our framework runs on GitHub Actions automatically upon Push/Pull Request. 
If a test fails on CI, **do not immediately re-run it**. Investigate the artifacts.

### 2. How to Investigate a Failed Test
Thanks to `TestListener` and `AllureManager`, every failure captures:
1. **Screenshot**: See what the UI looked like at the moment of failure.
2. **DOM Snapshot**: Inspect the HTML structure. Useful if locators changed.
3. **Browser Console Logs**: Check for JavaScript errors or 500 Network errors.
4. **Current URL**: Check if the framework navigated to an unexpected page.

**Steps to view:**
1. Download the `allure-html-report` zip from the GitHub Actions run.
2. Open `index.html`.
3. Navigate to the failed test and expand the attachments.

### 3. Handling Flaky Tests
If a test passes locally but fails randomly on CI:
- **Do not add `Thread.sleep`**. 
- Check if the application renders components dynamically. Use `WaitManager` to explicitly wait for an element's visibility or invisibility (e.g., waiting for a loading spinner to disappear).
- Check the `WAIT_TIMEOUT` environment variable. CI servers are slower; you may need to increase it via `-DWAIT_TIMEOUT=30`.

---

## 🇻🇳 Tiếng Việt

### 1. Luồng chạy CI (CI Pipeline)
Framework tự động chạy trên GitHub Actions mỗi khi có Push hoặc Pull Request.
Nếu test thất bại trên CI, **tuyệt đối không ấn chạy lại ngay lập tức (re-run)**. Hãy kiểm tra các file báo cáo trước.

### 2. Cách điều tra một Test bị lỗi (Failed Test)
Nhờ có `TestListener` và `AllureManager`, mỗi khi test fail hệ thống sẽ chụp lại:
1. **Screenshot (Ảnh màn hình)**: Xem giao diện lúc bị lỗi trông thế nào.
2. **DOM Snapshot (Mã HTML)**: Kiểm tra cấu trúc HTML. Rất hữu ích nếu Dev đổi tên class/id khiến locator bị sai.
3. **Browser Console Logs**: Kiểm tra xem UI có bị lỗi JavaScript hay lỗi gọi API (500) không.
4. **Current URL**: Kiểm tra xem trình duyệt có vô tình nhảy sang nhầm trang khác không.

**Cách xem:**
1. Tải file zip `allure-html-report` từ GitHub Actions artifact.
2. Mở file `index.html`.
3. Tìm đến test bị fail và bấm vào các file đính kèm.

### 3. Xử lý Test chập chờn (Flaky Tests)
Nếu test chạy ở máy bạn thì xanh (pass) nhưng lên CI lại đỏ (fail) ngẫu nhiên:
- **Cấm dùng `Thread.sleep`**.
- Kiểm tra xem UI có đang load dữ liệu ngầm không. Hãy dùng `WaitManager` để chờ rõ ràng cho đến khi một element hiển thị (visibility) hoặc biến mất (invisibility - VD: chờ biến mất cái vòng quay loading).
- Server CI thường chậm hơn máy local. Thử tăng biến môi trường timeout lên bằng lệnh `-DWAIT_TIMEOUT=30`.