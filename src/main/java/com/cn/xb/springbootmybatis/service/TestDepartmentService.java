package com.cn.xb.springbootmybatis.service;

import com.cn.xb.springbootmybatis.entity.TestDepartment;
import java.util.List;

/**
 * (TestDepartment)表服务接口
 *
 * @author makejava
 * @since 2020-09-22 14:28:47
 */
public interface TestDepartmentService {

    /**
     * 通过ID查询单条数据
     *
     * @param departmentId 主键
     * @return 实例对象
     */
    TestDepartment queryById(String departmentId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TestDepartment> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param testDepartment 实例对象
     * @return 实例对象
     */
    TestDepartment insert(TestDepartment testDepartment);

    /**
     * 修改数据
     *
     * @param testDepartment 实例对象
     * @return 实例对象
     */
    TestDepartment update(TestDepartment testDepartment);

    /**
     * 通过主键删除数据
     *
     * @param departmentId 主键
     * @return 是否成功
     */
    boolean deleteById(String departmentId);

}