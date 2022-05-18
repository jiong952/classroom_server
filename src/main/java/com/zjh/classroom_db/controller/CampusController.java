package com.zjh.classroom_db.controller;

import com.zjh.classroom_db.pojo.Campus;
import com.zjh.classroom_db.service.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 张俊鸿
 * @description: 校区管理
 * @since 2022-05-18 16:16
 */
@RestController()
@CrossOrigin
@RequestMapping("/campus")
public class CampusController {
    @Autowired
    private CampusService campusService;
    @RequestMapping("/get")
    public Object getCampusList(String query,int pagenum,int pagesize){
        List<Campus> campusList = campusService.getCampusList(query, pagenum, pagesize);
        int total = campusService.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("campusList",campusList);
        map.put("total",total);
        return map;
    }
    @RequestMapping("/delete")
    public Object removeCampus(int campusId){
        boolean status = campusService.remove(campusId);
        return status;
    }

    @RequestMapping("/add")
    public Object addCampus(Campus campus){
        boolean status = campusService.addCampus(campus);
        return status;
    }

    @RequestMapping("/update")
    public Object updateCampus(Campus campus){
        boolean status = campusService.updateCampus(campus);
        return status;
    }
}
