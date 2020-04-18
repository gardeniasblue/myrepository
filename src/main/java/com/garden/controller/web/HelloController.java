package com.garden.controller.web;

import com.garden.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(tags ="主页相关接口")
@RestController
public class HelloController {


    @ApiOperation(value="主页接口")
    @RequestMapping(value="index6",method= RequestMethod.POST)
    public ResponseEntity index8(User user) {
        Map map = new HashMap();
        map.put("username",user.getUsername());
        map.put("password",user.getPassworld());
        map.put("email",user.getEmail());
        return ResponseEntity.ok(map);
    }
}
