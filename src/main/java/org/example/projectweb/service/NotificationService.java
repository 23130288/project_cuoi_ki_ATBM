package org.example.projectweb.service;

import org.example.projectweb.dao.NotificationDao;
import org.example.projectweb.dao.UserDao;
import org.example.projectweb.dao.UserNotificationDao;
import org.example.projectweb.model.Notification;
import org.example.projectweb.model.UserNotificationView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class NotificationService {
    final NotificationDao nDao = new NotificationDao();
    final UserDao uDao = new UserDao();
    final UserNotificationDao unDao = new UserNotificationDao();

    public List<Notification> getAllNotifications() {
        return nDao.getListNotificatio();
    }

    public List<Notification> getAllNotificationsTitleLike(String title) {
        return nDao.getListNotificatio(title);
    }

    public void createGlobalNotification(String type, String title, String content) {
        // 1. Tạo notification (1 bản ghi)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String createdDate = LocalDateTime.now().format(formatter);
        int nid = nDao.insertNotification(type, title, content, createdDate);

        // 2. Lấy danh sách user
        List<Integer> userIds = uDao.getListIdUser();

        // 3. Gán thông báo cho từng user
        if ("all".equalsIgnoreCase(type)) {
            for (int uid : userIds) {
                unDao.insertUserNotification(nid, uid, false, createdDate);
            }
        } else {
            int id = Integer.parseInt(type);
            unDao.insertUserNotification(nid, id, false, createdDate);
        }
    }

    public List<UserNotificationView> getNotificationsByUid(int uid) {
        return nDao.getNotificationsByUid(uid);
    }

    public void markAsRead(int uid, int nid) {
        nDao.markAsRead(uid, nid);
    }

    public int countUnread(int uid) {
        return nDao.countUnread(uid);
    }
}
