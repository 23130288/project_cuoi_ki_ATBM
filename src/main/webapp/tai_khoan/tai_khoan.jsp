<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet" href="tai_khoan/tai_khoan.css">

<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css">
</head>
<body>

<div class="selection_2" id="selection_2">
    <div class="menu" id="menu">
        <div class="avata">
            <img src="images/userAvatar/${user.avatar}" alt="${user.avatar}">
            <h3 class="username">${user.name}</h3>
            <button class="change-btn" id="btn-doi-thong-tin">Đổi thông tin</button>
        </div>
        <div class="menu_item">
            <a class="item" data-target="user-info" href="#">Thông tin tài khoản</a>
            <a class="item" data-target="notification" href="#">Thông báo</a>
            <a class="item" data-target="order" href="#">Đơn hàng</a>
            <a class="item" data-target="voucher" href="#">Voucher</a>
            <a class="item" data-target="change-password" href="#">Đổi mật khẩu</a>
            <a class="item" data-target="log-out" id="dang_xuat" href="#">Đăng xuất</a>
        </div>
    </div>
    <div class="infomation">
        <%-- -===============================- USER INFO -===============================- --%>
        <div class="information-container" id="user-info">
            <h2>Thông tin tài khoản</h2>
            <div class="in4">
                <div class="in4_row">
                    <label class="in4_label" for="email">Tên tài khoản</label>
                    <input class="in4_input" type="text" id="email" name="name" value="${user.name}" readonly>
                </div>

                <div class="in4_row">
                    <label class="in4_label" for="email">Thông tin đăng nhập</label>
                    <input class="in4_input" type="text" id="email" name="email" value="${user.email}" readonly>
                </div>

                <div class="in4_row">
                    <label class="in4_label" for="sdt">Số điện thoại *</label>
                    <input class="in4_input" type="text" id="sdt" name="sdt" placeholder="Bạn chưa nhập sdt"
                           value="${user.phone}" readonly>
                </div>

                <div class="in4_row">
                    <label class="in4_label" for="address">Địa chỉ *</label>
                    <input class="in4_input" type="text" id="address" name="address"
                           placeholder="Bạn chưa nhập địa chỉ"
                           value="${user.address}" readonly>
                </div>
            </div>
        </div>
        <%-- -===============================- NOTIFICATION -===============================- --%>
        <div class="information-container" id="notification">
            <h2>Thông báo</h2>

            <a href="../ct_Order/ct_Order.jsp" class="notification_item">
                <%--                <div class="avatar">--%>
                <%--                    <img src="image/balo_cam_trai.png" alt="Ảnh đại diện">--%>
                <%--                </div>--%>
                <div class="info">
                    <h4>Đơn hàng giao thành công</h4>
                    <p>Mã đơn hàng: hvshiodvosidjvpodjspc</p>
                    <span>10/04/2025</span>
                </div>
            </a>
        </div>
        <%-- -===============================- ORDER -===============================- --%>
        <div class="information-container" id="order">
            <div class="container" id="Order">
                <div class="tab-nav-menu">
                    <a href="#" class="tab-menu active" data-tab="all">Tất cả</a>
                    <a href="#" class="tab-menu" data-tab="delivered">Đã giao</a>
                    <a href="#" class="tab-menu" data-tab="shipping">Đang giao</a>
                    <a href="#" class="tab-menu" data-tab="cancelled">Hủy</a>
                </div>
                <div class="container-contents">
                    <div class="product-content all active">
                        <!--header-->
                        <div class="product-header">
                            <div class="product-header-title">Sản phẩm</div>
                            <div class="product-header-price">Đơn giá</div>
                            <div class="product-header-status">Tình trạng</div>
                            <div class="product-header-date">Ngày tạo</div>
                        </div>
                        <!--content-->
                        <c:forEach var="order" items="${orders}">
                            <div class="product">
                                <div class="product-title">
                                    <div class="product-info">
                                        <a href="show-order?oid=${order.oid}"><label>Đơn hàng ${order.oid}</label></a>
                                    </div>
                                </div>
                                <div class="product-price"><fmt:formatNumber value="${order.finalPrice}" type="number"
                                                                             groupingUsed="true"/> đ
                                </div>
                                <div class="product-status-${order.status}">${order.status}</div>
                                <div class="product-date">${order.createdDate}</div>
                            </div>
                        </c:forEach>
                    </div>
                    <!-- Delivered -->
                    <div class="product-content delivered">
                        <!--header-->
                        <div class="product-header">
                            <div class="product-header-title">Tiêu đề</div>
                            <div class="product-header-price">Đơn giá</div>
                            <div class="product-header-date">Ngày tạo</div>
                        </div>
                        <!--content-->
                        <div class="product">
                            <c:forEach var="order" items="${deliveredOrders}">
                                <div class="product-title">
                                    <div class="product-info">
                                        <a href="show-order?oid=${order.oid}"><label>Đơn hàng ${order.oid}</label></a>
                                    </div>
                                </div>
                                <div class="product-price"><fmt:formatNumber value="${order.finalPrice}" type="number"
                                                                             groupingUsed="true"/> đ
                                </div>
                                <div class="product-date">${order.createdDate}</div>
                            </c:forEach>
                        </div>
                    </div>
                    <!-- Shipping -->
                    <div class="product-content shipping">
                        <!--header-->
                        <div class="product-header">
                            <div class="product-header-title">Tiêu đề</div>
                            <div class="product-header-price">Đơn giá</div>
                            <div class="product-header-date">Ngày tạo</div>
                        </div>
                        <!--content-->
                        <div class="product">
                            <c:forEach var="order" items="${deliveringOrder}">
                                <div class="product-title">
                                    <div class="product-info">
                                        <a href="show-order?oid=${order.oid}"><label>Đơn hàng ${order.oid}</label></a>
                                    </div>
                                </div>
                                <div class="product-price"><fmt:formatNumber value="${order.finalPrice}" type="number"
                                                                             groupingUsed="true"/> đ
                                </div>
                                <div class="product-date">${order.createdDate}</div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="product-content cancelled">
                        <!--header-->
                        <div class="product-header">
                            <div class="product-header-title">Tiêu đề</div>
                            <div class="product-header-price">Đơn giá</div>
                            <div class="product-header-date">Ngày tạo</div>
                        </div>
                        <!--content-->
                        <div class="product">
                            <c:forEach var="order" items="${canceledOrder}">
                                <div class="product-title">
                                    <div class="product-info">
                                        <a href="show-order?oid=${order.oid}"><label>Đơn hàng ${order.oid}</label></a>
                                    </div>
                                </div>
                                <div class="product-price"><fmt:formatNumber value="${order.finalPrice}" type="number"
                                                                             groupingUsed="true"/> đ
                                </div>
                                <div class="product-date">${order.createdDate}</div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%-- -===============================- VOUCHER -===============================- --%>
        <div class="information-container" id="voucher">
            <div class="container" id="Voucher">
                <div class="tab-nav-menu">
                    <a href="#" class="tab-menu active" data-tab="all">Tất cả</a>
                    <a href="#" class="tab-menu" data-tab="free">Miễn phí vận chuyển</a>
                    <a href="#" class="tab-menu" data-tab="percent">Giảm theo %</a>
                    <a href="#" class="tab-menu" data-tab="value">Giảm theo giá trị</a>
                </div>

                <div class="container-contents">

                    <!-- Tất cả -->
                    <div class="product-content all active">
                        <div class="product-header">
                            <div class="product-header-title">Voucher</div>
                            <div class="product-header-price">Nội dung</div>
                            <div class="product-header-date">Hạn sử dụng</div>
                        </div>

                        <div class="product">
                            <div class="product-title">
                                <img src="image/vpucher_free_ship.png" alt="">
                            </div>
                            <div class="product-price">Miễn phí vận chuyển</div>
                            <div class="product-date">01/12/2025</div>
                        </div>

                    </div>

                    <!-- Miễn phí vận chuyển -->
                    <div class="product-content free">
                        <div class="product-header">
                            <div class="product-header-title">Tiêu đề</div>
                            <div class="product-header-price">Nội dung</div>
                            <div class="product-header-date">Hạn sử dụng</div>
                        </div>

                        <div class="product">
                            <div class="product-title">
                                <img src="image/vpucher_free_ship.png" alt="">
                            </div>
                            <div class="product-price">Miễn phí vận chuyển đơn từ 0đ</div>
                            <div class="product-date">01/12/2025</div>
                        </div>
                    </div>

                    <!-- Giảm theo % -->
                    <div class="product-content percent">
                        <div class="product-header">
                            <div class="product-header-title">Tiêu đề</div>
                            <div class="product-header-price">Nội dung</div>
                            <div class="product-header-date">Hạn sử dụng</div>
                        </div>

                        <div class="product">
                            <div class="product-title">
                                <img src="image/voucer_percent.png" alt="">
                            </div>
                            <div class="product-price">Giảm 5% cho đơn trên 100k</div>
                            <div class="product-date">01/12/2025</div>
                        </div>
                    </div>

                    <!-- Giảm theo giá trị -->
                    <div class="product-content value">
                        <div class="product-header">
                            <div class="product-header-title">Tiêu đề</div>
                            <div class="product-header-price">Nội dung</div>
                            <div class="product-header-date">Hạn sử dụng</div>
                        </div>

                        <div class="product">
                            <div class="product-title">
                                <img src="image/voucher_50k.png" alt="">
                            </div>
                            <div class="product-price">Giảm 20.000đ cho đơn từ 150k</div>
                            <div class="product-date">01/12/2025</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%-- -===============================- CHANGE PASSWORD -===============================- --%>
        <div class="information-container" id="change-password">
            <h2>Đổi mật khẩu</h2>
            <div class="in4">
                <div class="in4_row">
                    <label class="in4_label" for="old_password">Mật khẩu cũ</label>
                    <div class="password-wrapper">
                        <input class="in4_input" id="old_password" type="password" name="old_password">
                        <button type="button" id="toggleOldPassword">👁</button>
                    </div>
                </div>

                <div class="in4_row">
                    <label class="in4_label" for="password">Mật khẩu mới</label>
                    <div class="password-wrapper">
                        <input class="in4_input" id="password" type="password" name="password">
                        <button type="button" id="togglePassword">👁</button>
                    </div>
                </div>

                <div class="in4_row">
                    <label class="in4_label" for="confirm_password">Xác nhận mật khẩu mới</label>
                    <div class="password-wrapper">
                        <input class="in4_input" id="confirm_password" type="password" name="confirm_password">
                        <button type="button" id="toggleConfirmPassword">👁</button>
                    </div>
                </div>
                <div class="in4_row">
                    <button class="bt_xac_nhan" id="btn-doi-mk">Xác nhận</button>
                </div>
            </div>
        </div>
        <%-- -===============================- LOG OUT -===============================- --%>
        <div class="information-container" id="log-out">
            <h2>Đăng xuất</h2>
            <p>Bạn có chắc muốn đăng xuất không?</p>
            <button class="bt_xac_nhan" id="btn-dang-xuat">Đăng xuất</button>
        </div>
    </div>
</div>
<!-- Popup đổi thông tin -->
<div id="adminPopup" class="popup">
    <div class="popup-content">
        <h2 id="popup-title">Tiêu đề</h2>

        <div id="popup-body">

        </div>

        <div class="popup-actions">
            <button id="popup-confirm" class="btn confirm">Xác nhận</button>
            <button id="popup-cancel" class="btn cancel">Hủy</button>
        </div>
    </div>
</div>
<script src="tai_khoan/tai_khoan.js"></script>

</body>
</html>