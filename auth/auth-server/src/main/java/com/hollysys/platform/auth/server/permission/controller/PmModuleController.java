package com.hollysys.platform.auth.server.permission.controller;

import com.hollysys.platform.common.web.controller.BaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hollysys.platform.common.core.exception.ApiException;
import com.hollysys.platform.common.web.controller.BaseController;
import com.hollysys.platform.auth.data.api.entity.PmModule;
import com.hollysys.platform.auth.data.api.provider.PmModuleProvider;
import com.hollysys.platform.common.core.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hollysys.platform.common.web.params.PageQueryParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;



/**
 *
 * @author Gen Code
 * @since 2019-12-17
 */
@Api(value="系统模块表 API", tags="{系统模块表}")
@Slf4j
@RestController
@RequestMapping("/v1/auth/pm-module")
public class PmModuleController extends BaseController {

    @Reference(check = false,timeout = 3000)
    private PmModuleProvider pmModuleProvider;

    /**
     * 分页查询PmModule
     */
    @ApiOperation(value = "分页查询")
    @PostMapping("/pageQuery")
    public Result pageQuery(@RequestBody @Validated PageQueryParams<PmModule> params){
        IPage pageResult = pmModuleProvider.page(params.getPage());
        return Result.success(getResultByPage(pageResult));
    }

    /**
     * 新增PmModule
     */
    @ApiOperation(value = "新增")
    @PostMapping
    public Result save(@Validated  @RequestBody PmModule pmModule, BindingResult result) throws ApiException{
        return pmModuleProvider.save(pmModule)?Result.success(): Result.fail();
    }
   /**
     * 修改PmModule
     */
    @ApiOperation(value = "修改")
    @PutMapping
    public Result update(@Validated PmModule pmModule, BindingResult result) throws ApiException{
        return pmModuleProvider.updateById(pmModule) ?Result.success(): Result.fail();
    }

    /**
     * 删除PmModule
     */
    @ApiOperation(value = "删除")
    @DeleteMapping
    public Result delete(@Validated @RequestBody PmModule pmModule, BindingResult result) throws ApiException{
        return  pmModuleProvider.removeById(pmModule)?Result.success(): Result.fail();
    }

}
