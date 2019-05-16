# 适用于springWeb项目的 beanValidate工具

## 改造需求
在springBoot中验证参数时需要传入org.springframework.validation.Errors 对象,获取验证结果信息.
在验证方法参数时还要在类上打@Validated注解
个人觉得不太好的地方:

1. 首先不太统一,一会在方法参数上打@Valid,一会在类上打@Validated

2. 获取验证结果时需要在方法上添加一个Errors 对象,这不仅麻烦而且看起来很诡异

3. 我想在调用业务方法时再检验参数
## 改造逻辑
1. 自定义一个可以加在方法上的注解,加了该注解的方法表示需要验证入参
2. AOP拦截加了注解的方法,在进入方法前对方法参数进行校验
3. 校验结果(参数错误信息)通过抛出一个指定的异常
4. 捕获异常转化返回信息,返回给前端
## 使用该工具
1. 复制validate包到项目源码目录(能被spring扫描到)中
2. 实现接口ErrorMsgConverter 并注入到spring中,实现示例:
```java
@Component
public class ValidateConfig implements ErrorMsgConverter {

    /**将参数验证结果转化为返回给前端的对象
     * @param errorMsg 参数验证失败的信息<k,V>
     *                 k:验证失败的参数 v:失败原因
     * @return 返回给前端的对象
     */
    @Override
    public Object convert(Map errorMsg) {
        return new ResultBean<>(500, errorMsg, "data");
    }
}
```
## 运行demo
运行ValidateApplication中的main方法,启动后访问http://localhost:8080/test
返回示例
```json
{
    "code": 500,
    "msg": {
        "test.id": "不能为null",
        "sex": "长度需要在1和2之间",
        "name": "不能为null",
        "age": "需要在10和50之间"
    },
    "data": "data"
}
```
## 校验注解
最后附一个校验注解的表格

| 注解 | 说明 |
| ---- | ---- |
|@Null  | 被注释的元素必须为 null   |
|@NotNull    |被注释的元素必须不为 null   |
|@AssertTrue     |被注释的元素必须为 true    |
|@AssertFalse   | 被注释的元素必须为 false    |
|@Min(value)     |被注释的元素必须是一个数字，其值必须大于等于指定的最小值  |
|@Max(value)     |被注释的元素必须是一个数字，其值必须小于等于指定的最大值  |
|@DecimalMin(value)  |被注释的元素必须是一个数字，其值必须大于等于指定的最小值 |
|@DecimalMax(value) | 被注释的元素必须是一个数字，其值必须小于等于指定的最大值 |
|@Size(max=, min=)   |被注释的元素的大小必须在指定的范围内    |
|@Digits (integer, fraction)    | 被注释的元素必须是一个数字，其值必须在可接受的范围内  |
|@Past   |被注释的元素必须是一个过去的日期    |
|@Future    | 被注释的元素必须是一个将来的日期    |
|@Pattern(regex=,flag=)  |被注释的元素必须符合指定的正则表达式  |
|@NotBlank(message =)   |验证字符串非null，且长度必须大于0  |
|@Email  |被注释的元素必须是电子邮箱地址    |
|@Length(min=,max=)  |被注释的字符串的大小必须在指定的范围内    |
|@NotEmpty  | 被注释的字符串的必须非空    |
|@Range(min=,max=,message=) | 被注释的元素必须在合适的范围内|