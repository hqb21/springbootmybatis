package com.cn.xb.springbootmybatis.controller;

import com.cn.xb.springbootmybatis.client.RabbitMqServiceClient;
import com.cn.xb.springbootmybatis.entity.TestPerson;
import com.cn.xb.springbootmybatis.service.TestPersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * (TestPerson)表控制层
 *
 * @author makejava
 * @since 2020-09-22 14:28:51
 */
@RestController
@RequestMapping("testPerson")
@Api("(TestPerson)表控制层")
public class TestPersonController {
    /**
     * 服务对象
     */
    @Resource
    private TestPersonService testPersonService;

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
    //@ApiIgnore  可以用在类、方法上，方法参数中，用来屏蔽某些接口或参数，使其不在页面上显示。
    @ApiImplicitParam(name = "id", value = "人员ID", required = true, dataType = "String" , paramType = "path")
    // value：方法名称   notes：方法描述
    @ApiOperation(value="查询人员", notes="通过人员ID查询单条数据")
    @GetMapping("selectOne/{id}")
    public TestPerson selectOne(@PathVariable String id) {
        return this.testPersonService.queryById(id);
    }

}