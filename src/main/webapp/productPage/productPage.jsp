<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sản phẩm</title>
    <link rel="stylesheet" href="productPage/productPage.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css">
</head>
<body>
<main>
    <div class="section-product">
        <div class="container-images">
            <img src="${mainImg.image}" class="main-image" alt="${p.name}">
            <div class="images-list">
                <c:forEach var="img" items="${imgs}" varStatus="st">
                    <img class="sub-image ${st.index == 0 ? 'active' : ''}"
                         src="${img.image}" alt="">
                </c:forEach>
            </div>
        </div>
        <div class="product-details">
            <!--general info section-->
            <h2>${p.name}</h2>
            <div class="rating">
                <c:forEach begin="1" end="5" var="i">
                    <span class="star">
                        <c:choose>
                            <c:when test="${i <= avgRating}">
                                <i class="fa-solid fa-star"></i>
                            </c:when>
                            <c:otherwise>
                                <i class="fa-regular fa-star"></i>
                            </c:otherwise>
                        </c:choose>
                    </span>
                </c:forEach>
                <span class="rating-number">(${avgRating})</span>
            </div>
            <p class="price" id="price">${pvs[0].price} Đ</p>

            <label>Nhà sản xuất:</label>
            <p class="desc">${p.producer}</p>
            <label>Loại: <p class="desc">${p.type}</p></label>
            <label>Kiểu dáng: <p class="desc">${p.style}</p></label>
            <label>Chất liệu: <p class="desc">${p.material}</p></label>

            <label>Mô tả sản phẩm:</label>
            <p class="desc">${p.description}</p>
            <!--option section-->
            <div class="container-options">
                <div class="option">
                    <label>Chọn màu: </label>
                    <div class="color-options">
                        <c:forEach var="color" items="${colors}">
                            <button class="color-btn ${color}" data-color="${color}"></button>
                        </c:forEach>
                    </div>
                </div>
                <div class="option">
                    <label>Chọn kích cỡ: </label>
                    <div class="size-options">
                        <c:forEach var="size" items="${sizes}">
                            <button class="size-btn disabled" data-size="${size}">${size}</button>
                        </c:forEach>
                    </div>
                </div>
            </div>

            <!--Button section-->
            <div class="container-buttons">
                <button type="button" id="buyBtn">
                    <i class="fa-solid fa-money-check-dollar"></i> Mua
                </button>

                <button type="button" id="addToCartBtn"
                        data-pid="${p.pid}"
                        data-pvid=""
                        data-main-img="${mainImg.image}">
                    <i class="fa-solid fa-cart-shopping"></i>
                </button>

                <form action="productPage" method="post" class="form-wishlist">
                    <input type="hidden" name="productId" value="${p.pid}">
                    <button type="submit" class="wishlistBtn ${inWishlist ? 'active' : ''}" id="wishlistBtn">
                        <i class="fa-solid fa-heart"></i>
                    </button>
                </form>
            </div>
            <p id="statusMessage" class="status-msg"></p>
            <div class="popup" id="buyPopup">
                <div class="popup-content">
                    <div class="container-popup-title">
                        <h2>Thông tin mua hàng</h2>
                        <button type="button" class="edit-btn" id="editBtn">Chỉnh sửa <i class="fa-solid fa-pen"></i>
                        </button>
                    </div>
                    <label>Họ tên:<input type="text" id="name" placeholder="Nhập họ tên..."
                                         value="${user.name}" required disabled></label>
                    <label>Số điện thoại:<input type="text" id="phone" placeholder="Nhập số điện thoại..."
                                                value="${user.phone}" required disabled></label>
                    <label>Địa chỉ giao hàng:<input id="address" placeholder="Nhập địa chỉ..." value="${user.address}"
                                                    required disabled></label>

                    <div class="payment-container">
                        <label>Phương thức thanh toán</label>
                        <div class="radio-container">
                            <label><input type="radio" name="payment" required>Vietcombank
                                <img src="../images/bankImages/vietcombank.jpg" alt="Chuyển ngân hàng">
                            </label>
                            <label><input type="radio" name="payment">Visa
                                <img src="../images/bankImages/visa.jpeg" alt="Chuyển ngân hàng">
                            </label>
                            <label><input type="radio" name="payment">Tiền mặt</label>
                        </div>
                    </div>

                    <!--                    <div class="container-voucher" id="container-voucher">-->
                    <!--                        <label for="voucher">Voucher:</label>-->
                    <!--                        <div class="voucher-input">-->
                    <!--                            <input type="text" id="voucher" placeholder="Nhập mã giảm giá">-->
                    <!--                            <button id="apply-voucher">Áp dụng</button>-->
                    <!--                        </div>-->
                    <!--                    </div>-->

                    <div class="container-voucher" id="container-voucher">
                        <label>Voucher:</label>
                        <div class="voucher-container">
                            <label>
                                <input type="checkbox" name="voucher" value="5%">
                                <span class="voucher-name">Giảm giá 5%</span>
                                <span class="voucher-condition">Áp dụng cho đơn từ 200k</span>
                            </label>

                            <label>
                                <input type="checkbox" name="voucher" value="free_ship">
                                <span class="voucher-name">Miễn phí vận chuyển</span>
                                <span class="voucher-condition">Áp dụng cho đơn từ 0đ</span>
                            </label>

                            <label>
                                <input type="checkbox" name="voucher" value="10%">
                                <span class="voucher-name">Giảm 10%</span>
                                <span class="voucher-condition">Áp dụng cho đơn từ 500k</span>
                            </label>
                        </div>
                    </div>

                    <div class="container-product-price">
                        <div class="container-price">
                            <label>Tổng tiền:</label>
                            <p>345.000 đ</p>
                        </div>
                        <div class="container-price">
                            <label>Giảm giá:</label>
                            <p>0 đ</p>
                        </div>
                        <div class="container-price" id="final-price">
                            <label class="title">Tổng thanh toán:</label>
                            <p class="final-price-para">345.000 đ</p>
                        </div>
                    </div>
                    <div class="popup-buttons">
                        <a href="../ct_Order/ct_Order.html">
                            <button id="confirmBuy">Xác nhận</button>
                        </a>
                        <button id="cancelBuy">Hủy</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!--This is a comment section-->
    <div class="section-review">
        <div class="container-review">
            <c:choose>
                <c:when test="${canReview}">
                    <c:choose>
                        <c:when test="${userReview == null}">
                            <form method="post" action="add-review" class="form-review">
                                <!-- Rating -->
                                <div class="container-rating">
                                    <input type="hidden" name="productId" value="${p.pid}">
                                    <h3>Đánh giá</h3>
                                    <div class="stars">
                                        <input type="radio" id="star1" name="rating" value="1">
                                        <label for="star1">★</label>
                                        <input type="radio" id="star2" name="rating" value="2">
                                        <label for="star2">★</label>
                                        <input type="radio" id="star3" name="rating" value="3">
                                        <label for="star3">★</label>
                                        <input type="radio" id="star4" name="rating" value="4">
                                        <label for="star4">★</label>
                                        <input type="radio" id="star5" name="rating" value="5">
                                        <label for="star5">★</label>
                                    </div>
                                </div>

                                <!-- Comment -->
                                <div class="comment-form">
                                    <label>
                                        <textarea rows="4" placeholder="Viết bình luận của bạn về sản phẩm"
                                                  name="comment" required></textarea>
                                    </label>
                                    <button type="submit">Đăng đánh giá</button>
                                </div>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form method="post" action="update-review" class="form-review">
                                <!-- Rating -->
                                <div class="container-rating">
                                    <input type="hidden" name="productId" value="${p.pid}">
                                    <h3>Đánh giá</h3>
                                    <div class="stars">
                                        <input type="radio" id="star5" name="rating" value="5"
                                               <c:if test="${userReview.rating == 5}">checked</c:if>>
                                        <label for="star5">★</label>
                                        <input type="radio" id="star4" name="rating" value="4"
                                               <c:if test="${userReview.rating == 4}">checked</c:if>>
                                        <label for="star4">★</label>
                                        <input type="radio" id="star3" name="rating" value="3"
                                               <c:if test="${userReview.rating == 3}">checked</c:if>>
                                        <label for="star3">★</label>
                                        <input type="radio" id="star2" name="rating" value="2"
                                               <c:if test="${userReview.rating == 2}">checked</c:if>>
                                        <label for="star2">★</label>
                                        <input type="radio" id="star1" name="rating" value="1"
                                               <c:if test="${userReview.rating == 1}">checked</c:if>>
                                        <label for="star1">★</label>
                                    </div>
                                </div>

                                <!-- Comment -->
                                <div class="comment-form">
                                    <label>
                                        <textarea rows="4" placeholder="Viết bình luận của bạn về sản phẩm"
                                                  name="comment" required>${userReview.comment}</textarea>
                                    </label>
                                    <button type="submit">Chỉnh sửa đánh giá</button>
                                </div>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <p class="review-warning">Bạn cần mua sản phẩm này để có thể đánh giá.</p>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="comment-list">
            <c:forEach var="r" items="${reviews}">
                <div class="comment">
                    <h4>${r.userName}</h4>
                    <div class="rating">
                        <span class="star">
                            <c:forEach begin="1" end="5" var="i">
                                <c:choose>
                                    <c:when test="${i <= r.rating}">
                                        <i class="fa-solid fa-star"></i>
                                    </c:when>
                                    <c:otherwise>
                                        <i class="fa-regular fa-star"></i>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </span>
                    </div>
                    <p>${r.comment}</p>
                    <span>${r.createdDate}</span>
                </div>
            </c:forEach>
        </div>
    </div>
</main>
<script src="productPage/productPageJS/changeImage.js"></script>
<script src="productPage/productPageJS/buttonHandler.js"></script>
<script src="cartPage/cartPageJS/editInfo.js"></script>
<script>
    const variants = [
        <c:forEach var="v" items="${pvs}" varStatus="s">
        {
            pvid: ${v.pvid},
            color: "${v.color}",
            size: "${v.size}",
            price: "${v.price}"
        }
        <c:if test="${!s.last}">, </c:if>
        </c:forEach>
    ];
</script>
</body>
</html>