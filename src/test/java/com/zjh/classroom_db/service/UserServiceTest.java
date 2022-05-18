package com.zjh.classroom_db.service;

import com.zjh.classroom_db.mapper.UserMapper;
import com.zjh.classroom_db.pojo.User;
import com.zjh.classroom_db.pojo.UserExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
//    private UserMapper userMapper;

    @Test
    public void getUserList() {
        UserExample userExample = new UserExample();
//        userExample.createCriteria().andNameLike("%工%")
//        userMapper.selectByExample();
        List<User> userList = userService.getUserList("", 1, 5);
        for (User user : userList) {
            System.out.println(user);
        }
    }
}