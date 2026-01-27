package org.example.projectweb.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.projectweb.model.User;
import org.example.projectweb.service.NotificationService;

import java.io.IOException;

@WebFilter(filterName = "HeaderFilter", urlPatterns = "/*")
public class HeaderFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);

        int unread = 0;
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                NotificationService ns = new NotificationService();
                unread = ns.countUnread(user.getUid());
            }
        }

        request.setAttribute("unreadCount", unread);
        chain.doFilter(request, response);
    }
}

