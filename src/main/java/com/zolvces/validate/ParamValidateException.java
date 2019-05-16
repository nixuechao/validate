package com.zolvces.validate;

import java.util.Map;

/**
 * @author niXueChao
 * @date 2019/5/14 11:24.
 */
class ParamValidateException extends RuntimeException {
    private Map<String, String> errorMsg;

    ParamValidateException(Map<String, String> errorMsg) {
        super();
        this.errorMsg = errorMsg;
    }

    Map<String, String> getErrorMsg() {
        return errorMsg;
    }
}
