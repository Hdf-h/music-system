package com.rabbiter.music.service.impl;

import com.rabbiter.music.dao.AttentionMapper;
import com.rabbiter.music.dao.CollectMapper;
import com.rabbiter.music.pojo.Attention;
import com.rabbiter.music.pojo.Collect;
import com.rabbiter.music.service.AdminService;
import com.rabbiter.music.service.AttentionService;
import com.rabbiter.music.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 关注service实现类
 */
@Service
public class AttentionServiceImpl implements AttentionService {

    @Autowired
    private AttentionMapper attentionMapper;


    @Override
    public boolean insert(Attention attention) {
        return attentionMapper.insert(attention)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return attentionMapper.delete(id)>0;
    }

    @Override
    public boolean deleteByUserIduseredId(Integer userId, Integer useredId) {
        return attentionMapper.deleteByUserIdSongId(userId,useredId)>0;
    }

    @Override
    public List<Collect> allAttention() {
        return attentionMapper.allAttention();
    }

    @Override
    public List<Collect> attentionOfUserId(Integer userId) {
        return attentionMapper.attentionOfUserId(userId);
    }

    @Override
    public boolean existUseredId(Integer userId, Integer useredId) {
        return attentionMapper.existUseredId(userId,useredId)>0;
    }
}
