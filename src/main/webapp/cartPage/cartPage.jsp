<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Giỏ hàng</title>
    <link rel="stylesheet" href="cartPage/cartPage.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css">
</head>
<body>
<jsp:include page="../shareStuff/header/header.jsp"/>
<main>
    <h1>Giỏ hàng</h1>
    <div class="container">
        <%-- ====================================== products ====================================== --%>
        <div class="product-info">
            <div class="product-info-header">
                <div class="product-info-header-content" id="product-info-header-title">Sản phẩm</div>
                <div class="product-info-header-content" id="product-info-header-price">Đơn giá</div>
                <div class="product-info-header-content" id="product-info-header-quantity">Số lượng</div>
                <div class="product-info-header-content" id="product-info-header-total">Tổng</div>
                <div class="product-info-header-content" id="product-info-header-action">Thao tác</div>
            </div>

            <div class="container-product-items">
                <c:if test="${empty cartItems}">
                    <p id="emptyCart">Giỏ hàng trống</p>
                </c:if>
                <c:forEach var="item" items="${cartItems}">
                    <div class="product-item">
                        <div class="product-item-title">
                            <a href="productPage?pid=${item.product.pid}">
                                <img src="${item.mainImg}" class="img_Show" alt="">
                            </a>
                            <div class="product-item-info">
                                <label>${item.product.name}</label>
                                <p>${item.product.description}</p>
                                <div class="product-item-variant"
                                     data-pid="${item.product.pid}"
                                     data-pvid="${item.productVariant.pvid}">
                                    <p>Màu: <span class="variant-color ${item.productVariant.color}"></span>
                                        | Size: <span class="variant-size">${item.productVariant.size}</span>
                                    </p>

                                    <button type="button" class="btn-edit-variant" data-pid="${item.product.pid}">
                                        <i class="fa-solid fa-pen-to-square"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="product-item-more">
                            <p class="price"><fmt:formatNumber value="${item.price}" type="number" groupingUsed="true"/>
                                đ</p>
                        </div>
                        <div class="product-item-quantity" data-pid="${item.product.pid}">
                            <button type="button" class="minus-quantity-button">-</button>
                            <p class="quantity">${item.quantity}</p>
                            <button type="button" class="plus-quantity-button">+</button>
                        </div>
                        <div class="product-item-more">
                            <p class="total" data-price="${item.price}"><fmt:formatNumber
                                    value="${item.price * item.quantity}" type="number" groupingUsed="true"/> đ</p>
                        </div>
                        <div class="product-item-more">
                            <a class="action-delete-product" href="remove-cart?pid=${item.product.pid}"><i
                                    class="fa-solid fa-x"></i></a>
                        </div>
                        <div class="popup hidden"
                             data-variants='[
                                <c:forEach var="v" items="${item.product.variants}" varStatus="s">
                                {
                                    "color": "${v.color}",
                                    "size": "${v.size}",
                                    "price": ${v.price},
                                    "pvid": ${v.pvid}
                                }<c:if test="${!s.last}">,</c:if>
                                </c:forEach>
                            ]'>
                            <div class="popup-content">
                                <h3>Chọn phân loại sản phẩm</h3>

                                <div class="container-options">
                                    <div class="option">
                                        <label>Chọn màu: </label>
                                        <div class="color-options">
                                            <c:forEach var="color" items="${item.variantColors}">
                                                <button class="color-btn ${color}" data-color="${color}"></button>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div class="option">
                                        <label>Chọn kích cỡ: </label>
                                        <div class="size-options">
                                            <c:forEach var="size" items="${item.variantSizes}">
                                                <button class="size-btn disabled" data-size="${size}">${size}</button>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>

                                <div class="popup-actions">
                                    <button class="confirmVariant">Xác nhận</button>
                                    <button class="closePopup">Hủy</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <%-- ====================================== PRICE ====================================== --%>
        <div class="container-product-price">
            <div class="container-price voucher-box" id="container-voucher">
                <label for="voucherSelect">Voucher:</label>
                <c:choose>
                    <c:when test="${empty vouchers}">
                        <p class="no-voucher">Bạn chưa có voucher nào ¯\_(ツ)_/¯</p>
                    </c:when>
                    <c:otherwise>
                        <div class="vouchers" id="voucher-list">
                            <select id="voucherSelect">
                                <option value="">-- Chưa chọn voucher --</option>
                                <c:forEach var="v" items="${vouchers}">
                                    <option value="${v.uvid}">
                                        <c:choose>
                                            <c:when test="${v.name == 'giam_gia'}">
                                                Giảm <fmt:formatNumber value="${v.discount}" type="number"
                                                                       groupingUsed="true"/> đ
                                                ~ Áp dụng cho đơn từ <fmt:formatNumber value="${v.condition}"
                                                                                       type="number"
                                                                                       groupingUsed="true"/> đ
                                            </c:when>
                                            <c:when test="${v.name == 'phan_tram'}">
                                                Giảm <fmt:formatNumber value="${v.discount * 100}"
                                                                       maxFractionDigits="0"/> %
                                                ~ Áp dụng cho đơn từ <fmt:formatNumber value="${v.condition}"
                                                                                       type="number"
                                                                                       groupingUsed="true"/> đ
                                            </c:when>
                                            <c:when test="${v.name == 'mien_phi_van_chuyen'}">
                                                Miễn phí vận chuyển ~ Áp dụng cho đơn từ <fmt:formatNumber
                                                    value="${v.condition}" type="number" groupingUsed="true"/> đ
                                            </c:when>
                                            <c:otherwise>
                                                ${v.name}
                                            </c:otherwise>

                                        </c:choose>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="container-price">
                <label>Tổng tiền:</label>
                <p id="cart-total-price" data-value="${cart.totalPrice}"><fmt:formatNumber value="${cart.totalPrice}"
                                                                                           type="number"
                                                                                           groupingUsed="true"/> đ</p>
            </div>
            <div class="container-price">
                <label>Giảm giá:</label>
                <p id="discount-ammount">
                    <c:choose>
                        <c:when test="${cart.voucher == null}">
                            0 đ
                        </c:when>
                        <c:when test="${cart.voucher.name == 'giam_gia'}">
                            <fmt:formatNumber value="${cart.voucher.discount}"
                                              type="number"
                                              groupingUsed="true"/> đ
                        </c:when>
                        <c:when test="${cart.voucher.name == 'phan_tram'}">
                            <fmt:formatNumber value="${cart.voucher.discount * 100}"
                                              maxFractionDigits="0"/> %
                        </c:when>
                        <c:when test="${cart.voucher.name == 'mien_phi_van_chuyen'}">
                            Miễn phí vận chuyển
                        </c:when>
                        <c:otherwise>0 đ</c:otherwise>
                    </c:choose>
                </p>
            </div>
            <div class="container-price" id="final-price">
                <label class="title">Tổng thanh toán:</label>
                <p class="price" id="cart-final-price"><fmt:formatNumber value="${cart.finalPrice}" type="number"
                                                                         groupingUsed="true"/> đ</p>
            </div>
        </div>

        <%-- ====================================== user info ====================================== --%>
        <div class="user-info">
            <form id="form-input-info" action="checkout" method="post">
                <div class="container-user-title">
                    <h2>Thông tin người dùng</h2>
                    <c:if test="${user != null}">
                        <a href="tai_khoan" class="edit-btn" id="editBtn">Chỉnh sửa <i class="fa-solid fa-pen"></i></a>
                    </c:if>
                </div>
                <div class="form-container">
                    <c:choose>
                        <c:when test="${user == null}">
                            <a href="dang_nhap" class="login-notice"><p>Xin hãy đăng nhập để có thể dùng chức năng giỏ
                                hàng.</p></a>
                        </c:when>
                        <c:when test="${empty user.phone || empty user.address}">
                            <div class="missing-information-container">
                                <p>Vui lòng cập nhật đầy đủ thông tin cá nhân (Sđt, địa chỉ) trước khi thanh toán.</p>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="user-info-container">
                                <div class="form-group">
                                    <label for="name">Họ tên: </label>
                                    <p>${user.name}</p>
                                </div>
                                <div class="form-group">
                                    <label for="phone">Số điện thoại: </label>
                                    <p>${user.phone}</p>
                                </div>
                                <div class="form-group">
                                    <label for="address">Địa chỉ: </label>
                                    <p>${user.address}</p>
                                </div>
                                <div class="optional-information">
                                    <label for="note">Thông tin thêm:</label>
                                    <textarea id="note" name="note" rows="3" placeholder="Optional"></textarea>
                                </div>
                            </div>

                            <div class="payment-container">
                                <label>Phương thức thanh toán</label>
                                <div class="radio-container">
                                    <label><input type="radio" name="payment" required>Vietcombank
                                        <img src="images/bankImages/vietcombank.jpg" alt="Chuyển ngân hàng">
                                    </label>
                                    <label><input type="radio" name="payment">Visa
                                        <img src="images/bankImages/visa.jpeg" alt="Chuyển ngân hàng">
                                    </label>
                                    <label><input type="radio" name="payment">Tiền mặt</label>
                                </div>
                            </div>
                            <div class="container-buttons">
                                <button type="button" class="confirm-btn" id="confirmBtn">Thanh toán</button>
                            </div>

                            <!-- digital sign -->
                            <div class="digital-sign-model" id="digitalSignModel">
                                <div class="digital-sign-box">
                                    <h2>Ký số điện tử</h2>
                                    <div class="sign-info">
                                        <p>Tài liệu: ...</p>
                                        <p>Thuật toán: SHA256withRSA</p>
                                        <p>Thời gian: <span id="currentTime"></span></p>
                                    </div>

                                    <div class="output-box">
                                        <h3>Download file:</h3>
                                        <div class="output-file">
                                            <span id="signedFileName">document.txt</span>
                                            <a href="#" download class="download-btn">Tải xuống</a>
                                        </div>
                                    </div>

                                    <div class="input-box">
                                        <h3>Chọn file xác thực</h3>
                                        <div class="file-group">
                                            <label for="inputFile">File Input</label>
                                            <input type="file" id="inputFile">
                                        </div>
                                        <div class="file-group">
                                            <label for="sigFile">File Sig</label>
                                            <input type="file" id="sigFile">
                                        </div>
                                    </div>

                                    <div class="button-group">
                                        <button class="cancel-btn" id="cancelBtn">Hủy</button>
                                        <button type="submit" class="sign-btn" id="signBtn">Xác nhận ký điện tử</button>
                                    </div>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </form>
        </div>
    </div>
</main>
<jsp:include page="../shareStuff/footer/footer.jsp"/>
<script>
</script>
<script src="cartPage/cartPageJS/variant.js"></script>
<script src="cartPage/cartPageJS/quantity.js"></script>
<script src="cartPage/cartPageJS/voucher.js"></script>
<script src="cartPage/cartPageJS/signature.js"></script>
</body>
</html>