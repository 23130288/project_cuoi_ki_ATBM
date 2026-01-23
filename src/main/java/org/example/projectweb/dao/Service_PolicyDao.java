package org.example.projectweb.dao;

import org.example.projectweb.model.Service_Policy;

import java.util.List;

public class Service_PolicyDao extends BaseDao {

    public void addService_Policy(String title, String content, String lastedUpdate, boolean isService, boolean status) {
        get().useHandle(h -> h.createUpdate("INSERT INTO service_policy (content ,title, lasted_update, type, status) VALUES (:content, :title, :lasted_update, :type, :status)")
                .bind("title", title).bind("content", content).bind("lasted_update", lastedUpdate).bind("type", isService).bind("status", status).execute()
        );
    }

    public List<Service_Policy> getListService_Policy() {
        return get().withHandle(h -> h.createQuery("select spid, title, lasted_update, type as service, status from service_policy")
                .mapToBean(Service_Policy.class).list());
    }

    public List<Service_Policy> getActivePolicyTitles() {
        return get().withHandle(h -> h.createQuery("select spid, title from service_policy WHERE type = false AND status = true  ORDER BY lasted_update DESC")
                .mapToBean(Service_Policy.class).list());
    }

    public List<Service_Policy> getActiveServiceTitles() {
        return get().withHandle(h -> h.createQuery("select spid, title from service_policy WHERE type = true AND status = true  ORDER BY lasted_update DESC")
                .mapToBean(Service_Policy.class).list());
    }

    public Service_Policy getService_PolicyById(int spid) {
        return get().withHandle(h ->
                h.createQuery("select  spid, content ,title, lasted_update, type, status from service_policy where spid = :spid")
                        .bind("spid", spid).mapToBean(Service_Policy.class).findOne().orElse(null)
        );
    }

    public void updateStatus(int spid) {
        get().useHandle(h ->
                h.createUpdate("UPDATE service_policy SET status = IF(status = 1, 0, 1) WHERE spid = :spid")
                        .bind("spid", spid)
                        .execute()
        );
    }
}
