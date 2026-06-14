<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Trang quản trị</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css">
    <link rel="stylesheet" href="admin/admin.css">
</head>
<body>
<div class="selection_2" id="selection_2">
    <!-- MENU BÊN TRÁI -->
    <div class="menu" id="menu">
        <div class="avata">
            <img src="images/userAvatar/${user.avatar}">
            <h3 class="username">${user.name}</h3>
            <button class="change-btn" id="btn-doi-thong-tin">Đổi thông tin</button>
        </div>

        <div class="menu_item">
            <a class="item" href="#">Quản lý sản phẩm</a>
            <a class="item" href="#">Quản lý biến thể sản phẩm</a>
            <a class="item" href="#">Quản lý voucher</a>
            <a class="item" href="#">Quản lý thông báo</a>
            <a class="item" href="#">Quản lý dịch vụ, chính sách</a>
            <a class="item" href="#">Quản lý người dùng</a>
            <a class="item" href="#">Quản lý Khóa</a>
            <a class="item" href="#">Quản lý đơn hàng</a>
            <a class="item" href="#">Trả lời câu hỏi</a>
            <a class="item" href="#">Thống kê báo cáo</a>
            <a class="item" href="#">Đăng xuất</a>
        </div>
    </div>

    <!-- KHU NỘI DUNG -->
    <div class="infomation">
        <!-- Nội dung động sẽ hiển thị ở đây -->
    </div>
    <!-- POPUP THÊM SẢN PHẨM / THÔNG BÁO -->
    <div id="adminPopup" class="popup">
        <div class="popup-content">
            <h2 id="popup-title">Tiêu đề</h2>

            <div id="popup-body">
                <!-- Nội dung popup sẽ được chèn bằng JS -->
            </div>

            <div class="popup-actions">
                <button id="popup-confirm" class="btn confirm">Xác nhận</button>
                <button id="popup-cancel" class="btn cancel">Hủy</button>
            </div>
        </div>
    </div>

</div>

<script src="admin/admin.js"></script>

</body>
</html>
