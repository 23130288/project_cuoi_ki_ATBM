<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Giỏ hàng</title>
    <link rel="stylesheet" href="cartPage/cartPage.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css">
</head>
<body>
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
                            <p class="price">${item.price} đ</p>
                        </div>
                        <div class="product-item-quantity" data-pid="${item.product.pid}">
                            <button type="button" class="minus-quantity-button">-</button>
                            <p class="quantity">${item.quantity}</p>
                            <button type="button" class="plus-quantity-button">+</button>
                        </div>
                        <div class="product-item-more">
                            <p class="total" data-price="${item.price}">${item.price * item.quantity} đ</p>
                        </div>
                        <div class="product-item-more">
                            <label class="action-select-product">
                                <input type="checkbox" name="select-checkbox">
                            </label>
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
                <div class="voucher-header" id="voucher-toggle">
                    <label>Voucher</label>
                    <span class="voucher-selected" id="voucher-selected">Chưa chọn</span>
                    <span class="arrow">▼</span>
                </div>

                <div class="vouchers" id="voucher-list">
                    <label>
                        <input type="radio" name="voucher">
                        <span class="voucher-name">Giảm giá 5%</span>
                        <span class="voucher-condition">Áp dụng cho đơn từ 200k</span>
                    </label>

                    <label>
                        <input type="radio" name="voucher">
                        <span class="voucher-name">Miễn phí vận chuyển</span>
                        <span class="voucher-condition">Áp dụng cho đơn từ 0đ</span>
                    </label>

                    <label>
                        <input type="radio" name="voucher">
                        <span class="voucher-name">Giảm 10%</span>
                        <span class="voucher-condition">Áp dụng cho đơn từ 500k</span>
                    </label>
                </div>
            </div>
            <div class="container-price">
                <label>Tổng tiền:</label>
                <p id="cart-total-price">${cart.totalPrice} đ</p>
            </div>
            <div class="container-price">
                <label>Giảm giá:</label>
                <p>0 đ</p>
            </div>
            <div class="container-price" id="final-price">
                <label class="title">Tổng thanh toán:</label>
                <p class="price" id="cart-final-price">${cart.totalPrice} đ</p>
            </div>
        </div>

        <%-- ====================================== user info ====================================== --%>
        <div class="user-info">
            <form id="form-input-info" action="../ct_Order/ct_Order.html">
                <div class="container-user-title">
                    <h2>Thông tin người dùng</h2>
                    <button type="button" class="edit-btn" id="editBtn">Chỉnh sửa <i class="fa-solid fa-pen"></i>
                    </button>
                </div>
                <div class="form-container">
                    <div class="user-info-container">
                        <div class="form-row">
                            <div class="form-group">
                                <label for="name">Họ tên:</label>
                                <input type="text" id="name" placeholder="Họ tên" value="Name placeholder" required
                                       disabled>
                            </div>
                            <div class="form-group">
                                <label for="phone">Số điện thoại:</label>
                                <input type="text" id="phone" placeholder="Số điện thoại" value="0123456789" required
                                       disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="address">Địa chỉ:</label>
                            <input type="text" id="address"
                                   placeholder="Địa chỉ..." value="1A/1 Tổ 1A, Khu phố 1, Phường An Bình, Tỉnh Đồng Nai"
                                   required disabled>
                        </div>
                        <div class="form-group">
                            <label for="note">Thông tin thêm:</label>
                            <textarea id="note" rows="3" placeholder="Optional"></textarea>
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
                        <button type="submit" class="confirm-btn">Thanh toán</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</main>
<script>
</script>
<script src="cartPage/cartPageJS/variant.js"></script>
<script src="cartPage/cartPageJS/quantity.js"></script>
<script src="cartPage/cartPageJS/voucher.js"></script>
<script src="cartPage/cartPageJS/voucher.js"></script>
</body>
</html>