package com.cn.xb.springbootmybatis.entity;

import java.io.Serializable;

/**
 * (TestDepartment)实体类
 *
 * @author huiqb
 * @since 2020-09-22 14:28:44
 */
public class TestDepartment implements Serializable {
    private static final long serialVersionUID = -23732098082889741L;

    public TestDepartment(String departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public TestDepartment() {
    }

    //部门编号
    private String departmentId;
    //部门名称
    private String departmentName;

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "TestDepartment{" +
                "departmentId='" + departmentId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}