package com.zjh.classroom_db.service;

import com.github.pagehelper.PageHelper;
import com.zjh.classroom_db.mapper.ClassroomMapper;
import com.zjh.classroom_db.mapper.UserMapper;
import com.zjh.classroom_db.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张俊鸿
 * @description:教室管理
 * @since 2022-05-18 16:59
 */
@Service
public class ClassroomService {
    @Autowired
    private ClassroomMapper classroomMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 让教室列表
     *
     * @param query    查询
     * @param pageNum  页面num
     * @param pageSize 页面大小
     * @return {@link List}<{@link Classroom}>
     */
    public List<Classroom> getClassroomList(String query, int pageNum, int pageSize){
        List<Classroom> classroomList;
        ClassroomExample example = new ClassroomExample();
        if(query != null && !query.equals("")){
            query = "%" + query + "%";
            example.createCriteria().andClassroomNameLike(query);
        }
        PageHelper.startPage(pageNum,pageSize);
        classroomList = classroomMapper.selectByExample(example);
        for (Classroom classroom : classroomList) {
            User user = userMapper.selectByPrimaryKey(classroom.getAdminId());
            classroom.setAdminName(user.getName());
        }
        return classroomList;
    }

    /**
     * 删除
     *
     * @param id id
     * @return boolean
     */
    public boolean remove(int id){
        boolean flag = false;
        int i = classroomMapper.deleteByPrimaryKey(id);
        if(i > 0) flag = true;
        return flag;
    }

    /**
     * 增加课堂
     *
     * @param classroom 教室
     * @return boolean
     */
    public boolean addClassroom(Classroom classroom){
        boolean flag = false;
        int i = classroomMapper.insertSelective(classroom);
        if(i > 0) flag = true;
        return flag;
    }

    /**
     * 更新教室
     *
     * @param classroom 教室
     * @return boolean
     */
    public boolean updateClassroom(Classroom classroom){
        boolean flag = false;
        int i = classroomMapper.updateByPrimaryKeySelective(classroom);
        if(i > 0) flag = true;
        return flag;
    }
}
