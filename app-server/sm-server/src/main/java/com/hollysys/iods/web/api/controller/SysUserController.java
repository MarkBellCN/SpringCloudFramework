package com.hollysys.iods.web.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hollysys.iods.data.api.provider.SysUserProvider;
import com.hollysys.iods.web.api.dto.SysUserQueryDTO;
import com.hollysys.iods.web.api.exception.SmServerErrorType;
import com.hollysys.platform.common.core.exception.SystemErrorType;
import com.hollysys.platform.common.core.vo.Result;
import com.hollysys.platform.common.web.controller.BaseController;
import com.hollysys.platform.common.web.params.PageQueryParams;
import com.hollysys.platform.common.web.proxy.RpcProxyServiceFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;


@Api(value = "用户信息 API", tags = {"用户信息 API"})
@Slf4j
@RestController
@RequestMapping("/v1/user")
public class SysUserController extends BaseController {
    @Reference(check = false)
    private SysUserProvider sysUserProvider;

    @ApiOperation(value = "分页查询用户信息")
    @PostMapping("/pageQuery")
    public Result pageQuery(@RequestBody @Validated PageQueryParams<SysUserQueryDTO> params){
        Map page = new HashMap();
        page.put("paramType","com.baomidou.mybatisplus.core.metadata.IPage");
        page.put("paramValue",params.getPage());
        Object o = RpcProxyServiceFactory.getInstance().invoke("com.hollysys.iods.data.api.provider.SysUserProvider","page",page);
        IPage result = JSON.parseObject(JSONObject.toJSONString(o), Page.class);
        return Result.success(getResultByPage(result));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户编号", dataType = "String", required = true),
    })
    @ApiOperation(value = "根据用户ID查询用户信息")
    @GetMapping("/{userId}")
    public Result getSysUserByUserId(@PathVariable("userId") String userId){
        return Result.success(sysUserProvider.getSysUserByUserId(userId));
    }

    @ApiOperation(value = "添加用户信息")
    @PostMapping()
    public Result save(){
        return Result.fail(SmServerErrorType.SAVE_DATA_ERROR);
    }

    @ApiOperation(value = "修改用户信息")
    @PutMapping()
    public Result update(){
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

}
