package com.rabbiter.music.service;

import com.rabbiter.music.pojo.Attention;
import com.rabbiter.music.pojo.Collect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 关注service接口
 */
public interface AttentionService {
    /**
     *增加
     */
    public boolean insert(Attention attention);


    /**
     * 删除
     */
    public boolean delete(Integer id);
    /**
     * 根据用户id和歌曲id删除
     */
    public boolean deleteByUserIduseredId(Integer userId, Integer useredId);
    /**
     * 查询所有关注
     */
    public List<Collect> allAttention();

    /**
     * 查询某个用户的关注列表
     */
    public List<Collect> attentionOfUserId(Integer userId);
    /**
     * 查询某个用户是否已经关注了某个用户
     */
    public boolean existUseredId(@Param("userId") Integer userId, @Param("useredId") Integer useredId);
}
