package com.hollysys.platform.auth.server.permission.controller;

import com.hollysys.platform.common.web.controller.BaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hollysys.platform.common.core.exception.ApiException;
import com.hollysys.platform.common.web.controller.BaseController;
import com.hollysys.platform.auth.data.api.entity.PmModuleAction;
import com.hollysys.platform.auth.data.api.provider.PmModuleActionProvider;
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
@Api(value="模块行为表 API", tags="{模块行为表}")
@Slf4j
@RestController
@RequestMapping("/v1/auth/pm-module-action")
public class PmModuleActionController extends BaseController {

    @Reference(check = false,timeout = 3000)
    private PmModuleActionProvider pmModuleActionProvider;

    /**
     * 分页查询PmModuleAction
     */
    @ApiOperation(value = "分页查询")
    @PostMapping("/pageQuery")
    public Result pageQuery(@RequestBody @Validated PageQueryParams<PmModuleAction> params){
        IPage pageResult = pmModuleActionProvider.page(params.getPage());
        return Result.success(getResultByPage(pageResult));
    }

    /**
     * 新增PmModuleAction
     */
    @ApiOperation(value = "新增")
    @PostMapping
    public Result save(@Validated  @RequestBody PmModuleAction pmModuleAction, BindingResult result) throws ApiException{
        return pmModuleActionProvider.save(pmModuleAction)?Result.success(): Result.fail();
    }
   /**
     * 修改PmModuleAction
     */
    @ApiOperation(value = "修改")
    @PutMapping
    public Result update(@Validated PmModuleAction pmModuleAction, BindingResult result) throws ApiException{
        return pmModuleActionProvider.updateById(pmModuleAction) ?Result.success(): Result.fail();
    }

    /**
     * 删除PmModuleAction
     */
    @ApiOperation(value = "删除")
    @DeleteMapping
    public Result delete(@Validated @RequestBody PmModuleAction pmModuleAction, BindingResult result) throws ApiException{
        return  pmModuleActionProvider.removeById(pmModuleAction)?Result.success(): Result.fail();
    }

}
