package com.cn.xb.springbootmybatis.service.impl;

import com.cn.xb.springbootmybatis.entity.TestPerson;
import com.cn.xb.springbootmybatis.dao.TestPersonDao;
import com.cn.xb.springbootmybatis.service.TestPersonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TestPerson)表服务实现类
 *
 * @author makejava
 * @since 2020-09-22 14:28:51
 */
@Service("testPersonService")
public class TestPersonServiceImpl implements TestPersonService {
    @Resource
    private TestPersonDao testPersonDao;

    /**
     * 通过ID查询单条数据
     *
     * @param personId 主键
     * @return 实例对象
     */
    @Override
    public TestPerson queryById(String personId) {
        return this.testPersonDao.queryById(personId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TestPerson> queryAllByLimit(int offset, int limit) {
        return this.testPersonDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param testPerson 实例对象
     * @return 实例对象
     */
    @Override
    public TestPerson insert(TestPerson testPerson) {
        this.testPersonDao.insert(testPerson);
        return testPerson;
    }

    /**
     * 修改数据
     *
     * @param testPerson 实例对象
     * @return 实例对象
     */
    @Override
    public TestPerson update(TestPerson testPerson) {
        this.testPersonDao.update(testPerson);
        return this.queryById(testPerson.getPersonId());
    }

    /**
     * 通过主键删除数据
     *
     * @param personId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String personId) {
        return this.testPersonDao.deleteById(personId) > 0;
    }
}