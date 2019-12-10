package com.hollysys.platform.common.web.params;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
@ApiModel(value = "分页查询", description = "分页查询")
@Data
public class PageQueryParams<T> {
    @ApiModelProperty(value = "当前页", example = "当前页" ,required = true)
    private int pageNum;
    @ApiModelProperty(value = "每页显示数量", example = "每页显示数量" ,required = true)
    private int pageSize = 10;
    @ApiModelProperty(value = "查询参数", example = "查询参数" ,required = false)
    @Valid
    private T data;
    @ApiModelProperty(value = "排序参数", example = "[]" ,required = false)
    private List<SortItem> sortMap;

    @JsonIgnore
    public IPage getPage() {
        return new Page(this.getPageNum(), this.getPageSize(),true);
    }
}
