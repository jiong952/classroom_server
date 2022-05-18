package com.zjh.classroom_db.pojo;

import java.util.Date;

public class Campus {
    private Integer campusId;

    private String campusName;

    private Date createTime;

    private Date updateTime;

    public int buildingCount;

    public int classroomCount;

    public int getClassroomCount() {
        return classroomCount;
    }

    public void setClassroomCount(int classroomCount) {
        this.classroomCount = classroomCount;
    }

    public int getBuildingCount() {
        return buildingCount;
    }

    public void setBuildingCount(int buildingCount) {
        this.buildingCount = buildingCount;
    }

    public Integer getCampusId() {
        return campusId;
    }

    public void setCampusId(Integer campusId) {
        this.campusId = campusId;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}