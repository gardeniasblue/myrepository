package com.garden.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags ="用户相关接口")
@RestController
public class IndexController {


    @ApiOperation(value="银联支付主页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", defaultValue = "李四"),
            @ApiImplicitParam(name = "address", value = "用户地址", defaultValue = "深圳", required = true)
    }
    )
    @RequestMapping(value="index",method= RequestMethod.GET)
    public String index() {
        return "hello";
    }


    @ApiOperation(value="银联支付副业")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", defaultValue = "李四"),
            @ApiImplicitParam(name = "address", value = "用户地址", defaultValue = "深圳", required = true)
    }
    )
    @RequestMapping(value="index2",method= RequestMethod.GET)
    public String index2() {
        return "hello";
    }


    @ApiOperation(value="银联支付系统")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", defaultValue = "李四"),
            @ApiImplicitParam(name = "address", value = "用户地址", defaultValue = "深圳", required = true)
    }
    )
    @RequestMapping(value="index3",method= RequestMethod.GET)
    public String index3() {
        return "hello";
    }


    @ApiOperation(value="银联支Api")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", defaultValue = "李四"),
            @ApiImplicitParam(name = "address", value = "用户地址", defaultValue = "深圳", required = true)
    }
    )
    @RequestMapping(value="index4",method= RequestMethod.GET)
    public String index4() {
        return "hello";
    }


    @ApiOperation(value="银联支付主页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", defaultValue = "李四"),
            @ApiImplicitParam(name = "address", value = "用户地址", defaultValue = "深圳", required = true)
    }
    )
    @RequestMapping(value="index5",method= RequestMethod.GET)
    public String index5() {
        return "hello";
    }
}
