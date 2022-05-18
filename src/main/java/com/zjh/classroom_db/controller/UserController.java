package com.zjh.classroom_db.controller;

import com.zjh.classroom_db.pojo.User;
import com.zjh.classroom_db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 张俊鸿
 * @description: 用户管理
 * @since 2022-05-18 15:08
 */
@RestController()
@CrossOrigin
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/get")
    public Object getUserList(String query,int pagenum,int pagesize){
        List<User> userList = userService.getUserList(query, pagenum, pagesize);
        int total = userService.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("userList",userList);
        map.put("total",total);
        return map;
    }

    @RequestMapping("/delete")
    public Object removeUser(int id){
        boolean status = userService.remove(id);
        return status;
    }

    /**
     * 更新角色
     * 1学生 2老师
     * @param user 用户
     * @return {@link Object}
     */
    @RequestMapping("/role")
    public Object updateRole(User user){
        boolean status = userService.updateRole(user);
        return status;
    }

    @RequestMapping("/add")
    public Object addUser(User user){
        boolean status = userService.addUser(user);
        return status;
    }

    @RequestMapping("/update")
    public Object updateUser(User user){
        boolean status = userService.updateUser(user);
        return status;
    }

    /**
     * 更新状态
     * 1有效 0无效
     * @param user 用户
     * @return {@link Object}
     */
    @RequestMapping("/state")
    public Object updateState(User user){
        boolean status = userService.updateState(user);
        return status;
    }
}
