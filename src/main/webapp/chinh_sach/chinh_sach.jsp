<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../shareStuff/header/header.css">
    <link rel="stylesheet" href="../shareStuff/footer/footer.css">
    <link rel="stylesheet" href="chinh_sach/chinh_sach.css">
</head>
<body>
    <jsp:include page="../shareStuff/header/header.jsp" />

    <div class="name">
        <h1>Chính sách</h1>
    </div>
    <div class="container">
        <div class="sidebar" id="sidebar">
            <h3>DANH MỤC</h3>
            <div class="menu_item">
                <c:forEach var="p" items="${policies}">
                    <a class="item"
                       href="${pageContext.request.contextPath}/chinh_sach?spid=${p.spid}">
                            ${p.title}
                    </a>
                </c:forEach>
            </div>
        </div>

        <main class="content" id="content">
            <c:if test="${not empty current}">
                <h2>${current.title}</h2>
                <p class="policy-date"> ${current.lastedUpdate}</p>
                <pre>${current.content}</pre>
            </c:if>
        </main>
    </div>

    <jsp:include page="../shareStuff/footer/footer.jsp" />
</body>
</html>
