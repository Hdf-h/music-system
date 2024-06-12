package com.rabbiter.music.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论
 */
public class Comment implements Serializable {
    /*主键*/
    private Integer id;
    /*用户id*/
    private Integer userId;
    /*歌曲id*/
    private Integer songId;
    /*评论内容*/
    private String content;
    /*评论时间*/
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return id;
    }

    public void setUserId(Integer id) {
        this.id = id;
    }


    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", type=" + userId +
                ", songId=" + songId +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
