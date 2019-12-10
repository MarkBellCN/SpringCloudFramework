package com.hollysys.platform.common.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hollysys.platform.common.core.utils.BeanGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;

public class BaseController {
    @Autowired
    protected Validator globalValidator;

    @Autowired
    protected BeanGenerator beanGenerator;

    protected Map getResultByPage(IPage pageResult){
        Map result = new HashMap();
        result.put("list",pageResult.getRecords());
        result.put("total",pageResult.getTotal());
        result.put("size",pageResult.getSize());
        result.put("current",pageResult.getCurrent());
        return result;
    }
}
