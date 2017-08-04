package com.xlw.levyx.api.utils.aliOnsMq;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.ons.api.*;
import org.apache.log4j.Logger;

import java.util.Properties;
import java.util.ResourceBundle;

public class AliOnsProducerClient {
	
	private static Logger logger = Logger.getLogger(AliOnsProducerClient.class);
	
	private static ResourceBundle resource = ResourceBundle.getBundle("application");

    private static Producer producer = null;

    private static Producer getProducer() {
    	
        if (null == producer) {
        	
            Properties properties = new Properties();
            properties.put(PropertyKeyConst.ProducerId, resource.getString("ali.ons.productId"));
            properties.put(PropertyKeyConst.AccessKey, resource.getString("ali.ons.accessKey"));
            properties.put(PropertyKeyConst.SecretKey, resource.getString("ali.ons.secretKey"));
            //公有云生产环境：http://onsaddr-internal.aliyun.com:8080/rocketmq/nsaddr4client-internal
            //公有云公测环境：http://onsaddr-internet.aliyun.com/rocketmq/nsaddr4client-internet
            properties.put(PropertyKeyConst.ONSAddr, resource.getString("ali.ons.addr"));
            producer = ONSFactory.createProducer(properties);

        }
        if (!producer.isStarted()) {
            //在发送消息前，必须调用start方法来启动Producer，只需调用一次即可。
            producer.start();
        }
        return producer;
    }

    public static void sendOnsMessage(String memberId, JSONObject msgTopic, JSONObject msgBody) {
        if (msgTopic != null&&msgBody!=null) {
            
            Thread t = new Thread() {
                public void run() {
                    Message msg = new Message(
                            //Message Topic
                    		msgTopic.getString("topic"), 
                            //Message Tag,
                            //可理解为Gmail中的标签，对消息进行再归类，方便Consumer指定过滤条件在ONS服务器过滤
                    		msgTopic.getString("tag"),
                            //Message Body
                            //任何二进制形式的数据，ONS不做任何干预，需要Producer与Consumer协商好一致的序列化和反序列化方式
                    		msgBody.toJSONString().getBytes()
                            
                    );

                    // 设置代表消息的业务关键属性，请尽可能全局唯一。
                    // 以方便您在无法正常收到消息情况下，可通过ONS Console查询消息并补发。
                    // 注意：不设置也不会影响消息正常收发
                    msg.setKey(memberId);
                    //发送消息，只要不抛异常就是成功
                    SendResult sendResult = getProducer().send(msg);
                    logger.info("ali ons message push result : " + sendResult.toString());
                }
            };
            t.start();
        }
    }

}
