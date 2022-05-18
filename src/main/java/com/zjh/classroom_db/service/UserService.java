package com.zjh.classroom_db.service;


import com.github.pagehelper.PageHelper;
import com.mysql.cj.util.StringUtils;
import com.zjh.classroom_db.mapper.UserMapper;
import com.zjh.classroom_db.pojo.User;
import com.zjh.classroom_db.pojo.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 张俊鸿
 * @description: 用户管理
 * @since 2022-05-18 14:27
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    /**
     * 获取用户列表
     *
     * @param query    查询内容
     * @param pageNum  页码
     * @param pageSize 页面大小
     * @return {@link List}<{@link User}>
     */
    public List<User> getUserList(String query, int pageNum, int pageSize){
        List<User> userList;
        UserExample example = new UserExample();
        if(query != null && !query.equals("")){
            query = "%" + query + "%";
            example.createCriteria().andNameLike(query);
        }
        PageHelper.startPage(pageNum,pageSize);
        userList = userMapper.selectByExample(example);
        return userList;
    }

    /**
     * 删除指定用户
     *
     * @param id id
     * @return boolean
     */
    public boolean remove(int id){
        boolean flag = false;
        int i = userMapper.deleteByPrimaryKey(id);
        if(i > 0) flag = true;
        return flag;
    }

    /**
     * 分配角色
     *
     * @param user 用户
     * @return boolean
     */
    public boolean updateRole(User user){
        boolean flag = false;
        int i = userMapper.updateByPrimaryKeySelective(user);
        if(i > 0) flag = true;
        return flag;
    }

    /**
     * 添加用户
     *
     * @param user 用户
     * @return boolean
     */
    public boolean addUser(User user){
        boolean flag = false;
        int i = userMapper.insertSelective(user);
        if(i > 0) flag = true;
        return flag;
    }

    /**
     * 更新用户
     *
     * @param user 用户
     * @return boolean
     */
    public boolean updateUser(User user) {
        boolean flag = false;
        int i = userMapper.updateByPrimaryKeySelective(user);
        if(i > 0) flag = true;
        return flag;
    }

    /**
     * 更新状态
     *
     * @param user 用户
     * @return boolean
     */
    public boolean updateState(User user){
        boolean flag = false;
        int i = userMapper.updateByPrimaryKeySelective(user);
        if(i > 0) flag = true;
        return flag;
    }
}
