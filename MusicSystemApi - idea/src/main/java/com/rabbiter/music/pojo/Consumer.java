package com.rabbiter.music.pojo;

import java.util.Date;

/**
 * 前端用户
 */
public class Consumer {
    /*主键*/
    private Integer id;
    /*账号*/
    private String username;
    /*密码*/
    private String password;
    /*性别*/
    private Byte sex;
    /*创建时间*/
    private Date createTime;
    /*更新时间*/
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
