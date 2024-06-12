package com.rabbiter.music.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 收藏
 */
public class Attention implements Serializable {
    private Integer id;     //主键
    private Integer userId; //用户id
    private Integer useredId; //被关注用户id
    private String  useredName;
    private Date createTime;    //关注时间

    public String getUsered_name() {
        return useredName;
    }

    public void setUsered_name(String usered_name) {
        this.useredName = usered_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getuseredId() {
        return useredId;
    }

    public void setuseredId(Integer useredId) {
        this.useredId = useredId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Attention{" +
                "id=" + id +
                ", userId=" + userId +
                ", useredId=" + useredId +
                ", usered_name='" + useredName + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
