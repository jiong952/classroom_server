package com.zjh.classroom_db.service;

import com.zjh.classroom_db.mapper.AdminMapper;
import com.zjh.classroom_db.pojo.Admin;
import com.zjh.classroom_db.pojo.AdminExample;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author 张俊鸿
 * @description: 登录测试
 * @since 2022-05-17 23:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class adminTest {
    @Autowired
    private AdminMapper adminMapper;
    @Test
    public void testLogin(){
        Admin admin = adminMapper.selectByPrimaryKey("admin");
        Assert.assertEquals(admin.getPassword(),"123456");
//        System.out.println(adminService.login("admin", "123456"));
    }
}
