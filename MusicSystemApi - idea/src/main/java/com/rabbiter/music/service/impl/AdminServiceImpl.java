package com.rabbiter.music.service.impl;

import com.rabbiter.music.service.AdminService;
import com.rabbiter.music.dao.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 管理员service实现类
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 验证密码是否正确
     *
     * @param username
     * @param password
     */
    @Override
    public boolean verifyPassword(String username, String password) {
        return adminMapper.verifyPassword(username,password)>0;
    }
}
