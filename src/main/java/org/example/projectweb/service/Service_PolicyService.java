package org.example.projectweb.service;

import org.example.projectweb.dao.Service_PolicyDao;
import org.example.projectweb.model.Service_Policy;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Service_PolicyService {
    Service_PolicyDao spDao = new Service_PolicyDao();

    public List<Service_Policy> getAllService_Policys() {
        return spDao.getListService_Policy();
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
        return true;
    }
}
