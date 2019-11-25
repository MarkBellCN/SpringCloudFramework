package com.hollysys.platform.common.web.params;

import lombok.Data;

import java.util.List;

@Data
public class ExportParams<T> extends PageQueryParams<T> {

    private List<String> headerEn;

    private List<TableField> tableFields;
}
