package com.autuleaf.usermanage.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户实体类
 * author:autumn_leaf
 */
@Entity
@Table(name = "tb_user")
public class UserInfo {

    //主键,自动递增
    @Id
    @GeneratedValue
    private long id;

    //不允许为空且唯一
    @Column(nullable = false,unique = true)
    private String userName;

    @Column(nullable = false)
    private String pwd;

    @Column(nullable = false)
    private int age;

    //注册时间
    @Column(nullable = false)
    private Date regTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    //利用commons-lang包进行反射
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
