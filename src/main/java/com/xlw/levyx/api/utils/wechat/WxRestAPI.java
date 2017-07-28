package com.xlw.levyx.api.utils.wechat;

import com.google.common.collect.Lists;
import com.xlw.levyx.api.config.Global;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * 微信 Rest Service客户端
 *
 * @author levyx
 * @version 1.0
 */
public class WxRestAPI {
    private static Logger logger = LoggerFactory.getLogger(WxRestAPI.class);
    private static final String WX_OPEN_URI = "https://open.weixin.qq.com";
    //下面的这些参数要进行替换，比如写到配置文件中
    private static final String REST_BASE_URL = Global.getConfig("wxs_restBaseUrl");
    public static final String APPID = Global.getConfig("wxs_appid");
    public static final String MCHID = Global.getConfig("wxs_mchid");
    private static final String accessKey = Global.getConfig("wxs_accessKey");
    private static final String secretKey = Global.getConfig("wxs_secretKey");

    private static RestTemplate httpClientRestTemplate;
    private static HttpComponentsClientHttpRequestFactory httpClientRequestFactory;

    static {
        httpClientRestTemplate = new RestTemplate();
        httpClientRequestFactory = new HttpComponentsClientHttpRequestFactory();
        // (optional) 设置连接主机超时（单位：毫秒）
        httpClientRequestFactory.setConnectTimeout(5000);
        httpClientRequestFactory.setConnectionRequestTimeout(5000);
        // 设置从主机读取数据超时（单位：毫秒）
        httpClientRequestFactory.setReadTimeout(5000);

        httpClientRestTemplate.setRequestFactory(httpClientRequestFactory);
        ClientHttpRequestInterceptor interceptor = new HttpBasicInterceptor(accessKey, secretKey);
        httpClientRestTemplate.setInterceptors(Lists.newArrayList(interceptor));
    }

    public static TemplateMessageResult sendMessageTemplate(String template_id, String openid, String url, String topcolor, LinkedHashMap<String, TemplateMessageItem> data) {
        String resourceUrl = REST_BASE_URL + "/api/v1/messageTemplate/{appid}";
        TemplateMessage templateMessag = new TemplateMessage();
        templateMessag.setTemplate_id(template_id);
        templateMessag.setTouser(openid);
        templateMessag.setUrl(url);
        templateMessag.setTopcolor(topcolor);
        templateMessag.setData(data);
        TemplateMessageResult result = httpClientRestTemplate.postForObject(resourceUrl, templateMessag, TemplateMessageResult.class, APPID);
        return result;
    }

    public static class HttpBasicInterceptor implements ClientHttpRequestInterceptor {
        private final String ak;
        private final String sk;

        public HttpBasicInterceptor(String ak, String sk) {
            this.ak = ak;
            this.sk = sk;
        }

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
                throws IOException {
            request.getHeaders().set(com.google.common.net.HttpHeaders.AUTHORIZATION,
                    encodeHttpBasic(ak, sk));
            return execution.execute(request, body);
        }
    }

    /**
     * 客户端对Http Basic验证的 Header进行编码.
     */
    public static String encodeHttpBasic(String userName, String password) {
        String encode = userName + ":" + password;
        return "Basic " + new String(Base64.encodeBase64(encode.getBytes()));
    }

    public static void main(String[] args) {
        try {
            String sys_temp_id = Global.getConfig("sys_template_id");
            String url = Global.getConfig("consultation_detail_url");
            String topicColor = "#173177";
            String message = "测试微信消息！";
            String title = "测试";
            String openId = "";
            LinkedHashMap<String, TemplateMessageItem> dataMap = new LinkedHashMap<String, TemplateMessageItem>();
            dataMap.put("first", new TemplateMessageItem(message, "#173177"));
            dataMap.put("keyword1", new TemplateMessageItem(title, "#173177"));
            dataMap.put("keyword2", new TemplateMessageItem(DateFormatUtils.format(new Date(), "yyyy年MM月dd日 HH:mm"), "#173177"));
            WxRestAPI.sendMessageTemplate(sys_temp_id,openId,url,topicColor,dataMap);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
    }
}

