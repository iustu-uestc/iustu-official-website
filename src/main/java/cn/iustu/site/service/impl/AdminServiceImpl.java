package cn.iustu.site.service.impl;

import cn.iustu.site.common.entity.Admin;
import cn.iustu.site.dao.AdminRepository;
import cn.iustu.site.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;


    @Override
    public Admin get() {
        try{
            return adminRepository.findById(1).get();
        }catch (NoSuchElementException e){
            return null;
        }
    }

    @Transactional
    @Override
    public void update(Admin admin) {
        adminRepository.save(admin);
    }
}
