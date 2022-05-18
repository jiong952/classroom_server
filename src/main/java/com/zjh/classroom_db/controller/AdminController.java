package com.zjh.classroom_db.controller;

import com.zjh.classroom_db.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 张俊鸿
 * @description:
 * @since 2022-05-17 23:31
 */
@RestController
@CrossOrigin
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public Object home(String username,String password) {
        System.out.println(username);
        System.out.println(password);
        boolean status = adminService.login(username, password);
        return status;
    }
}

