<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>TÌM KIẾM </title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css">
    <link rel="stylesheet" href="trang_tim_kiem/trang_tim_kiem.css">
</head>
<body>
<jsp:include page="../shareStuff/header/header.jsp"/>
<div class="container-search">
    <div class="search-bar">
        <input type="text" name="query" placeholder="Tên sản phẩm..."/>
        <button class="btn-search"><i class="fa-solid fa-magnifying-glass"></i></button>
    </div>

    <form method="post" action="search">
        <div class="filter-container">
            <button type="button" class="filter-toggle" id="filterToggle">
                <label>Bộ Lọc</label>
                <i class="fa-solid fa-plus"></i>
            </button>
            <div class="filter-panel" id="filterPanel">

                <!-- PRODUCER -->
                <div class="filter-group">
                    <label>Hãng Sản Xuất:</label>
                    <input type="text" name="producer" placeholder="Nhập tên hãng...">
                </div>

                <!-- CATEGORY -->
                <div class="filter-group">
                    <label>Loại:</label>
                    <select name="category">
                        <option value="">Tất cả</option>
                        <option value="balo">Balo</option>
                        <option value="vali">Vali</option>
                    </select>
                </div>

                <!-- COLOR -->
                <div class="filter-group">
                    <label>Màu Sắc:</label>
                    <select name="color">
                        <option value="">Tất cả</option>
                        <option value="red">Đỏ</option>
                        <option value="orange">Cam</option>
                        <option value="yellow">Vàng</option>
                        <option value="green">Lục</option>
                        <option value="blue">Xanh</option>
                        <option value="purple">Tím</option>
                        <option value="pink">Hồng</option>
                        <option value="grey">Xám</option>
                        <option value="black">Đen</option>
                    </select>

                </div>

                <!-- SIZE -->
                <div class="filter-group">
                    <label>Kích Cỡ:</label>
                    <select name="size">
                        <option value="">Tất cả</option>
                        <option value="S">S</option>
                        <option value="M">M</option>
                        <option value="L">L</option>
                        <option value="XL">XL</option>
                        <option value="20 inch">20 inch</option>
                        <option value="24 inch">24 inch</option>
                        <option value="28 inch">28 inch</option>
                    </select>
                </div>

                <!-- PRICE -->
                <div class="filter-group price-group">
                    <label>Giá:</label>
                    <div class="price-inline">
                        <input type="number" name="minPrice" placeholder="Từ">
                        <span>-</span>
                        <input type="number" name="maxPrice" placeholder="Đến">
                    </div>
                </div>


                <!-- SORT -->
                <div class="filter-group">
                    <label>Sắp Xếp:</label>
                    <select name="sort">
                        <option value="price_asc">Giá Thấp → Cao</option>
                        <option value="price_desc">Giá Cao → Thấp</option>
                        <option value="name_asc">Tên A → Z</option>
                        <option value="name_desc">Tên Z → A</option>
                    </select>
                </div>

                <!-- SUBMIT -->
                <button type="submit" class="btn-result">Áp Dụng</button>
            </div>
        </div>
    </form>
</div>

<section class="results">

    <c:choose>
        <c:when test="${not empty searchResults}">
            <div class="product-grid">
                <c:forEach var="p" items="${searchResults}">
                    <div class="product-card">
                        <c:choose>
                            <c:when test="${ not empty p.mainImage}">
                                <a href="productPage?pid=${p.pid}"><img src="${p.mainImage.image}" alt=""></a>
                            </c:when>
                            <c:otherwise>
                                <a href="productPage?pid=${p.pid}"><img src="trang_chu/image/balo_leo_nui.png" alt=""></a>
                            </c:otherwise>
                        </c:choose>

                        <h3>${p.name}</h3>

                        <c:if test="${not empty p.variants}">
                            <p class="price">
                                <span class="new-price">
                                    <fmt:formatNumber value="${p.variants[0].price}" pattern="#,###"/>đ
                                </span>
                            </p>
                        </c:if>
                        <div class="product-actions">
                            <a class="buy-btn" href="productPage?pid=${p.pid}">Xem Ngay</a>
                            <button type="button" class="inner-circle1"
                                    data-pid="${p.pid}"
                                    data-pvid="${p.variants[0].pvid}"
                                    data-main-img="${p.mainImage.image}">
                                <i class="fa-solid fa-cart-shopping"></i>
                            </button>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>

        <c:otherwise>
            <h2>Không tìm thấy sản phẩm.Vui lòng thử lại</h2>
        </c:otherwise>
    </c:choose>
</section>
<jsp:include page="../shareStuff/footer/footer.jsp"/>
<script src="trang_tim_kiem/trang_tim_kiem.js"></script>
</body>


