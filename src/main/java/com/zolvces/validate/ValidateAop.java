package com.zolvces.validate;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.validator.internal.engine.ValidatorImpl;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author niXueChao
 * @date 2019/5/13 22:42.
 */
@Aspect
@Component
public class ValidateAop {

    @Pointcut("@annotation(Validate)")
    public void needValidateMethod() {
    }

    @Before("needValidateMethod()")
    public void validateMethod(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Validate validate = method.getDeclaredAnnotation(Validate.class);
        Class<?>[] groups = validate.groups();

        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        ValidatorImpl validator = (ValidatorImpl)vf.getValidator();

        Map<String, String> msg = new HashMap<>();
        //验证在方法参数上打的注解
        Set<ConstraintViolation<Object>> constraintViolations = validator.validateParameters(joinPoint.getTarget(), method, args,groups);
        for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
            msg.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
        }

        //验证每一个参数对象内打的注解
        for (Object arg : args) {
            if (arg == null) {
                continue;
            }
            Set<ConstraintViolation<Object>> set = validator.validate(arg,groups);
            for (ConstraintViolation<Object> constraintViolation : set) {
                msg.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
            }
        }
        if (msg.size() > 0) {
            throw new ParamValidateException(msg);
        }

    }
}

