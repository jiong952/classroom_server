package com.zjh.classroom_db.controller;

import com.zjh.classroom_db.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 张俊鸿
 * @description:
 * @since 2022-05-17 23:31
 */
@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public Object home(String username,String password) {
        boolean status = adminService.login(username, password);
        return status;
    }
}

