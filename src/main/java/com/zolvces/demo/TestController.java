package com.zolvces.demo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @author niXueChao
 * @date 2019/5/15 13:01.
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public ResultBean validateTest(){
        Student student = new Student();
        student.setName(null);
        student.setSex("哈哈哈");
        student.setAge(100);
        testService.test(null, student);

        return new ResultBean();
    }


     class Student {
        @NotNull
        private String name;

        @Length(max = 2,min = 1)
        private String sex;

        @Range(max = 50, min = 10)
        private Integer age;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
