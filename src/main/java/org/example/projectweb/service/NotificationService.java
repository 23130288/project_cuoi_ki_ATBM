package org.example.projectweb.service;

import org.example.projectweb.dao.NotificationDao;
import org.example.projectweb.dao.UserDao;
import org.example.projectweb.dao.UserNotificationDao;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class NotificationService {
    final NotificationDao notificationDao = new NotificationDao();
    final UserDao userDao = new UserDao();
    final UserNotificationDao userNotificationDao = new UserNotificationDao();

    public void createGlobalNotification(String type, String title, String content, int id) {
        // 1. Tạo notification (1 bản ghi)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String createdDate = LocalDateTime.now().format(formatter);
        int nid = notificationDao.insertNotification(type, title, content, createdDate);

        // 2. Lấy danh sách user
        List<Integer> userIds = userDao.getListIdUser();

        // 3. Gán thông báo cho từng user
        if ("all".equalsIgnoreCase(type)) {
            for (int uid : userIds) {
                userNotificationDao.insertUserNotification(nid, uid, false, createdDate);
            }
        } else {
            userNotificationDao.insertUserNotification(nid, id, false, createdDate);
        }
    }
}
