package com.autuleaf.usermanage.param;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class UserParam {

    private long id;

    @NotEmpty(message = "用户名不能为空")
    private String userName;

    @NotEmpty(message = "密码不能为空")
    @Length(message = "密码长度不能少于6位")
    private String pwd;

    @Max(value = 100,message = "年龄不能超过100")
    @Min(value = 1,message = "年龄必须大于0")
    private int age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
