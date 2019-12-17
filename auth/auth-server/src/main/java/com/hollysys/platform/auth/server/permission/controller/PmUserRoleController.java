package com.hollysys.platform.auth.server.permission.controller;

import com.hollysys.platform.common.web.controller.BaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hollysys.platform.common.core.exception.ApiException;
import com.hollysys.platform.common.web.controller.BaseController;
import com.hollysys.platform.auth.data.api.entity.PmUserRole;
import com.hollysys.platform.auth.data.api.provider.PmUserRoleProvider;
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
@Api(value="用户角色 API", tags="{用户角色}")
@Slf4j
@RestController
@RequestMapping("/v1/auth/pm-user-role")
public class PmUserRoleController extends BaseController {

    @Reference(check = false,timeout = 3000)
    private PmUserRoleProvider pmUserRoleProvider;

    /**
     * 分页查询PmUserRole
     */
    @ApiOperation(value = "分页查询")
    @PostMapping("/pageQuery")
    public Result pageQuery(@RequestBody @Validated PageQueryParams<PmUserRole> params){
        IPage pageResult = pmUserRoleProvider.page(params.getPage());
        return Result.success(getResultByPage(pageResult));
    }

    /**
     * 新增PmUserRole
     */
    @ApiOperation(value = "新增")
    @PostMapping
    public Result save(@Validated  @RequestBody PmUserRole pmUserRole, BindingResult result) throws ApiException{
        return pmUserRoleProvider.save(pmUserRole)?Result.success(): Result.fail();
    }
   /**
     * 修改PmUserRole
     */
    @ApiOperation(value = "修改")
    @PutMapping
    public Result update(@Validated PmUserRole pmUserRole, BindingResult result) throws ApiException{
        return pmUserRoleProvider.updateById(pmUserRole) ?Result.success(): Result.fail();
    }

    /**
     * 删除PmUserRole
     */
    @ApiOperation(value = "删除")
    @DeleteMapping
    public Result delete(@Validated @RequestBody PmUserRole pmUserRole, BindingResult result) throws ApiException{
        return  pmUserRoleProvider.removeById(pmUserRole)?Result.success(): Result.fail();
    }

}
