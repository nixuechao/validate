package com.zolvces.demo;

import com.zolvces.validate.ErrorMsgConverter;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author niXueChao
 * @date 2019/5/15 14:27.
 */
@Component
public class ValidateConfig implements ErrorMsgConverter {

    /**将参数验证结果转化为返回给前端的对象
     * @param errorMsg 参数验证失败的信息<k,V>
     *                 k:验证失败的参数 v:失败原因
     * @return
     */
    @Override
    public Object convert(Map errorMsg) {
        return new ResultBean<>(500, errorMsg, "data");
    }
}
