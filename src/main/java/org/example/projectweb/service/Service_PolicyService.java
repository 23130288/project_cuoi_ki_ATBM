package org.example.projectweb.service;

import org.example.projectweb.dao.Service_PolicyDao;
import org.example.projectweb.model.Service_Policy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Service_PolicyService {
    Service_PolicyDao spDao = new Service_PolicyDao();
    NotificationService ns = new NotificationService();

    public List<Service_Policy> getAllService_Policys() {
        return spDao.getListService_Policy();
    }

    public List<Service_Policy> getActivePolicyTitles() {
        return spDao.getActivePolicyTitles();
    }

    public List<Service_Policy> getActiveServiceTitles() {
        return spDao.getActiveServiceTitles();
    }

    public Service_Policy getService_PolicyById(int spid) {
        return spDao.getService_PolicyById(spid);
    }

    public boolean updateService_PolicyStatus(int spid) {
        Service_Policy sp = spDao.getService_PolicyById(spid);
        //check tồn tại
        if (sp == null) {
            return false;
        }

        //đảo trạng thái
        sp.setStatus(!sp.isStatus());
        spDao.updateStatus(sp.getSpid());
        return true;
    }

    public boolean addServicePolicy(String title, String content, boolean isService) {
        LocalDateTime now = LocalDateTime.now();
        String lastedUpdate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        boolean status = true;

        spDao.addService_Policy(title, content, lastedUpdate, isService, status);
        // Tạo thông báo
        if (isService) ns.createGlobalNotification("All", "Có dịch vụ mới", "Dịch vụ " + title + " vừa được thêm vào!");
        else ns.createGlobalNotification("All", "Có chính sách mới", "Chính sách " + title + " vừa được thêm vào!");

        return true;
    }
}
