package com.cn.xb.springbootmybatis.service;

import com.cn.xb.springbootmybatis.entity.TestPerson;
import java.util.List;

/**
 * (TestPerson)表服务接口
 *
 * @author makejava
 * @since 2020-09-22 14:28:51
 */
public interface TestPersonService {

    /**
     * 通过ID查询单条数据
     *
     * @param personId 主键
     * @return 实例对象
     */
    TestPerson queryById(String personId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TestPerson> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param testPerson 实例对象
     * @return 实例对象
     */
    TestPerson insert(TestPerson testPerson);

    /**
     * 修改数据
     *
     * @param testPerson 实例对象
     * @return 实例对象
     */
    TestPerson update(TestPerson testPerson);

    /**
     * 通过主键删除数据
     *
     * @param personId 主键
     * @return 是否成功
     */
    boolean deleteById(String personId);

}