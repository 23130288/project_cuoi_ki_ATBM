<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Footer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/shareStuff/footer/footer.css">
</head>
<body>
<footer>
    <div class="footer1" id="footer1">
        <div class="footer1-container">
            <div class="footer1-column">
                <h4>Cửa hàng Travel</h4>
                <a href="trangChu">Balo & Vali Lịch</a>
            </div>

            <div class="footer1-column">
                <h4>Dịch vụ khách hàng</h4>
                <a href="dich_vu?spid=12">Dịch vụ giao hàng</a>
                <a href="dich_vu?spid=10">Dịch vụ đổi trả</a>
            </div>

            <div class="footer1-column">
                <h4>Liên hệ admin</h4>
                <a href="support">Liên hệ</a>
            </div>

            <div class="footer1-column">
                <h4>Điều khoản pháp lý</h4>
                <a href="chinh_sach?spid=5">Chính sách bảo mật</a>
                <a href="chinh_sach?spid=6">Chính sách bảo hành</a>
                <a href="chinh_sach?spid=7">Điều khoản sử dụng</a>
            </div>
        </div>
    </div>

    <div class="footer2" id="footer2">
        <div class="socials">
            <a href="https://www.facebook.com/groups/congdongsinhvien.hcmnlu">Tiktok</a>
            <a href="https://www.facebook.com/groups/congdongsinhvien.hcmnlu">Instagram</a>
            <a href="https://www.facebook.com/groups/congdongsinhvien.hcmnlu">Facebook</a>
            <a href="https://www.facebook.com/groups/congdongsinhvien.hcmnlu">Pinterest</a>
        </div>

        <div class="brand">
            <img src="${pageContext.request.contextPath}/shareStuff/footer/image/logo_white.png">
            <p>© 2025 Công ty Balo & Vali Du lịch Travel</p>
        </div>
    </div>
</footer>
</body>
</html>