package com.zjh.classroom_db.controller;

import com.zjh.classroom_db.pojo.Building;
import com.zjh.classroom_db.pojo.Campus;
import com.zjh.classroom_db.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 张俊鸿
 * @description: 楼宇管理
 * @since 2022-05-18 16:49
 */
@RestController()
@CrossOrigin
@RequestMapping("/building")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;

    @RequestMapping("/get")
    public Object getBuildingList(String query,int pagenum,int pagesize){
        List<Building> buildingList = buildingService.getBuildingList(query, pagenum, pagesize);
        int total = buildingService.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("buildingList",buildingList);
        map.put("total",total);
        return map;
    }

    @RequestMapping("/delete")
    public Object removeBuilding(int buildingId){
        boolean status = buildingService.remove(buildingId);
        return status;
    }

    @RequestMapping("/add")
    public Object addBuilding(Building building){
        boolean status = buildingService.addBuilding(building);
        return status;
    }

    @RequestMapping("/update")
    public Object updateBuilding(Building building){
        boolean status = buildingService.updateBuilding(building);
        return status;
    }
}
