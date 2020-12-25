package com.ruoyi.games.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @description:
 * @date 2020/12/24 21:46
 */
public class SmsUtil {

    //产品名称:云通信短信API产品,开发者无需替换
    private static String product = "Dysmsapi";
    //产品域名,开发者无需替换
    private static String domain = "dysmsapi.aliyuncs.com";

    private static String regionIdForPop = "cn-hangzhou";

    public static SendSmsResponse sendSms(Map<String, String> param) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile(regionIdForPop, param.get("AccessKeyID"), param.get("AccessKeySecret"));
        DefaultProfile.addEndpoint(regionIdForPop, regionIdForPop, product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        SendSmsResponse sendSmsResponse = null;
        try {
            //必填:待发送手机号
            request.setPhoneNumbers(param.get("PhoneNumber"));
            //必填:短信签名-可在短信控制台中找到
            request.setSignName(param.get("SignName"));
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(param.get("TemplateCode"));
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            request.setTemplateParam("{\"code\":\"" + param.get("PhoneCode") + "\"}");

            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            request.setOutId("uid");
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (Exception e) {
            System.out.println(e);
        }

        return sendSmsResponse;
    }

}
