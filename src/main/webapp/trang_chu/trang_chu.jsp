<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>TRANG CHỦ</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css">
    <link rel="stylesheet" href="trang_chu/trang_chu.css">
</head>
<body>
<jsp:include page="../shareStuff/header/header.jsp"/>
<section class="product-section">
    <div class="page-title">
        <h1>TRANG CHỦ</h1>
    </div>
    <div class="voucher_section">

        <c:forEach var="v" items="${vouchers}">
            <div class="voucher-container">
                <div class="voucher-avata">
                    <img src="${v.image}" alt="voucher">
                </div>

                <div class="voucher-conten">
                    <h3>Giảm <fmt:formatNumber value="${v.discount}" pattern="#,###"/>đ</h3>
                    <h3>Áp dụng cho đơn từ <fmt:formatNumber value="${v.condition}" pattern="#,###"/>đ</h3>
                        <%--                        <button type="button" class="getBtn">Nhận</button>--%>
                    <form action="get-voucher" method="post">
                        <input type="hidden" name="vid" value="${v.vid}">
                        <button type="submit" class="getBtn">Nhận</button>
                    </form>
                </div>
            </div>
        </c:forEach>

    </div>

    <%--                        <c:choose>--%>
    <%--                            <c:when test="${sessionScope.user != null}">--%>
    <%--                                <a href="receiveVoucher?vid=${v.vid}" class="getBtn">--%>
    <%--                                    Nhận--%>
    <%--                                </a>--%>
    <%--                            </c:when>--%>
    <%--                            <c:otherwise>--%>
    <%--                                <a href="login.jsp" class="getBtn">--%>
    <%--                                    Đăng nhập để nhận--%>
    <%--                                </a>--%>
    <%--                            </c:otherwise>--%>
    <%--                        </c:choose>--%>


    <h2>SẢN PHẨM HOT</h2>
    <div class="slider-container">

        <div class="arrow left"><i class="fa fa-angle-left"></i></div>

        <div class="product-grid">
            <c:forEach var="p" items="${hotProducts}">
                <div class="product-card">

                    <c:choose>
                        <c:when test="${not empty p.mainImage}">
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

        <div class="arrow right"><i class="fa fa-angle-right"></i></div>
    </div>


    <h2>VALI DU LỊCH</h2>
    <div class="slider-container">

        <div class="arrow left"><i class="fa fa-angle-left"></i></div>

        <div class="product-grid">
            <c:forEach var="p" items="${valiProducts}">
                <div class="product-card">

                    <c:choose>
                        <c:when test="${not empty p.mainImage}">
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

        <div class="arrow right"><i class="fa fa-angle-right"></i></div>
    </div>

    <h2>BALO DU LỊCH</h2>
    <div class="slider-container">

        <div class="arrow left"><i class="fa fa-angle-left"></i></div>

        <div class="product-grid">
            <c:forEach var="p" items="${baloProducts}">
                <div class="product-card">

                    <c:choose>
                        <c:when test="${not empty p.mainImage}">
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

        <div class="arrow right"><i class="fa fa-angle-right"></i></div>
    </div>

</section>
<script src="trang_chu/trang_chu.js"></script>
<jsp:include page="../shareStuff/footer/footer.jsp"/>
</body>
</html>



