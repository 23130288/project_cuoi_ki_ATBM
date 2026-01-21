package org.example.projectweb.service;

import org.example.projectweb.dao.VoucherDao;
import org.example.projectweb.model.Voucher;
import java.util.List;

public class VoucherService {
    final VoucherDao vDao = new VoucherDao();

    public List<Voucher> getAllVouchers() {
        return vDao.getListVoucher();
    }

    public List<Voucher> getVouchersByUid(int uid) {
        return vDao.getVouchersByUid(uid);
    }
}
