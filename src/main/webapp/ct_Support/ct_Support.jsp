<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hỗ trợ</title>
    <link rel="stylesheet" href="ct_Support/ct_Support.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css">
</head>
<body>

<main>
    <h1>Trung tâm hỗ trợ khách hàng</h1>
    <div class="container">
        <!-- topics to choose -->
        <div class="container-topics">
            <label for="topicSelect"></label><select id="topicSelect">
            <option value="" disabled selected>${support.topic}</option>
        </select>
        </div>
        <div class="support-form">
            <label>
                <textarea rows=1 disabled>${support.title}</textarea>
            </label>
        </div>

        <!-- danh sách message -->
        <div class="support-messages">
            <c:forEach var="m" items="${messages}">
                <div class="message ${m.sender_id == support.uid ? 'user' : 'admin'}">
                    <p>${m.message}</p>
                    <span>${m.createdDate}</span>
                </div>
            </c:forEach>
        </div>

        <!-- form -->
        <form class="support-form" method="post" action="add-message">
            <c:choose>
                <c:when test="${support.status == 'done'}">
                    <label id="done">Đã xử lý xong</label>
                </c:when>
                <c:otherwise>
                    <input type="hidden" name="spid" value="${support.spid}"/>
                    <label>
                        <textarea rows=3 name="mess" placeholder="Trả lời tại đây..."></textarea>
                    </label>
                    <button type="submit" id="sendBtn">Gửi phản hồi</button>
                </c:otherwise>
            </c:choose>
        </form>
    </div>
</main>
</body>
</html>