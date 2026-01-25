<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hóa đơn</title>
    <link rel="stylesheet" href="ct_Order/ct_Order.css">
</head>
<body>
<main>
    <div class="container">
        <h2 class="thanks-message">Cảm ơn bạn đã thanh toán</h2>
        <h3 class="title">Đơn hàng #${order.oid}</h3>
        <p class="date">${order.createdDate}</p>
        <div class="customer-info">
            <p class="customer-name">${user.name}</p>
        </div>
        <div class="product-info">
            <div class="product-header">
                <div class="product-header-content" id="product-header-title">Sản phẩm</div>
                <div class="product-header-content" id="product-header-price">Đơn giá</div>
                <div class="product-header-content" id="product-header-quantity">Số lượng</div>
                <div class="product-header-content" id="product-header-total">Tổng</div>
            </div>
            <div class="container-product-items">
                <c:forEach var="od" items="${orderDetails}">
                    <div class="product-item">
                        <div class="product-item-title">
                            <img src="${od.image}" class="img_Show">
                            <div class="product-item-info">
                                <label>${od.name}</label>
                                <div class="product-item-variant">
                                    <p>Màu sắc:
                                        <button class="variant-color ${od.color}"></button>
                                    </p>
                                    <p>Kích cỡ: <span class="variant-size">${od.size}</span></p>
                                </div>
                            </div>
                        </div>
                        <div class="product-item-more">
                            <span class="price"><fmt:formatNumber value="${od.price}" type="number"
                                                                  groupingUsed="true"/> đ</span>
                        </div>
                        <div class="product-item-quantity">
                            <span class="quantity">${od.quantity}</span>
                        </div>
                        <div class="product-item-more">
                            <span class="total"><fmt:formatNumber value="${od.totalPrice}" type="number"
                                                                  groupingUsed="true"/> đ</span>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div class="container-product-price">
                <div class="container-price">
                    <label>Tổng tiền:</label>
                    <label><fmt:formatNumber value="${order.totalPrice}" type="number" groupingUsed="true"/> đ</label>
                </div>
                <div class="container-price">
                    <label>Giảm giá:</label>
                    <label>
                        <c:choose>
                            <c:when test="${order.voucher == null}">
                                0 đ
                            </c:when>
                            <c:when test="${order.voucher.name == 'giam_gia'}">
                                <fmt:formatNumber value="${order.voucher.discount}" type="number"
                                                  groupingUsed="true"/> đ
                            </c:when>
                            <c:when test="${order.voucher.name == 'phan_tram'}">
                                <fmt:formatNumber value="${order.voucher.discount * 100}" maxFractionDigits="0"/> %
                            </c:when>
                            <c:when test="${order.voucher.name == 'mien_phi_van_chuyen'}">
                                Miễn phí vận chuyển
                            </c:when>
                            <c:otherwise>0 đ</c:otherwise>
                        </c:choose>
                    </label>
                </div>
                <div class="container-price" id="final-price">
                    <label class="title">Tổng thanh toán:</label>
                    <label class="price"><fmt:formatNumber value="${order.finalPrice}" type="number"
                                                           groupingUsed="true"/> đ</label>
                </div>
                <div class="container-status">
                    <label class="title">Trạng thái đơn hàng: ${order.status}</label>
                </div>
            </div>
        </div>
    </div>
    <form id="form-input-info" action="trangChu">
        <button type="submit">QUAY LẠI TRANG CHỦ</button>
    </form>
</main>
</body>
</html>