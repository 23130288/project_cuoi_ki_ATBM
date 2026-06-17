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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css">
</head>
<body>
<main>
    <div class="container">
        <h3 class="title">Đơn hàng #${order.oid}</h3>
        <p class="date">${order.createdDate}</p>
        <div class="customer-info">
            <p class="customer-name">User: ${user.name}</p>
            <p class="description">Description: ${order.description}</p>
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
    <c:choose>
        <%-- changes and signed --%>
        <c:when test="${order.changed && order.status != 'waiting'}">
            <div class="signed-message changed">
                <i class="fa-solid fa-circle-check"></i>
                <p>Đơn hàng (có thay đổi) đã được đóng gói.</p>
            </div>
        </c:when>
        <%-- nothing unusual --%>
        <c:when test="${!order.changed && order.signStatus}">
            <div class="signed-message">
                <i class="fa-solid fa-circle-check"></i>
                <span>Bạn đã ký đơn hàng này.</span>
            </div>
        </c:when>
        <%-- expired --%>
        <c:when test="${order.expired && !order.signStatus}">
            <div class="expired-message">
                <i class="fa-solid fa-circle-xmark"></i>
                <p>Đơn hàng đã quá hạn.</p>
            </div>
        </c:when>
        <%-- not signed --%>
        <c:otherwise>
            <c:if test="${order.changed}">
                <form action="support-order-changed" id="supportForm" method="post">
                    <button type="button" id="report-missing-btn">Báo cáo thay đổi</button>
                    <div class="report-missing" id="report-missing">
                        <div class="report-missing-box">
                            <h3>Đơn hàng có sự thay đổi</h3>
                            <p>Vui lòng xem lại trước khi ký.</p>
                            <input type="hidden" name="oid" value="${order.oid}">
                            <input type="hidden" name="uid" value="${order.uid}">
                            <textarea rows=3 placeholder="Hãy mô tả vấn đề của bạn tại đây..."
                                      name="message"></textarea>
                            <button type="button" class="cancel-btn" id="cancel-report-missing">Hủy</button>
                            <button type="submit" class="report-btn">Báo cáo thay đổi</button>
                        </div>
                    </div>
                    <div id="waitingMessage">
                        <h3>Yêu cầu của bạn đã được gửi.</h3>
                    </div>
                    </div>
                </form>
            </c:if>
            <form id="form-verify" method="post" action="verify-order">
                <button type="button" class="confirm-btn" id="confirmBtn">Ký đơn hàng</button>
                <input type="hidden" name="oid" value="${order.oid}">
                <div class="digital-sign-model" id="digitalSignModel">
                    <div class="digital-sign-box">
                        <h2>Ký số điện tử</h2>
                        <div class="sign-info">
                            <p>Thuật toán: MD5withRSA</p>
                        </div>

                        <div class="output-box">
                            <h3>File:</h3>
                            <div class="output-file">
                                <span id="signedFileName">Order#${order.oid}.txt</span>
                                <button type="submit" form="downloadForm" class="download-btn"><i
                                        class="fa-solid fa-download"></i></button>
                            </div>
                        </div>

                        <div class="input-box">
                            <h3>Signature:</h3>
                            <input type="file" id="sigFile" name="sigFile" required>
                        </div>

                        <div class="button-group">
                            <button type="button" class="cancel-btn" id="cancelBtn">Hủy</button>
                            <button type="button" class="sign-btn" id="signBtn">Xác nhận ký điện tử</button>
                        </div>
                        <p class="error-verify" id="error-verify">Signature verification failed</p>
                    </div>
                </div>
            </form>
            <form id="downloadForm" action="DownloadOrderContent" method="post">
                <input type="hidden" name="oid" value="${order.oid}">
            </form>
        </c:otherwise>
    </c:choose>
    <form id="form-input-info" action="trangChu">
        <button type="submit">QUAY LẠI TRANG CHỦ</button>
    </form>
</main>
<script src="ct_Order/ct_Order.js"></script>
</body>
</html>