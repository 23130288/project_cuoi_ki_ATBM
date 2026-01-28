<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>HEADER</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/shareStuff/header/header.css">
</head>
<body>
<header>
    <div class="top-banner">
        <div class="brand-name">
            <a href="trangChu">
                <img src="${pageContext.request.contextPath}/shareStuff/header/image/logo.png" alt="Travel Logo">
            </a>
        </div>
        <div class="header-search">
            <form class="search-bar">
                <select class="search-select" name="category">
                    <option value="VALI">VALI</option>
                    <option value="BALO">BALO</option>
                </select>
                <div class="search-suggestions" id="suggestions">
                    <ul>
                        <li>Balo laptop chống sốc</li>
                        <li>Balo du lịch 40L</li>
                        <li>Vali kéo 24 inch</li>
                        <li>Vali nhựa siêu nhẹ</li>
                        <li>Balo thời trang nữ</li>
                    </ul>
                </div>
                <div class="divider"></div>
                <div class="search-icon">
                    <i class="fa fa-search"></i>
                </div>
                <input type="text" name="query" placeholder="Nhập từ khóa..."/>
                <div class="divider"></div>
                <a href="search" class="search-button">TÌM KIẾM</a>
            </form>
        </div>
        <div class="container-icons">
            <div class="avatar-icon-notify">
                <div class="inner-circle">
                    <a href="tai_khoan?tab=notification">
                        <i class="fa-solid fa-bell"></i>
                        <c:if test="${unreadCount > 0}">
                            <span class="notify-dot"></span>
                        </c:if>
                    </a>
                </div>
            </div>
            <div class="avatar-icon-shopping-cart">
                <div class="inner-circle">
                    <a href="cart">
                        <i class="fa fa-shopping-cart"></i>
                    </a>
                </div>
            </div>
            <div class="avatar-icon-user">
                <div class="inner-circle">
                    <a href="tai_khoan">
                        <c:choose>
                            <c:when test="${userAvatar != null}">
                                <img src="${userAvatar}" alt="avatar" class="avatar-img">
                            </c:when>
                            <c:otherwise>
                                <i class="fa fa-user"></i>
                            </c:otherwise>
                        </c:choose>
                    </a>
                </div>
            </div>
        </div>
    </div>

    <nav>
        <div class="content-nav">
            <!--            <button id="menu-toggle">-->
            <!--                <i class="fa fa-bars" aria-hidden="true"></i>-->
            <!--            </button>-->
            <ul>
                <li><a href="trangChu">TRANG CHỦ</a></li>
                <li class="dropdown">
                    <a href="search">TÌM KIẾM</a>
                </li>
                <li><a href="wishlist">WISHLIST</a></li>
                <li><a href="support">LIÊN HỆ</a></li>
                <li><a href="chinh_sach">CHÍNH SÁCH</a></li>
                <li><a href="dich_vu">DỊCH VỤ</a></li>
            </ul>
        </div>
    </nav>
</header>
</body>
</html>