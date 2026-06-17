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
<jsp:include page="../shareStuff/header/header.jsp"/>
<div class="selection_2" id="selection_2">
    <div class="menu" id="menu">
        <div class="avata">
            <img src="${user.avatar}" alt="${user.avatar}">
            <h3 class="username">${user.name}</h3>
        </div>
        <div class="menu_item">
            <a class="item" data-target="user-info" href="#">Thông tin tài khoản</a>
            <a class="item" data-target="key" href="#">Khóa</a>
            <a class="item" data-target="notification" href="#">Thông báo</a>
            <a class="item" data-target="order" href="#">Đơn hàng</a>
            <a class="item" data-target="voucher" href="#">Voucher</a>
            <a class="item" data-target="support" href="#">Hỗ trợ</a>
            <a class="item" data-target="change-password" href="#">Đổi mật khẩu</a>
            <a class="item" data-target="log-out" id="dang_xuat" href="#">Đăng xuất</a>
        </div>
    </div>
    <div class="infomation">
        <%-- -===============================- USER INFO -===============================- --%>
        <div class="information-container" id="user-info">
            <h2>Thông tin tài khoản</h2>
            <form action="change-user-information" method="post" id="userForm">
                <div class="in4">
                    <div class="in4_row">
                        <label class="in4_label" for="email">Thông tin đăng nhập</label>
                        <input class="in4_input" type="email" id="email" name="email" value="${user.email}" readonly>
                    </div>

                    <div class="in4_row">
                        <label class="in4_label" for="name">Tên tài khoản</label>
                        <input class="in4_input info2" type="text" id="name" name="name" value="${user.name}" readonly>
                    </div>

                    <div class="in4_row">
                        <label class="in4_label" for="sdt">Số điện thoại *</label>
                        <input class="in4_input info2" type="text" id="sdt" name="sdt" placeholder="Bạn chưa nhập sdt"
                               value="${user.phone}" inputmode="numeric" pattern="[0-9]*" maxlength="11" readonly>
                    </div>

                    <div class="in4_row">
                        <label class="in4_label" for="address">Địa chỉ *</label>
                        <input class="in4_input info2" type="text" id="address" name="address"
                               placeholder="Bạn chưa nhập địa chỉ"
                               value="${user.address}" readonly>
                    </div>
                </div>
                <div class="btn-group">
                    <button type="button" class="change-btn" id="btn-edit">Đổi thông tin</button>
                    <button type="submit" class="save-btn" id="btn-save">Lưu</button>
                    <button type="button" class="cancel-btn" id="btn-cancel">Hủy</button>
                </div>
            </form>
        </div>
        <%-- -===============================- KEY -===============================- --%>
        <div class="information-container" id="key">
            <h2>Khóa</h2>
            <div class="container">
                <div class="container-key">
                    <label class="key-lbl">Khóa </label>
                    <c:choose>
                        <c:when test="${empty key}">
                            <input class="key" type="text" id="current-key" value="..." readonly>
                        </c:when>
                        <c:otherwise>
                            <input class="key" type="text" id="current-key" value="${key.publicKeyStr}" readonly>
                        </c:otherwise>
                    </c:choose>
                </div>
                <c:choose>
                    <c:when test="${canUpKey}">
                        <form action="change-key" id="upload-key-form" method="post" enctype="multipart/form-data">
                            <div class="container-key">
                                <input type="hidden" name="uid" value="${user.uid}">
                                <label class="up-key">Upload khoá </label>
                                <input type="file" id="new-key" name="new-key" required>
                                <button type="submit" id="up-new-key">Upload</button>
                            </div>
                        </form>
                        <div id="afterMessage">
                            <h3>Upload khóa thành công</h3>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="container-key">
                            <label class="report-missing-key">Báo mất khóa </label>
                            <button type="button" class="show-report-missing" id="show-report-missing">Mất khóa</button>
                            <div class="report-missing" id="report-missing">
                                <div class="report-missing-box" id="report-missing">
                                    <h2>Báo mất khóa</h2>
                                    <form action="support-request-change-key" id="report-missing-key-form"
                                          method="post">
                                        <input type="hidden" name="uid" value="${user.uid}">
                                        <textarea rows=3 placeholder="Hãy mô tả vấn đề của bạn tại đây..."
                                                  name="message"></textarea>

                                        <button type="button" class="close-report-missing" id="close-report-missing">
                                            Hủy
                                        </button>
                                        <button type="submit" class="report-missing-key-btn"
                                                id="report-missing-key-btn">Báo cáo
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div id="afterMessage">
                            <h3>Yêu cầu của bạn đã được gửi.</h3>
                            <p>Vui lòng chờ admin hỗ trợ.</p>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <%-- -===============================- NOTIFICATION -===============================- --%>
        <div class="information-container" id="notification">
            <h2>Thông báo</h2>
            <div class="container">
                <div class="products">
                    <c:forEach var="n" items="${notifications}">
                        <div class="notification_item ${!n.isRead ? 'unread' : ''}" data-nid="${n.nid}">
                            <div class="info">
                                <h4>${n.title}</h4>
                                <p>${n.content}</p>
                                <span>${n.receivedDate}</span>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <%-- -===============================- ORDER -===============================- --%>
        <div class="information-container" id="order">
            <div class="container" id="Order">
                <div class="tab-nav-menu">
                    <a href="#" class="tab-menu active" data-tab="all">Tất cả</a>
                    <a href="#" class="tab-menu" data-tab="delivered">Đã giao</a>
                    <a href="#" class="tab-menu" data-tab="shipping">Đang giao</a>
                    <a href="#" class="tab-menu" data-tab="waiting">Đang chờ</a>
                    <a href="#" class="tab-menu" data-tab="cancelled">Hủy</a>
                </div>
                <div class="container-contents">
                    <%-- All --%>
                    <div class="product-content all active">
                        <!--header-->
                        <div class="product-header">
                            <div class="product-header-title">Sản phẩm</div>
                            <div class="product-header-price">Đơn giá</div>
                            <div class="product-header-date">Ngày tạo</div>
                            <div class="product-header-status">Tình trạng</div>
                            <div class="product-header-sign-status">Ký</div>
                        </div>
                        <!--content-->
                        <div class="products">
                            <c:forEach var="order" items="${orders}">
                                <div class="product">
                                    <div class="product-title">
                                        <a href="show-order?oid=${order.oid}"><label>Đơn hàng ${order.oid}</label></a>
                                    </div>
                                    <div class="product-price"><fmt:formatNumber value="${order.finalPrice}"
                                                                                 type="number"
                                                                                 groupingUsed="true"/> đ
                                    </div>
                                    <div class="product-date">${order.createdDate}</div>
                                    <div class="product-status ${order.status}">${order.status}</div>
                                    <div class="product-sign-status">
                                        <c:choose>
                                            <c:when test="${order.changed}">
                                                <c:if test="${order.signStatus}">
                                                    <i class="fa-solid fa-circle-check signed"></i>Đã ký<br>
                                                </c:if>
                                                <i class="fa-solid fa-triangle-exclamation text-warning"></i>Có thay đổi
                                            </c:when>
                                            <c:when test="${order.signStatus}">
                                                <i class="fa-solid fa-circle-check signed"></i>Đã ký
                                            </c:when>
                                            <c:otherwise>
                                                <i class="fa-solid fa-circle-xmark unsigned"></i>Chưa ký
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <!-- Delivered -->
                    <div class="product-content delivered">
                        <!--header-->
                        <div class="product-header">
                            <div class="product-header-title">Tiêu đề</div>
                            <div class="product-header-price">Đơn giá</div>
                            <div class="product-header-date">Ngày tạo</div>
                            <div class="product-header-sign-status">Ký</div>
                        </div>
                        <!--content-->
                        <div class="products">
                            <c:forEach var="order" items="${orders}">
                                <c:if test="${order.status == 'delivered'}">
                                    <div class="product">
                                        <div class="product-title">
                                            <div class="product-info">
                                                <a href="show-order?oid=${order.oid}"><label>Đơn
                                                    hàng ${order.oid}</label></a>
                                            </div>
                                        </div>
                                        <div class="product-price"><fmt:formatNumber value="${order.finalPrice}"
                                                                                     type="number"
                                                                                     groupingUsed="true"/> đ
                                        </div>
                                        <div class="product-date">${order.createdDate}</div>
                                        <div class="product-sign-status">
                                            <c:choose>
                                                <c:when test="${order.changed}">
                                                    <c:if test="${order.signStatus}">
                                                        <i class="fa-solid fa-circle-check signed"></i>Đã ký<br>
                                                    </c:if>
                                                    <i class="fa-solid fa-triangle-exclamation text-warning"></i>Có thay đổi
                                                </c:when>
                                                <c:when test="${order.signStatus}">
                                                    <i class="fa-solid fa-circle-check signed"></i>Đã ký
                                                </c:when>
                                                <c:otherwise>
                                                    <i class="fa-solid fa-circle-xmark unsigned"></i>Chưa ký
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                    <!-- Delivering -->
                    <div class="product-content shipping">
                        <!--header-->
                        <div class="product-header">
                            <div class="product-header-title">Tiêu đề</div>
                            <div class="product-header-price">Đơn giá</div>
                            <div class="product-header-date">Ngày tạo</div>
                            <div class="product-header-sign-status">Ký</div>
                        </div>
                        <!--content-->
                        <div class="products">
                            <c:forEach var="order" items="${orders}">
                                <c:if test="${order.status == 'delivering'}">
                                    <div class="product">
                                        <div class="product-title">
                                            <div class="product-info">
                                                <a href="show-order?oid=${order.oid}"><label>Đơn
                                                    hàng ${order.oid}</label></a>
                                            </div>
                                        </div>
                                        <div class="product-price"><fmt:formatNumber value="${order.finalPrice}"
                                                                                     type="number"
                                                                                     groupingUsed="true"/> đ
                                        </div>
                                        <div class="product-date">${order.createdDate}</div>
                                        <div class="product-sign-status">
                                            <c:choose>
                                                <c:when test="${order.changed}">
                                                    <c:if test="${order.signStatus}">
                                                        <i class="fa-solid fa-circle-check signed"></i>Đã ký<br>
                                                    </c:if>
                                                    <i class="fa-solid fa-triangle-exclamation text-warning"></i>Có thay đổi
                                                </c:when>
                                                <c:when test="${order.signStatus}">
                                                    <i class="fa-solid fa-circle-check signed"></i>Đã ký
                                                </c:when>
                                                <c:otherwise>
                                                    <i class="fa-solid fa-circle-xmark unsigned"></i>Chưa ký
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                    <!-- Waiting -->
                    <div class="product-content waiting">
                        <!--header-->
                        <div class="product-header">
                            <div class="product-header-title">Tiêu đề</div>
                            <div class="product-header-price">Đơn giá</div>
                            <div class="product-header-date">Ngày tạo</div>
                            <div class="product-header-sign-status">Ký</div>
                        </div>
                        <!--content-->
                        <div class="products">
                            <c:forEach var="order" items="${orders}">
                                <c:if test="${order.status == 'waiting'}">
                                    <div class="product">
                                        <div class="product-title">
                                            <div class="product-info">
                                                <a href="show-order?oid=${order.oid}"><label>Đơn
                                                    hàng ${order.oid}</label></a>
                                            </div>
                                        </div>
                                        <div class="product-price"><fmt:formatNumber value="${order.finalPrice}"
                                                                                     type="number"
                                                                                     groupingUsed="true"/> đ
                                        </div>
                                        <div class="product-date">${order.createdDate}</div>
                                        <div class="product-sign-status">
                                            <c:choose>
                                                <c:when test="${order.changed}">
                                                    <c:if test="${order.signStatus}">
                                                        <i class="fa-solid fa-circle-check signed"></i>Đã ký<br>
                                                    </c:if>
                                                    <i class="fa-solid fa-triangle-exclamation text-warning"></i>Có thay đổi
                                                </c:when>
                                                <c:when test="${order.signStatus}">
                                                    <i class="fa-solid fa-circle-check signed"></i>Đã ký
                                                </c:when>
                                                <c:otherwise>
                                                    <i class="fa-solid fa-circle-xmark unsigned"></i>Chưa ký
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                    <!-- Cancelled -->
                    <div class="product-content cancelled">
                        <!--header-->
                        <div class="product-header">
                            <div class="product-header-title">Tiêu đề</div>
                            <div class="product-header-price">Đơn giá</div>
                            <div class="product-header-date">Ngày tạo</div>
                            <div class="product-header-sign-status">Ký</div>
                        </div>
                        <!--content-->
                        <div class="products">
                            <c:forEach var="order" items="${orders}">
                                <c:if test="${order.status == 'cancelled'}">
                                    <div class="product">
                                        <div class="product-title">
                                            <div class="product-info">
                                                <a href="show-order?oid=${order.oid}"><label>Đơn
                                                    hàng ${order.oid}</label></a>
                                            </div>
                                        </div>
                                        <div class="product-price"><fmt:formatNumber value="${order.finalPrice}"
                                                                                     type="number"
                                                                                     groupingUsed="true"/> đ
                                        </div>
                                        <div class="product-date">${order.createdDate}</div>
                                        <div class="product-sign-status">
                                            <c:choose>
                                                <c:when test="${order.changed}">
                                                    <c:if test="${order.signStatus}">
                                                        <i class="fa-solid fa-circle-check signed"></i>Đã ký<br>
                                                    </c:if>
                                                    <i class="fa-solid fa-triangle-exclamation text-warning"></i>Có thay đổi
                                                </c:when>
                                                <c:when test="${order.signStatus}">
                                                    <i class="fa-solid fa-circle-check signed"></i>Đã ký
                                                </c:when>
                                                <c:otherwise>
                                                    <i class="fa-solid fa-circle-xmark unsigned"></i>Chưa ký
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </c:if>
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
                        <div class="products">
                            <c:forEach var="v" items="${vouchers}">
                                <div class="product">
                                    <div class="product-title">
                                        <img src="${v.image}" alt="${v.name}">
                                    </div>
                                    <div class="product-price">
                                        <c:choose>
                                            <c:when test="${v.name == 'phan_tram'}">
                                                Giảm <fmt:formatNumber value="${v.discount * 100}"
                                                                       maxFractionDigits="0"/> %
                                            </c:when>
                                            <c:when test="${v.name == 'giam_gia'}">
                                                Giảm <fmt:formatNumber value="${v.discount}" type="number"
                                                                       groupingUsed="true"/> đ
                                            </c:when>
                                            <c:otherwise>
                                                Miễn phí vận chuyển
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div class="product-date">${v.expiredDate}</div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>

                    <!-- Miễn phí vận chuyển -->
                    <div class="product-content free">
                        <div class="product-header">
                            <div class="product-header-title">Tiêu đề</div>
                            <div class="product-header-price">Nội dung</div>
                            <div class="product-header-date">Hạn sử dụng</div>
                        </div>

                        <div class="products">
                            <c:forEach var="v" items="${vouchers}">
                                <c:if test="${v.name == 'mien_phi_van_chuyen'}">
                                    <div class="product">
                                        <div class="product-title">
                                            <img src="${v.image}" alt="${v.name}">
                                        </div>
                                        <div class="product-price">
                                            Miễn phí vận chuyển
                                        </div>
                                        <div class="product-date">${v.expiredDate}</div>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>

                    <!-- Giảm theo % -->
                    <div class="product-content percent">
                        <div class="product-header">
                            <div class="product-header-title">Tiêu đề</div>
                            <div class="product-header-price">Nội dung</div>
                            <div class="product-header-date">Hạn sử dụng</div>
                        </div>

                        <div class="products">
                            <c:forEach var="v" items="${vouchers}">
                                <c:if test="${v.name == 'phan_tram'}">
                                    <div class="product">
                                        <div class="product-title">
                                            <img src="${v.image}" alt="${v.name}">
                                        </div>
                                        <div class="product-price">
                                            Giảm <fmt:formatNumber value="${v.discount * 100}" maxFractionDigits="0"/> %
                                        </div>
                                        <div class="product-date">${v.expiredDate}</div>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>

                    <!-- Giảm theo giá trị -->
                    <div class="product-content value">
                        <div class="product-header">
                            <div class="product-header-title">Tiêu đề</div>
                            <div class="product-header-price">Nội dung</div>
                            <div class="product-header-date">Hạn sử dụng</div>
                        </div>

                        <div class="products">
                            <c:forEach var="v" items="${vouchers}">
                                <c:if test="${v.name == 'giam_gia'}">
                                    <div class="product">
                                        <div class="product-title">
                                            <img src="${v.image}" alt="${v.name}">
                                        </div>
                                        <div class="product-price">
                                            Giảm <fmt:formatNumber value="${v.discount}" type="number"
                                                                   groupingUsed="true"/> đ
                                        </div>
                                        <div class="product-date">${v.expiredDate}</div>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%-- -===============================- SUPPORT -===============================- --%>
        <div class="information-container" id="support">
            <div class="container" id="Order">
                <div class="tab-nav-menu">
                    <a href="#" class="tab-menu active" data-tab="all">Tất cả</a>
                    <a href="#" class="tab-menu" data-tab="processing">Đang chờ xử lý</a>
                    <a href="#" class="tab-menu" data-tab="done">Xử lý xong</a>
                </div>
                <div class="container-contents">
                    <h2>Hỗ trợ</h2>
                    <div class="product-content all active">
                        <!--content-->
                        <div class="products">
                            <c:forEach var="support" items="${supports}">
                                <a href="show-support?spid=${support.spid}" class="notification_item">
                                    <div class="info">
                                        <h4>${support.title}</h4>
                                        <p>${support.topic}</p>
                                        <span>${support.createdDate} - ${support.status}</span>
                                    </div>
                                </a>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="product-content processing">
                        <!--content-->
                        <div class="products">
                            <c:forEach var="support" items="${supports}">
                                <c:if test="${support.status == 'processing'}">
                                    <a href="show-support?spid=${support.spid}" class="notification_item">
                                        <div class="info">
                                            <h4>${support.title}</h4>
                                            <p>${support.topic}</p>
                                            <span>${support.createdDate} - ${support.status}</span>
                                        </div>
                                    </a>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>

                    <div class="product-content done">
                        <!--content-->
                        <div class="products">
                            <c:forEach var="support" items="${supports}">
                                <c:if test="${support.status == 'done'}">
                                    <a href="show-support?spid=${support.spid}" class="notification_item">
                                        <div class="info">
                                            <h4>${support.title}</h4>
                                            <p>${support.topic}</p>
                                            <span>${support.createdDate} - ${support.status}</span>
                                        </div>
                                    </a>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%-- -===============================- CHANGE PASSWORD -===============================- --%>
        <div class="information-container" id="change-password">
            <h2>Đổi mật khẩu</h2>
            <form class="in4" method="post" action="change-password">
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
                <div class="in4_row" id="password-info-button">
                    <c:if test="${not empty sessionScope.error}">
                        <p class="password-error">${sessionScope.error}</p>
                        <c:remove var="error" scope="session"/>
                    </c:if>

                    <c:if test="${not empty sessionScope.success}">
                        <p class="password-success">${sessionScope.success}</p>
                        <c:remove var="success" scope="session"/>
                    </c:if>
                    <button class="bt_xac_nhan" id="btn-doi-mk">Xác nhận</button>
                </div>
            </form>
        </div>

        <%-- -===============================- LOG OUT -===============================- --%>
        <div class="information-container" id="log-out">
            <h2>Đăng xuất</h2>
            <p>Bạn có chắc muốn đăng xuất không?</p>
            <form method="post" action="log-out">
                <button class="bt_xac_nhan" id="btn-dang-xuat">Đăng xuất</button>
            </form>
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
<jsp:include page="../shareStuff/footer/footer.jsp"/>
<script src="tai_khoan/tai_khoan.js"></script>
</body>
</html>