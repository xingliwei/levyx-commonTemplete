package com.xlw.levyx.api.util.aliOnsMq;

import com.alibaba.fastjson.JSONObject;
import com.xlw.levyx.api.service.UserService;
import com.xlw.levyx.api.utils.aliOnsMq.AliOnsConsumerClient;
import com.xlw.levyx.api.utils.aliOnsMq.AliOnsProducerClient;
import com.xlw.levyx.mapper.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by levyx on 2017/8/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class UserServiceTester {
    @Autowired
    private UserService userService;
    @Test
    public void test1(){
        User user = userService.findUserById(1L);
        System.out.println(user);
    }

}
