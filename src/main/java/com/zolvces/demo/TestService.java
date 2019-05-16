package com.zolvces.demo;

import com.zolvces.validate.Validate;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @author niXueChao
 * @date 2019/5/15 14:36.
 */
@Component
public class TestService {

    @Validate
    public void test(@NotNull String id, TestController.Student student) {
        System.out.println("进入test方法");
    }
}
