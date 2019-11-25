package com.hollysys.platform.common.web.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@ApiModel(value = "排序参数", description = "排序参数")
@Data
public class SortItem {
    @ApiModelProperty(value = "字段名", example = "字段名" ,required = true)
    private String name;
    @ApiModelProperty(value = "排序类型", example = "default desc" ,required = false)
    private String value = "desc";
}
