package com.xlw.levyx.api.service;

import com.xlw.levyx.mapper.client.UserMapper;
import com.xlw.levyx.mapper.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by levyx on 2016/9/23 0023.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findUserById(Long id){
        return userMapper.selectByPrimaryKey(id);
    }

}
