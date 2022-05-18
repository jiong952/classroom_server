package com.zjh.classroom_db.service;

import com.github.pagehelper.PageHelper;
import com.zjh.classroom_db.mapper.BuildingMapper;
import com.zjh.classroom_db.mapper.CampusMapper;
import com.zjh.classroom_db.mapper.ClassroomMapper;
import com.zjh.classroom_db.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张俊鸿
 * @description: 楼宇管理
 * @since 2022-05-18 16:43
 */
@Service
public class BuildingService {
    @Autowired
    private ClassroomMapper classroomMapper;

    @Autowired
    private CampusMapper campusMapper;

    @Autowired
    private BuildingMapper buildingMapper;

    /**
     * 获取楼宇列表
     *
     * @param query    查询
     * @param pageNum  页面num
     * @param pageSize 页面大小
     * @return {@link List}<{@link Building}>
     */
    public List<Building> getBuildingList(String query, int pageNum, int pageSize){
        List<Building> buildingList;
        BuildingExample example = new BuildingExample();
        if(query != null && !query.equals("")){
            query = "%" + query + "%";
            example.createCriteria().andBuildingNameLike(query);
        }
        PageHelper.startPage(pageNum,pageSize);
        buildingList = buildingMapper.selectByExample(example);
        for (Building building : buildingList) {
            //加入数量
            ClassroomExample classroomExample = new ClassroomExample();
            classroomExample.createCriteria().andCampusIdEqualTo(building.getCampusId());
            building.setClassroomCount((int) classroomMapper.countByExample(classroomExample));
            //加入校区名称
            building.setCampusName(campusMapper.selectByPrimaryKey(building.getCampusId()).getCampusName());
        }
        return buildingList;
    }

    /**
     * 删除
     *
     * @param id id
     * @return boolean
     */
    public boolean remove(int id){
        boolean flag = false;
        int i = buildingMapper.deleteByPrimaryKey(id);
        if(i > 0) flag = true;
        return flag;
    }

    /**
     * 增加建筑
     *
     * @param building 建筑
     * @return boolean
     */
    public boolean addBuilding(Building building){
        boolean flag = false;
        int i = buildingMapper.insertSelective(building);
        if(i > 0) flag = true;
        return flag;
    }

    /**
     * 更新建筑
     *
     * @param building 建筑
     * @return boolean
     */
    public boolean updateBuilding(Building building){
        boolean flag = false;
        int i = buildingMapper.updateByPrimaryKeySelective(building);
        if(i > 0) flag = true;
        return flag;
    }
    /**
     * 返回总数
     *
     * @return int
     */
    public int getTotal(){
        return  (int)buildingMapper.countByExample(null);
    }
}
