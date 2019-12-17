package com.hollysys.platform.auth.server.permission.controller;

import com.hollysys.platform.common.web.controller.BaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hollysys.platform.common.core.exception.ApiException;
import com.hollysys.platform.common.web.controller.BaseController;
import com.hollysys.platform.auth.data.api.entity.OauthClientDetails;
import com.hollysys.platform.auth.data.api.provider.OauthClientDetailsProvider;
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
@Api(value="授权客户端 API", tags="{授权客户端}")
@Slf4j
@RestController
@RequestMapping("/v1/auth/oauth-client-details")
public class OauthClientDetailsController extends BaseController {

    @Reference(check = false,timeout = 3000)
    private OauthClientDetailsProvider oauthClientDetailsProvider;

    /**
     * 分页查询OauthClientDetails
     */
    @ApiOperation(value = "分页查询")
    @PostMapping("/pageQuery")
    public Result pageQuery(@RequestBody @Validated PageQueryParams<OauthClientDetails> params){
        IPage pageResult = oauthClientDetailsProvider.page(params.getPage());
        return Result.success(getResultByPage(pageResult));
    }

    /**
     * 新增OauthClientDetails
     */
    @ApiOperation(value = "新增")
    @PostMapping
    public Result save(@Validated  @RequestBody OauthClientDetails oauthClientDetails, BindingResult result) throws ApiException{
        return oauthClientDetailsProvider.save(oauthClientDetails)?Result.success(): Result.fail();
    }
   /**
     * 修改OauthClientDetails
     */
    @ApiOperation(value = "修改")
    @PutMapping
    public Result update(@Validated OauthClientDetails oauthClientDetails, BindingResult result) throws ApiException{
        return oauthClientDetailsProvider.updateById(oauthClientDetails) ?Result.success(): Result.fail();
    }

    /**
     * 删除OauthClientDetails
     */
    @ApiOperation(value = "删除")
    @DeleteMapping
    public Result delete(@Validated @RequestBody OauthClientDetails oauthClientDetails, BindingResult result) throws ApiException{
        return  oauthClientDetailsProvider.removeById(oauthClientDetails)?Result.success(): Result.fail();
    }

}
