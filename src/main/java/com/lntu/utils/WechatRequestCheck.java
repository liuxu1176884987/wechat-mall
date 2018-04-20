package com.lntu.utils;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * 客户端请求验证
 * Created by Administrator on 2018/4/10.
 * @param token 传入一个标记进行排序
 * @param timestamp 传入一个时间戳
 * @param nonce 传入一个随机字符串
 * @param signature 加密后与其进行判断对比的字符串
 *
 */
public class WechatRequestCheck {

    public static Boolean check(HttpServletRequest request,String token){
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String signature = request.getParameter("signature");
        if(!StringUtils.isEmpty(timestamp) && !StringUtils.isEmpty(nonce) && !StringUtils.isEmpty(token) && !StringUtils.isEmpty(signature)){
            // 验证微信签名
            // * 将token、timestamp、nonce三个参数进行字典序排序
            // * 将三个参数字符串拼接成一个字符串进行sha1加密
            // * 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
            String checkStr = timestamp+nonce+token;
            char[] strArray = checkStr.toCharArray();
            Arrays.sort(strArray);
            String sortCheckStr = String.valueOf(strArray);
            // 进行sha1加密
            String sha1Str = sha1Encode(sortCheckStr);
            if(signature.toLowerCase().equals(sha1Str.toLowerCase())){
                return true;
            }
            return false;
        }
        return false;
    }

    private static String sha1Encode(String str){
        if(StringUtils.isEmpty(str)){
            return null;
        }
        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9',
                'a','b','c','d','e','f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j*2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }

}
