package com.hollysys.platform.common.core.exception;

import com.hollysys.platform.common.core.utils.LocaleMessage;
import com.hollysys.platform.common.core.utils.SpringContextUtil;

public interface ErrorType {
    LocaleMessage localeMessage = (LocaleMessage) SpringContextUtil.getBean("localeMessage");
    /**
     * 返回code
     *
     * @return
     */
    String getCode();

    /**
     * 返回msg
     *
     * @return
     */
    String getMsg();
}
