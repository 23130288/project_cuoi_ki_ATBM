<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet" href="${pageContext.request.contextPath}/Tham_quyen/tham_quyen.css">


<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css">
</head>
<body>
<jsp:include page="../shareStuff/header/header.jsp"/>

<div class="selection_2" id="selection_2">
    <span class="no-permission">Bạn không có thẩm quyền</span>
</div>

<jsp:include page="../shareStuff/footer/footer.jsp"/>
</body>
</html>