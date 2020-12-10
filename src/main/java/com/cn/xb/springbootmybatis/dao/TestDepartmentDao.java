package com.cn.xb.springbootmybatis.dao;

import com.cn.xb.springbootmybatis.entity.TestDepartment;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TestDepartment)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-22 14:28:46
 */
public interface TestDepartmentDao {

    /**
     * 通过ID查询单条数据
     *
     * @param departmentId 主键
     * @return 实例对象
     */
    TestDepartment queryById(String departmentId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TestDepartment> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param testDepartment 实例对象
     * @return 对象列表
     */
    List<TestDepartment> queryAll(TestDepartment testDepartment);

    /**
     * 新增数据
     *
     * @param testDepartment 实例对象
     * @return 影响行数
     */
    int insert(TestDepartment testDepartment);

    /**
     * 修改数据
     *
     * @param testDepartment 实例对象
     * @return 影响行数
     */
    int update(TestDepartment testDepartment);

    /**
     * 通过主键删除数据
     *
     * @param departmentId 主键
     * @return 影响行数
     */
    int deleteById(String departmentId);

}