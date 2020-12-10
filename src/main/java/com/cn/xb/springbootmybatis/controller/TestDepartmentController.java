package com.cn.xb.springbootmybatis.controller;

import com.cn.xb.springbootmybatis.entity.TestDepartment;
import com.cn.xb.springbootmybatis.service.TestDepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TestDepartment)表控制层
 *
 * @author makejava
 * @since 2020-09-22 14:28:48
 */
@RestController
@RequestMapping("testDepartment")
@Api("(TestDepartment)表控制层")
public class TestDepartmentController {
    /**
     * 服务对象
     */
    @Resource
    private TestDepartmentService testDepartmentService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    /*   name：传入字段名称
     value：传入参数说明
     required：是否必传
     dataType：传入类型 int、String、实体类、Map等等。。。。
     paramType：header-->放在请求头。请求参数的获取：@RequestHeader(代码中接收注解)
                query-->用于get请求的参数拼接。请求参数的获取：@RequestParam(代码中接收注解)
                path（用于restful接口）-->请求参数的获取：@PathVariable(代码中接收注解)
                body-->放在请求体。请求参数的获取：@RequestBody(代码中接收注解)
                form（不常用）
 */
    @ApiImplicitParam(name = "id", value = "部门ID", required = true, dataType = "String" , paramType = "path")
    // value：方法名称   notes：方法描述
    @ApiOperation(value="查询部门", notes="通过部门ID查询单条数据")
    @GetMapping("selectOne/{id}")
    public TestDepartment selectOne(@PathVariable String id) {
        return this.testDepartmentService.queryById(id);
    }

}