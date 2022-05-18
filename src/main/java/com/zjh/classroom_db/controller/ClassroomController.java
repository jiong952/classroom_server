package com.zjh.classroom_db.controller;

import com.zjh.classroom_db.pojo.Classroom;
import com.zjh.classroom_db.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 张俊鸿
 * @description: 教室管理
 * @since 2022-05-18 16:59
 */
@RestController()
@RequestMapping("/classroom")
public class ClassroomController {
    @Autowired
    private ClassroomService classroomService;
    @RequestMapping("/get")
    public Object getClassroomList(String query,int pagenum,int pagesize){
        List<Classroom> classroomList = classroomService.getClassroomList(query, pagenum, pagesize);
        return classroomList;
    }

    @RequestMapping("/delete")
    public Object removeClassroom(int classroomId){
        boolean status = classroomService.remove(classroomId);
        return status;
    }

    @RequestMapping("/add")
    public Object addClassroom(Classroom classroom){
        boolean status = classroomService.addClassroom(classroom);
        return status;
    }
    @RequestMapping("/update")
    public Object updateClassroom(Classroom classroom){
        boolean status = classroomService.updateClassroom(classroom);
        return status;
    }
}
