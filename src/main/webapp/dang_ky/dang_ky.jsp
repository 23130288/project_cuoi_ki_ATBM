<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<%--<link rel="stylesheet" href="dang_ky/dang_ky.css">--%>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="dang_ky/dang_ky.css">
    <title>Title</title>
</head>
<body>
<div class="section_2" id="section_2">
    <a href="trangChu">
        <img src="dang_ky/image/logo_white.png" alt="Logo" class="logo">
    </a>
</div>

<div class="section_1" id="section_1">
    <h1>Đăng ký tài khoản của bạn</h1>
    <form action="dang_ky" method="post">
        <%
            String err = (String) request.getAttribute("err");
            if (err == null) err = "";

            String name = (String) request.getParameter("name");
            if (name == null) name = "";

            String email = (String) request.getParameter("email");
            if (email == null) email = "";

            String password = (String) request.getAttribute("password");
            if (password == null) password = "";

            String confirm_password = (String) request.getParameter("confirm_password");
            if (confirm_password == null) confirm_password = "";
        %>

        <div class="container">
            <span class="login-error"><%= err %></span>
        </div>

        <div class="container" id="container_email">
            <label for="email">Email:</label>
            <input type="text" id="email" name="email" value="<%= email %>">
            <p id="emailError" class="error"></p>
        </div>
        <div class="container" id="container_name">
            <label for="name">Tên:</label>
            <input type="text" id="name" name="name" value="<%= name %>">
        </div>

        <div class="container" id="container_password">
            <label for="password">Mật khẩu:</label>
            <div class="password-wrapper">
                <input type="password" id="password" name="password" value="<%= password %>">
                <button type="button" id="togglePassword">👁</button>
            </div>
            <p id="passwordError" class="error"></p>
        </div>

        <div class="container" id="container_confirm">
            <label for="confirm_password">Nhập lại mật khẩu:</label>
            <div class="password-wrapper">
                <input type="password" id="confirm_password" name="confirm_password" value="<%= confirm_password %>">
                <button type="button" id="toggleConfirmPassword">👁</button>
            </div>
            <p id="confirmError" class="error"></p>
        </div>

        <div class="container" id="container_log">
            <input class="log" id="log" type="submit" value="Đăng ký">
        </div>
    </form>
    <footer class="footer" id="container_more">
    </footer>
    <script src="dang_ky/dang_ky.js"></script>
</div>
</body>
</html>