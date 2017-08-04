package com.xlw.levyx.api.utils.aliOnsMq;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.ons.api.*;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.ResourceBundle;

@Component
public class AliOnsConsumerClient {


	private static Logger logger = Logger.getLogger(AliOnsConsumerClient.class);
	
	private static ResourceBundle resource = ResourceBundle.getBundle("application");

    private static Consumer consumer = null;

    private static Consumer getConsumer() {
    	
        if (null == consumer) {
        	
            Properties properties = new Properties();
            properties.put(PropertyKeyConst.ConsumerId, resource.getString("ali.ons.consumerId"));
            properties.put(PropertyKeyConst.AccessKey, resource.getString("ali.ons.accessKey"));
            properties.put(PropertyKeyConst.SecretKey, resource.getString("ali.ons.secretKey"));
            //公有云生产环境：http://onsaddr-internal.aliyun.com:8080/rocketmq/nsaddr4client-internal
            //公有云公测环境：http://onsaddr-internet.aliyun.com/rocketmq/nsaddr4client-internet
            properties.put(PropertyKeyConst.ONSAddr, resource.getString("ali.ons.addr"));
            consumer = ONSFactory.createConsumer(properties);

        }
        return consumer;
    }


    public static void subscribe(){
        getConsumer().subscribe(resource.getString("ali.ons.message.topic"), "*", new MessageListener() {
            @Override
            public Action consume(Message message, ConsumeContext consumeContext) {
                logger.info("receive message:\n");
                logger.info(new String(message.getBody()));

                JSONObject jsonObject = JSONObject.parseObject(new String(message.getBody()));
                return Action.CommitMessage;
            }
        });
        consumer.start();
    }


//    public static void test(NewsService newsService){
//        ExtraNews extraNews = new ExtraNews();
//        extraNews.setId("test1");
//        extraNews.setTitle("测试hessian配置");
//        extraNews.setDescription("蛤蛤");
//        extraNews.setImage("cd6447da9f814e5486a332b0cc058f33-01-05-18-03-36.jpg");
//        extraNews.setContent("测试大概几点阿斯达萨达撒多");
//        extraNews.setCopyfrom("");
//        extraNews.setCopyfromurl("http://news.bioon.com/article/6679234.html");
//        extraNews.setOriginal("1");
//        extraNews.setRemarks("");
//        extraNews.setCategoryId("46edb4ad360441ebab67396cefba81f9");
//        Pair<NewsWithBLOBs,NewsMenu> pair = ExtraNews.convert(extraNews);
//        NewsWithBLOBs news = pair.getLeft();
//        NewsMenu newsMenu = pair.getRight();
//        News news1 = newsService.selectByExtraInfoId(news.getExtraInfoId());
//        if (news1 != null){
//            news.setId(news1.getId());
//            newsService.update(news,newsMenu);
//        }else {
//            newsService.insert(news,newsMenu);
//        }
//        taskExecutor.execute(new AddToEs(news,newsMenu, 0));
//    }


}
