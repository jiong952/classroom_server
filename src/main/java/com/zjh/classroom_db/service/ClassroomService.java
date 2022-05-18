package com.zjh.classroom_db.service;

import com.github.pagehelper.PageHelper;
import com.zjh.classroom_db.mapper.BuildingMapper;
import com.zjh.classroom_db.mapper.CampusMapper;
import com.zjh.classroom_db.mapper.ClassroomMapper;
import com.zjh.classroom_db.mapper.UserMapper;
import com.zjh.classroom_db.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.Map;

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

    @Autowired
    private CampusMapper campusMapper;

    @Autowired
    private BuildingMapper buildingMapper;

    String json = "{\n" +
            "        //湿度\n" +
            "        Humidity: 11,\n" +
            "        //温度\n" +
            "        Temperature: 11,\n" +
            "        //火灾\n" +
            "        fire_state: \"safe\",\n" +
            "        //火灾\n" +
            "        smoke_state: \"safe\",\n" +
            "        //深度学习状态\n" +
            "        deep_state: {\n" +
            "          have_person: {\n" +
            "            //教室人数\n" +
            "            person_nums: 0,\n" +
            "            //1区域有没有人\n" +
            "            area_1: 0,\n" +
            "            area_2: 0,\n" +
            "            area_3: 0,\n" +
            "            area_4: 0\n" +
            "          },\n" +
            "          person_state: {\n" +
            "            //人物状态\n" +
            "            person_1: 0,\n" +
            "            person_2: 0,\n" +
            "            person_3: 0,\n" +
            "            person_4: 0\n" +
            "          }\n" +
            "        },\n" +
            "        web_state: {\n" +
            "          //为0表示智能模式，为1表示网页控制\n" +
            "          web_ctrl: false,\n" +
            "          ctrl_state: {\n" +
            "            light_state: {\n" +
            "              //灯的状态\n" +
            "              light_1: 1,\n" +
            "              light_2: 1,\n" +
            "              light_3: 1,\n" +
            "              light_4: 1\n" +
            "            },\n" +
            "            //风扇状态\n" +
            "            fan_state: 1\n" +
            "          }\n" +
            "        }\n" +
            "      }";
    String json2 = "{\n" +
            "        //湿度\n" +
            "        Humidity: 11,\n" +
            "        //温度\n" +
            "        Temperature: 11,\n" +
            "        //火灾\n" +
            "        fire_state: \"safe\",\n" +
            "        //火灾\n" +
            "        smoke_state: \"safe\",\n" +
            "        //深度学习状态\n" +
            "        deep_state: {\n" +
            "          have_person: {\n" +
            "            //教室人数\n" +
            "            person_nums: 1,\n" +
            "            //1区域有没有人\n" +
            "            area_1: 1,\n" +
            "            area_2: 0,\n" +
            "            area_3: 0,\n" +
            "            area_4: 0\n" +
            "          },\n" +
            "          person_state: {\n" +
            "            //人物状态\n" +
            "            person_1: 0,\n" +
            "            person_2: 0,\n" +
            "            person_3: 0,\n" +
            "            person_4: 0\n" +
            "          }\n" +
            "        },\n" +
            "        web_state: {\n" +
            "          //为0表示智能模式，为1表示网页控制\n" +
            "          web_ctrl: true,\n" +
            "          ctrl_state: {\n" +
            "            light_state: {\n" +
            "              //灯的状态\n" +
            "              light_1: 1,\n" +
            "              light_2: 1,\n" +
            "              light_3: 1,\n" +
            "              light_4: 1\n" +
            "            },\n" +
            "            //风扇状态\n" +
            "            fan_state: 1\n" +
            "          }\n" +
            "        }\n" +
            "      }";
    Map map = (Map)JSON.parse(json);
    Map map2 = (Map)JSON.parse(json2);
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
        for (int i = 0; i < classroomList.size(); i++) {
            //管理员
            User user = userMapper.selectByPrimaryKey(classroomList.get(i).getAdminId());
            classroomList.get(i).setAdminName(user.getName());
            //校区
            classroomList.get(i).setCampusName(campusMapper.selectByPrimaryKey(classroomList.get(i).getCampusId()).getCampusName());
            //楼宇
            classroomList.get(i).setBuildingName(buildingMapper.selectByPrimaryKey(classroomList.get(i).getBuildingId()).getBuildingName());
            if(i == 1){
                classroomList.get(i).setState(map2);
            }else {
                classroomList.get(i).setState(map);
            }

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

    /**
     * 返回总数
     *
     * @return int
     */
    public int getTotal(){
        return  (int)classroomMapper.countByExample(null);
    }
}
