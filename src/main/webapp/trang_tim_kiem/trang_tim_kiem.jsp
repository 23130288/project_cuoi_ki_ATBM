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
    <link rel="stylesheet" href="../shareStuff/header/header.css">
    <link rel="stylesheet" href="../shareStuff/footer/footer.css">
</head>
<body>
<header>
    <div id="header-placeholder"></div>
    <script src="../shareStuff/header/headerGetter.js"></script>
</header>
<div class="container-search">
    <div class="search-bar">
        <input type="text" name="query" placeholder="Tên sản phẩm..."/>
        <button class="btn-search"><i class="fa-solid fa-magnifying-glass"></i></button>
    </div>

    <form method="post" action="search">

        <div class="filter-container">

            <button type="button" class="filter-toggle" id="filterToggle">
                <label>Bộ lọc</label>
                <i class="fa-solid fa-plus"></i>
            </button>

            <div class="filter-panel" id="filterPanel">

                <!-- PRODUCER -->
                <div class="filter-group">
                    <label>Hãng sản xuất:</label>
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
                    <label>Màu sắc:</label>
                    <select name="color">
                        <option value="">Tất cả</option>
                        <option value="do">Đỏ</option>
                        <option value="cam">Cam</option>
                        <option value="vang">Vàng</option>
                        <option value="luc">Lục</option>
                        <option value="xanh">Xanh</option>
                        <option value="tim">Tím</option>
                        <option value="hong">Hồng</option>
                        <option value="xam">Xám</option>
                        <option value="den">Đen</option>
                    </select>
                </div>

                <!-- SIZE -->
                <div class="filter-group">
                    <label>Size:</label>
                    <select name="size">
                        <option value="">Tất cả</option>
                        <option value="S">S</option>
                        <option value="M">M</option>
                        <option value="L">L</option>
                        <option value="XL">XL</option>
                        <option value="20">20 inch</option>
                        <option value="24">24 inch</option>
                        <option value="28">28 inch</option>
                    </select>
                </div>

                <!-- PRICE -->
                <div class="filter-group">
                    <label>Giá từ:</label>
                    <input type="number" name="minPrice" min="0" step="100000" placeholder="0">
                    <label>Đến:</label>
                    <input type="number" name="maxPrice" min="0" step="100000" placeholder="0">
                </div>

                <!-- SORT -->
                <div class="filter-group">
                    <label>Sắp xếp:</label>
                    <select name="sort">
                        <option value="price_asc">Giá Thấp → Cao</option>
                        <option value="price_desc">Giá Cao → Thấp</option>
                        <option value="name_asc">Tên A → Z</option>
                        <option value="name_desc">Tên Z → A</option>
                    </select>
                </div>

                <!-- SUBMIT -->
                <button type="submit" class="btn-search">Áp dụng</button>

            </div>
        </div>

    </form>


</div>

<section class="results">
    <c:choose>
    <c:when test="${not empty searchResults}">
        <h1>Kết quả tìm kiếm cho: "${param.query}"</h1>
        <div class="slider-container">
            <div class="product-grid">
                <c:forEach var="p" items="${searchResults}">
                    <div class="product-card">
                        <a href="productDetail?pid=${p.pid}">
                            <img src="${p.mainImage.image}" alt="${p.name}">
                            <h2>${p.name}</h2>
                        </a>
                        <p class="price">
                                <span class="new-price">
                                    <fmt:formatNumber value="${p.variants[0].price}" pattern="#,###"/>đ
                                </span>
                        </p>
                        <div class="product-actions">
                            <button class="buy-btn">Mua Ngay</button>
                            <div class="inner-circle1">
                                <i class="fa fa-shopping-cart"></i>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div class="no-results-container">
            <h2 class="no-results-title">Không tìm thấy sản phẩm nào!</h2>
            <p class="no-results-text">
                Rất tiếc, chúng tôi không tìm thấy kết quả cho từ khóa:
                <strong class="highlight-keyword">"${param.query}"</strong>
            </p>
            <p class="no-results-subtext">Vui lòng kiểm tra lại chính tả hoặc tìm kiếm bằng từ khóa khác.</p>
        </div>
    </c:otherwise>
    </c:choose>
</section>
<footer>
    <div id="footer-placeholder"></div>
    <script src="../shareStuff/footer/footerGetter.js"></script>
</footer>
<script src="trang_tim_kiem/trang_tim_kiem.js"></script>
</body>


