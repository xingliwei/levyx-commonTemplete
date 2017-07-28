package com.xlw.levyx.api.utils.push;

/**
 * Created by Administrator on 2017/5/25.
 */
public class PushModel {
    private String registerId;  // 唯一标识
    private String type;		// 推送类别（2：网页 1：版本 ）
    private String title;		// 标题
    private String content;		// 内容
    private Integer phoneType;
    private String businessKey;
    private String messageId;   //金琉璃消息ID
    private String communityMessageId;//金琉璃圈子消息ID

    public static PushModel from(String type,String title,String content,String businessKey,String communityMessageId,String messageId){
        PushModel pushModel = new PushModel();
        pushModel.setType(type);
        pushModel.setTitle(title);
        pushModel.setContent(content);
        pushModel.setBusinessKey(businessKey);
        pushModel.setCommunityMessageId(communityMessageId);
        pushModel.setMessageId(messageId);
        return pushModel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(Integer phoneType) {
        this.phoneType = phoneType;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getCommunityMessageId() {
        return communityMessageId;
    }

    public void setCommunityMessageId(String communityMessageId) {
        this.communityMessageId = communityMessageId;
    }
}
