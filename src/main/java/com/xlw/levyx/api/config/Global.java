/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xlw.levyx.api.config;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.DefaultResourceLoader;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * 全局配置类
 * @author ThinkGem
 * @version 2014-06-25
 */
public class Global {

	/**
	 * 当前对象实例
	 */
	private static Global global = new Global();
	
	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = Maps.newHashMap();
	
	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader loader = new PropertiesLoader("application.properties");

	/**
	 * 显示/隐藏
	 */
	public static final String SHOW = "1";
	public static final String HIDE = "0";

	/**
	 * 是/否
	 */
	public static final String YES = "1";
	public static final String NO = "0";
	
	/**
	 * 对/错
	 */
	public static final String TRUE = "true";
	public static final String FALSE = "false";


	public static String getSdkUrl() {
		return getConfig("sdkUrl");
	}

	public static String getSdkUser() {
		return getConfig("sdkUser");
	}

	public static String getSdkPwd() {
		return getConfig("sdkPwd");
	}
	public static String getApplicationKey() {
		return getConfig("applicationKey");
	}
	public static String getMsgToUp() {
		return getConfig("msgToUp");
	}
	public static String getMsgToCustomer() {
		return getConfig("msgToCustomer");
	}
	public static String getWxTemplateId() {
		return getConfig("wx_template_id");
	}
	//退回医生模板
	public static String getBackDoctorTemp() {
		return getConfig("back_doctor_template_id");
	}
	//积分变动
	public static String getPointExchangeTemp() {
		return getConfig("point_exchange_template_id");
	}
	//注册消息提醒地服
	public static String getMessageSendTemp() {
		return getConfig("message_send_id");
	}

	/**
	 *开发者中心绑定token
	 * @return
	 */
	public static String getToken() {
		return getConfig("wx_token");
	}
	public static String getMsgUrl() {
		return getConfig("msgUrl");
	}

	//退回医生模板
	public static Integer getInviteScore() {
		int score=0;
		String inviteSocre = getConfig("invite_socre");
		if(StringUtils.isNotEmpty(inviteSocre)){
			score = Integer.valueOf(inviteSocre);
		}
		return score;
	}


	/**
	 * 上传文件基础虚拟路径
	 */
	public static final String USERFILES_BASE_URL = "/userfiles/";
	
	/**
	 * 获取当前对象实例
	 */
	public static Global getInstance() {
		return global;
	}
	
	/**
	 * 获取配置
	 * @see
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null){
			value = loader.getProperty(key);
			map.put(key, value != null ? value : StringUtils.EMPTY);
		}
		return value;
	}
	
	/**
	 * 获取管理端根路径
	 */
	public static String getAdminPath() {
		return getConfig("adminPath");
	}
	
	/**
	 * 获取前端根路径
	 */
	public static String getFrontPath() {
		return getConfig("frontPath");
	}
	
	/**
	 * 获取URL后缀
	 */
	public static String getUrlSuffix() {
		return getConfig("urlSuffix");
	}

	/**
	 * 获取当前系统部署服务器IP
	 */
	public static String getLocalIp() {
		return getConfig("wxs_local_ip");
	}

	/**
	 * 是否是演示模式，演示模式下不能修改用户、角色、密码、菜单、授权
	 */
	public static Boolean isDemoMode() {
		String dm = getConfig("demoMode");
		return "true".equals(dm) || "1".equals(dm);
	}
	
	/**
	 * 在修改系统用户和角色时是否同步到Activiti
	 */
	public static Boolean isSynActivitiIndetity() {
		String dm = getConfig("activiti.isSynActivitiIndetity");
		return "true".equals(dm) || "1".equals(dm);
	}

	public static Boolean isDebug(){
		String debug = getConfig("debug");
		if (StringUtils.isEmpty(debug)) {
			return false;
		}
		return "true".equals(debug);
	}

	public static Boolean isAddToEngine(){
		String debug = getConfig("addToEngine");
		if (StringUtils.isEmpty(debug)) {
			return false;
		}
		return "true".equals(debug);
	}


	public static Boolean isAddWeiXinQuenu(){
		String debug = getConfig("isAddWeiXinQuenu");
		if (StringUtils.isEmpty(debug)) {
			return false;
		}
		return "true".equals(debug);
	}

    
	/**
	 * 页面获取常量
	 * @see
	 */
	public static Object getConst(String field) {
		try {
			return Global.class.getField(field).get(null);
		} catch (Exception e) {
			// 异常代表无配置，这里什么也不做
		}
		return null;
	}


	
    /**
     * 获取工程路径
     * @return
     */
    public static String getProjectPath(){
    	// 如果配置了工程路径，则直接返回，否则自动获取。
		String projectPath = Global.getConfig("projectPath");
		if (StringUtils.isNotBlank(projectPath)){
			return projectPath;
		}
		try {
			File file = new DefaultResourceLoader().getResource("").getFile();
			if (file != null){
				while(true){
					File f = new File(file.getPath() + File.separator + "src" + File.separator + "main");
					if (f == null || f.exists()){
						break;
					}
					if (file.getParentFile() != null){
						file = file.getParentFile();
					}else{
						break;
					}
				}
				projectPath = file.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return projectPath;
    }
	
}
