package com.zolvces.validate;

import java.util.Map;

/**
 * @author niXueChao
 * @date 2019/5/14 15:42.
 */
public interface ErrorMsgConverter<T> {

    /** 将验证结果转化为返回给前端的对象
     * @param errorMsg 参数验证失败的信息<k,V>
     *                 k:验证失败的参数 v:失败原因
     * @return
     */
     T convert(Map<String,String> errorMsg);
}
