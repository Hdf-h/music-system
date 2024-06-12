package com.rabbiter.music.dao;

import com.rabbiter.music.pojo.Attention;
import com.rabbiter.music.pojo.Collect;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Dao关注
 */
@Repository
public interface AttentionMapper {
    /**
     *增加
     */
    public int insert(Attention attention);

    /**
     * 删除
     */
    public int delete(Integer id);

    /**
     * 根据用户id和被关注用户id取消关注
     */
    public int deleteByUserIdSongId(@Param("userId") Integer userId, @Param("useredId") Integer songId);

    /**
     * 查询所有关注
     */
    public List<Collect> allAttention();

    /**
     * 查询某个用户的收藏列表
     */
    public List<Collect> attentionOfUserId(Integer userId);

    /**
     * 查询某个用户是否已经收藏了某个歌曲
     */
    public int existUseredId(@Param("userId") Integer userId, @Param("UseredId") Integer songId);



}
