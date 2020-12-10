package com.cn.xb.springbootmybatis.service.impl;

import com.cn.xb.springbootmybatis.entity.TestDepartment;
import com.cn.xb.springbootmybatis.dao.TestDepartmentDao;
import com.cn.xb.springbootmybatis.service.TestDepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TestDepartment)表服务实现类
 *
 * @author makejava
 * @since 2020-09-22 14:28:47
 */
@Service("testDepartmentService")
public class TestDepartmentServiceImpl implements TestDepartmentService {
    @Resource
    private TestDepartmentDao testDepartmentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param departmentId 主键
     * @return 实例对象
     */
    @Override
    public TestDepartment queryById(String departmentId) {
        return this.testDepartmentDao.queryById(departmentId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TestDepartment> queryAllByLimit(int offset, int limit) {
        return this.testDepartmentDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param testDepartment 实例对象
     * @return 实例对象
     */
    @Override
    public TestDepartment insert(TestDepartment testDepartment) {
        this.testDepartmentDao.insert(testDepartment);
        return testDepartment;
    }

    /**
     * 修改数据
     *
     * @param testDepartment 实例对象
     * @return 实例对象
     */
    @Override
    public TestDepartment update(TestDepartment testDepartment) {
        this.testDepartmentDao.update(testDepartment);
        return this.queryById(testDepartment.getDepartmentId());
    }

    /**
     * 通过主键删除数据
     *
     * @param departmentId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String departmentId) {
        return this.testDepartmentDao.deleteById(departmentId) > 0;
    }
}