package org.example.projectweb.dao;

import org.example.projectweb.model.Service_Policy;

import java.util.List;

public class Service_PolicyDao extends BaseDao {

    public void addService_Policy(String title, String conten, String lastedUpdate, boolean isService, boolean status) {
        get().useHandle(h -> h.createUpdate("INSERT INTO service_policy (conten ,title, lasted_update, type, status) VALUES (:conten, :title, :lasted_update, :type, :status)")
                .bind("title", title).bind("conten", conten).bind("lasted_update", lastedUpdate).bind("type", isService).bind("status", status).execute()
        );
    }

    public List<Service_Policy> getListService_Policy() {
        return get().withHandle(h -> h.createQuery("select spid, title, lasted_update, type, status from service_policy")
                .mapToBean(Service_Policy.class).list());
    }

    public Service_Policy getService_PolicyById(int spid) {
        return get().withHandle(h ->
                h.createQuery("select  spid, conten ,title, lasted_update, type, status from service_policy where spid = :spid")
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
