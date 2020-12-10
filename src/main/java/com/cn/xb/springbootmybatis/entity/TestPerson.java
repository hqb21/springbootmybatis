package com.cn.xb.springbootmybatis.entity;

import java.io.Serializable;

/**
 * (TestPerson)实体类
 *
 * @author huiqb
 * @since 2020-09-22 14:28:51
 */
public class TestPerson implements Serializable {
    private static final long serialVersionUID = -39744106576938982L;

    public TestPerson(String personId, String personName, String personDepartmentId) {
        this.personId = personId;
        this.personName = personName;
        this.personDepartmentId = personDepartmentId;
    }

    public TestPerson() {
    }

    //人员编号
    private String personId;
    //人员名称
    private String personName;
    //人员所属部门编号
    private String personDepartmentId;


    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonDepartmentId() {
        return personDepartmentId;
    }

    public void setPersonDepartmentId(String personDepartmentId) {
        this.personDepartmentId = personDepartmentId;
    }

    @Override
    public String toString() {
        return "TestPerson{" +
                "personId='" + personId + '\'' +
                ", personName='" + personName + '\'' +
                ", personDepartmentId='" + personDepartmentId + '\'' +
                '}';
    }
}