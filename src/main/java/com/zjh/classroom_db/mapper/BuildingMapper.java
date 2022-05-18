package com.zjh.classroom_db.mapper;

import com.zjh.classroom_db.pojo.Building;
import com.zjh.classroom_db.pojo.BuildingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuildingMapper {
    long countByExample(BuildingExample example);

    int deleteByExample(BuildingExample example);

    int deleteByPrimaryKey(Integer buildingId);

    int insert(Building record);

    int insertSelective(Building record);

    List<Building> selectByExample(BuildingExample example);

    Building selectByPrimaryKey(Integer buildingId);

    int updateByExampleSelective(@Param("record") Building record, @Param("example") BuildingExample example);

    int updateByExample(@Param("record") Building record, @Param("example") BuildingExample example);

    int updateByPrimaryKeySelective(Building record);

    int updateByPrimaryKey(Building record);
}