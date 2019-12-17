package com.hollysys.platform.auth.server.permission.controller;

import com.hollysys.platform.common.web.controller.BaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hollysys.platform.common.core.exception.ApiException;
import com.hollysys.platform.common.web.controller.BaseController;
import com.hollysys.platform.auth.data.api.entity.PmRoleResource;
import com.hollysys.platform.auth.data.api.provider.PmRoleResourceProvider;
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
@Api(value="角色资源表 API", tags="{角色资源表}")
@Slf4j
@RestController
@RequestMapping("/v1/auth/pm-role-resource")
public class PmRoleResourceController extends BaseController {

    @Reference(check = false,timeout = 3000)
    private PmRoleResourceProvider pmRoleResourceProvider;

    /**
     * 分页查询PmRoleResource
     */
    @ApiOperation(value = "分页查询")
    @PostMapping("/pageQuery")
    public Result pageQuery(@RequestBody @Validated PageQueryParams<PmRoleResource> params){
        IPage pageResult = pmRoleResourceProvider.page(params.getPage());
        return Result.success(getResultByPage(pageResult));
    }

    /**
     * 新增PmRoleResource
     */
    @ApiOperation(value = "新增")
    @PostMapping
    public Result save(@Validated  @RequestBody PmRoleResource pmRoleResource, BindingResult result) throws ApiException{
        return pmRoleResourceProvider.save(pmRoleResource)?Result.success(): Result.fail();
    }
   /**
     * 修改PmRoleResource
     */
    @ApiOperation(value = "修改")
    @PutMapping
    public Result update(@Validated PmRoleResource pmRoleResource, BindingResult result) throws ApiException{
        return pmRoleResourceProvider.updateById(pmRoleResource) ?Result.success(): Result.fail();
    }

    /**
     * 删除PmRoleResource
     */
    @ApiOperation(value = "删除")
    @DeleteMapping
    public Result delete(@Validated @RequestBody PmRoleResource pmRoleResource, BindingResult result) throws ApiException{
        return  pmRoleResourceProvider.removeById(pmRoleResource)?Result.success(): Result.fail();
    }

}
