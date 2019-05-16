package com.zolvces.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author niXueChao
 * @date 2019/5/14 11:27.
 */
@RestControllerAdvice
public class ValidateExceptionHandler {
    @Autowired
    private ErrorMsgConverter errorMsgConverter;

    @ExceptionHandler(ParamValidateException.class)
    public Object validateExceptionProcess(ParamValidateException e) {
        return errorMsgConverter.convert(e.getErrorMsg());
    }
}
