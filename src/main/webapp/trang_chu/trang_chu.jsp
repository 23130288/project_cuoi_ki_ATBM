<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>TRANG CHỦ</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="trang_chu/trang_chu.css">
    <link rel="stylesheet" href="../shareStuff/header/header.css">
    <link rel="stylesheet" href="../shareStuff/footer/footer.css">
</head>
<body>
<header>
    <div id="header-placeholder"></div>
    <script src="../shareStuff/header/headerGetter.js"></script>
</header>
<section class="product-section">
    <div class="page-title">
        <h1>TRANG CHỦ</h1>
    </div>
    <div class="voucher_section">
        <div class="voucher-container">
            <div class="voucher-avata">
                <img src="trang_chu/image/voucer_percent.png" alt="">
            </div>
            <div class="voucher-conten">
               <h3>Giảm giá 5%</h3>
                <h3>Áp dụng cho đơn từ 200k</h3>
                <button type="button" class="getBtn">nhận</button>
            </div>
        </div>

        <div class="voucher-container">
            <div class="voucher-avata">
                <img src="trang_chu/image/vpucher_free_ship.png" alt="">
            </div>
            <div class="voucher-conten">
                <h3>Miễn phí vận chuyển</h3>
                <h3>Áp dụng cho đơn từ 0đ</h3>
                <button type="button" class="getBtn">nhận</button>
            </div>
        </div>

        <div class="voucher-container">
            <div class="voucher-avata">
                <img src="trang_chu/image/voucher_50k.png" alt="">
            </div>
            <div class="voucher-conten">
                <h3>Giảm 50k</h3>
                <h3>Áp dụng cho đơn từ 200k</h3>
                <button type="button" class="getBtn">nhận</button>
            </div>
        </div>
    </div>

    <h2>SẢN PHẨM HOT</h2>
    <div class="slider-container">

            <div class="arrow left"><i class="fa fa-angle-left"></i></div>

            <div class="product-grid">
                <c:forEach var="p" items="${hotProducts}">
                    <a href="productDetail?pid=${p.pid}" class="product-link">
                        <div class="product-card">

                            <c:choose>
                                <c:when test="${not empty p.mainImage}">
<%--                                    <img src="${p.images[0].image}" alt="${p.name}">--%>
                                    <img src="trang_chu/image/${p.mainImage.image}" alt="">
                                </c:when>
                                <c:otherwise>
                                    <img src="trang_chu/image/no-image.png" alt="">
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
                                <button class="buy-btn">Mua Ngay</button>
                                <div class="inner-circle1">
                                    <i class="fa fa-shopping-cart"></i>
                                </div>
                            </div>

                        </div>
                    </a>
                </c:forEach>
            </div>

            <div class="arrow right"><i class="fa fa-angle-right"></i></div>
        </div>


    <h2>VALI DU LỊCH</h2>
    <div class="slider-container">

        <div class="arrow left"><i class="fa fa-angle-left"></i></div>

        <div class="product-grid">
            <c:forEach var="p" items="${valiProducts}">
                <a href="productDetail?pid=${p.pid}" class="product-link">
                    <div class="product-card">

                        <c:choose>
                            <c:when test="${not empty p.mainImage}">
<%--                                <img src="${p.images[0].image}" alt="${p.name}">--%>
                                <img src="trang_chu/image/${p.mainImage.image}" alt="">
                            </c:when>
                            <c:otherwise>
                                <img src="trang_chu/image/no-image.png" alt="">
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
                            <button class="buy-btn">Mua Ngay</button>
                            <div class="inner-circle1">
                                <i class="fa fa-shopping-cart"></i>
                            </div>
                        </div>

                    </div>
                </a>
            </c:forEach>
        </div>

        <div class="arrow right"><i class="fa fa-angle-right"></i></div>
    </div>

    <h2>BALO DU LỊCH</h2>
    <div class="slider-container">

        <div class="arrow left"><i class="fa fa-angle-left"></i></div>

        <div class="product-grid">
            <c:forEach var="p" items="${baloProducts}">
                <a href="productDetail?pid=${p.pid}" class="product-link">
                    <div class="product-card">

                        <c:choose>
                            <c:when test="${not empty p.mainImage}">
<%--                                <img src="${p.images[0].image}" alt="${p.name}">--%>
                                <img src="trang_chu/image/${p.mainImage.image}" alt="">
                            </c:when>
                            <c:otherwise>
                                <img src="trang_chu/image/no-image.png" alt="">
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
                            <button class="buy-btn">Mua Ngay</button>
                            <div class="inner-circle1">
                                <i class="fa fa-shopping-cart"></i>
                            </div>
                        </div>

                    </div>
                </a>
            </c:forEach>
        </div>

        <div class="arrow right"><i class="fa fa-angle-right"></i></div>
    </div>

</section>
<script src="trang_chu/trang_chu.js"></script>
<footer>
    <div id="footer-placeholder"></div>
    <script src="../shareStuff/footer/footerGetter.js"></script>
</footer>
</body>



