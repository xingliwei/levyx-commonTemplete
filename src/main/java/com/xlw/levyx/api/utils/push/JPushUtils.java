package com.xlw.levyx.api.utils.push;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.google.common.collect.Maps;
import com.xlw.levyx.api.config.Global;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.function.Consumer;

public class JPushUtils {

    private static final String appKey = Global.getConfig("jpushAppkey");
    private static final String masterSecret = Global.getConfig("jpushMasterSecret");//每个应用都对应一个masterSecret线上

    private static JPushClient jpush = new JPushClient(masterSecret, appKey);
    /**
     * 安卓
     * */
    public final static int PHONE_TYPE_ANDROID = 1;
    /**
     * IOS苹果
     * */
    public final static int PHONE_TYPE_IOS = 2;

    /**
     * 发送通知给特定的用户
     */
    public  void sendNotificationToUser(PushModel model) {

        try {
            if (StringUtils.isNotEmpty(model.getRegisterId())) {
                Map<String, String> map = Maps.newHashMap();
                map.put("type", model.getType());
                map.put("businessKey",model.getBusinessKey());
                map.put("messageId",model.getMessageId());
                map.put("communityMessageId",model.getCommunityMessageId());
                if (PHONE_TYPE_ANDROID == model.getPhoneType()) {
                    //给安卓端推送通知
                    PushResult result = jpush.sendAndroidNotificationWithRegistrationID(model.getTitle(), model.getContent(), map, model.getRegisterId());
                    System.out.println(result.getResponseCode());
                } else if (PHONE_TYPE_IOS == model.getPhoneType()) {
                    //给ios端推送通知
                    buildPushObject_ios_tagAnd_alertWithExtrasAndMessage(model);
                }
            }

        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }

    }

    public  void buildPushObject_ios_tagAnd_alertWithExtrasAndMessage(PushModel model) {
        try {
            if (StringUtils.isNotEmpty(model.getRegisterId())) {
                PushPayload pl = PushPayload.newBuilder()
                                            .setPlatform(Platform.ios())
                                            .setAudience(Audience.registrationId(model.getRegisterId()))
                                            .setNotification(Notification.newBuilder()
                                                                         .addPlatformNotification(IosNotification.newBuilder()
                                                                                                                 .setAlert(model.getContent())
                                                                                                                 .setBadge(+1)
                                                                                                                 .setSound("Default")
                                                                                                                 .addExtra("type", model.getType())
                                                                                                                 .addExtra("businessKey",model.getBusinessKey())
                                                                                                                 .addExtra("messageId",model.getMessageId())
                                                                                                                 .addExtra("communityMessageId",model.getCommunityMessageId())
                                                                                                                 .build())
                                                                         .build())
                                            .setOptions(Options.newBuilder()
                                                               .setApnsProduction(true)
                                                               .build())
                                            .build();
                PushResult pushResult = jpush.sendPush(pl);
                System.out.println(pushResult.getResponseCode());
            }
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }

    public static void send(final Consumer<JPushUtils> consumer) {

        JPushUtils jPushUtils = new JPushUtils();
        consumer.accept(jPushUtils);
    }


    public static void main(String[] args) {
        PushModel pushModel = new PushModel();
        pushModel.setContent("极光推送内容。。。");
        pushModel.setPhoneType(1);
        //registerId一般在前端注册的时候纪录到用户信息中，是推送给用户信息的唯一标识
        pushModel.setRegisterId("设备对应的registerId");
        JPushUtils.send(jPushUtils -> jPushUtils.sendNotificationToUser(pushModel));
    }
}
