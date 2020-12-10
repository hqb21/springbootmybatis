package com.cn.xb.springbootmybatis.dao;

import com.cn.xb.springbootmybatis.entity.TestPerson;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TestPerson)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-22 14:28:51
 */
public interface TestPersonDao {

    /**
     * 通过ID查询单条数据
     *
     * @param personId 主键
     * @return 实例对象
     */
    TestPerson queryById(String personId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TestPerson> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param testPerson 实例对象
     * @return 对象列表
     */
    List<TestPerson> queryAll(TestPerson testPerson);

    /**
     * 新增数据
     *
     * @param testPerson 实例对象
     * @return 影响行数
     */
    int insert(TestPerson testPerson);

    /**
     * 修改数据
     *
     * @param testPerson 实例对象
     * @return 影响行数
     */
    int update(TestPerson testPerson);

    /**
     * 通过主键删除数据
     *
     * @param personId 主键
     * @return 影响行数
     */
    int deleteById(String personId);

}