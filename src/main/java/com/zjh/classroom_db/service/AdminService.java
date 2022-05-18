package com.zjh.classroom_db.service;

import com.zjh.classroom_db.mapper.AdminMapper;
import com.zjh.classroom_db.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 张俊鸿
 * @description: 登录功能
 * @since 2022-05-17 23:00
 */
@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    /**
     * 登录功能
     *
     * @param id  id
     * @param pwd 密码
     * @return boolean
     */
    public boolean login(String id,String pwd){
        boolean flag = false;
        Admin admin = adminMapper.selectByPrimaryKey(id);
        if(admin != null && pwd.equals(admin.getPassword())) flag = true;
        return flag;
    }
}
