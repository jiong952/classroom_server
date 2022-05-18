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
 * @description: 校区管理
 * @since 2022-05-18 16:16
 */
@Service
public class CampusService {
    @Autowired
    private CampusMapper campusMapper;

    @Autowired
    private ClassroomMapper classroomMapper;

    @Autowired
    private BuildingMapper buildingMapper;

    /**
     * 获得校区列表
     *
     * @param query    查询
     * @param pageNum  页面num
     * @param pageSize 页面大小
     * @return {@link List}<{@link Campus}>
     */
    public List<Campus> getCampusList(String query, int pageNum, int pageSize){
        List<Campus> campusList;
        CampusExample example = new CampusExample();
        if(query != null && !query.equals("")){
            query = "%" + query + "%";
            example.createCriteria().andCampusNameLike(query);
        }
        PageHelper.startPage(pageNum,pageSize);
        campusList = campusMapper.selectByExample(example);
        for (Campus campus : campusList) {
            //加入数量
            BuildingExample buildingExample = new BuildingExample();
            buildingExample.createCriteria().andCampusIdEqualTo(campus.getCampusId());
            campus.setBuildingCount((int) buildingMapper.countByExample(buildingExample));
            ClassroomExample classroomExample = new ClassroomExample();
            classroomExample.createCriteria().andCampusIdEqualTo(campus.getCampusId());
            campus.setClassroomCount((int) classroomMapper.countByExample(classroomExample));
        }
        return campusList;
    }

    /**
     * 删除
     *
     * @param id id
     * @return boolean
     */
    public boolean remove(int id){
        boolean flag = false;
        int i = campusMapper.deleteByPrimaryKey(id);
        if(i > 0) flag = true;
        return flag;
    }

    /**
     * 增加校园
     *
     * @param campus 校园
     * @return boolean
     */
    public boolean addCampus(Campus campus){
        boolean flag = false;
        int i = campusMapper.insertSelective(campus);
        if(i > 0) flag = true;
        return flag;
    }

    /**
     * 更新校园
     *
     * @param campus 校园
     * @return boolean
     */
    public boolean updateCampus(Campus campus) {
        boolean flag = false;
        int i = campusMapper.updateByPrimaryKeySelective(campus);
        if(i > 0) flag = true;
        return flag;
    }

}
