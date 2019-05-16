package com.zolvces.validate;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author niXueChao
 * @date 2019/5/15 10:09.
 */
@Configuration
public class MsgConverterConfig {

    @Bean
    @ConditionalOnMissingBean
    public ErrorMsgConverter defaultConverter(){
        return errorMsg -> new HashMap<String,Object>(4){{
            put("code", "200");
            put("message", errorMsg);
        }};
    }
}
