package org.example.projectweb.service;

import org.example.projectweb.dao.VoucherDao;
import org.example.projectweb.model.Voucher;

import java.util.List;

public class VoucherService {
    final VoucherDao vDao = new VoucherDao();

    public boolean addVoucher(String type, double discount, double condition, String expiredDate) {
        if ("phan_tram".equals(type)) {
            discount = discount / 100.0; // 44 → 0.44
        }

        String imagePath = getImageByType(type);
        boolean status = true;

        vDao.addVoucher( type, discount, condition, expiredDate, imagePath, status);
        return đ;
    }

    private String getImageByType(String type) {
        switch (type) {
            case "mien_phi_van_chuyen":
                return "/images/voucher/free_ship.png";
            case "giam_gia":
                return "/images/voucher/sale_d.png";
            case "phan_tram":
                return "/images/voucher/sale_p.png";
            default:
                return "/images/voucher/default.png";
        }
    }

    public List<Voucher> getAllVouchers() {
        return vDao.getListVoucher();
    }

    public boolean updateVoucherStatus(int vid) {
        Voucher voucher = vDao.getVoucherById(vid);
        //check tồn tại
        if (voucher == null) {
            return false;
        }

        //đảo trạng thái
        voucher.setStatus(!voucher.isStatus());
        vDao.updateStatus(voucher.getVid());
        return true;
    }

    public List<Voucher> getVouchersByUid(int uid) {
        return vDao.getVouchersByUid(uid);
    }
}
