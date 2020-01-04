package com.caiyf.justdoit.client.impl;

import com.caiyf.justdoit.client.api.ShortMessageClient;
import com.caiyf.justdoit.util.DateUtil;
import com.caiyf.justdoit.util.StringUtil;
import com.zhenzi.sms.ZhenziSmsClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * zhenzi short message platform
 *
 * @author caiyf
 * @date 2020-01-04
 */
public class ZhenZiClient implements ShortMessageClient {

    private static Logger log = LoggerFactory.getLogger(DateUtil.class);

    /**
     * send short message
     *
     * @param phoneNum
     * @param message
     */
    public void sendShortMessage(String phoneNum, String message) {
        if (checkParam(phoneNum, message)) {
            // todo: customized exception
            throw new RuntimeException("参数缺失");
        }

        // generate param
        Map<String, String> param = generateSendParam(phoneNum, message);
        ZhenziSmsClient client = null;
        try {
            client = new ZhenziSmsClient("", "", "");
            client.send(param);
        } catch (Exception e) {
            log.error("{} send error, cause: {}", ERROR_LOG_MSG, e.getMessage());
            throw new  RuntimeException("发送失败");
        }
    }

    /**
     * param check
     *
     * @param phoneNum
     * @param message
     * @return
     */
    private boolean checkParam(String phoneNum, String message) {
        if (StringUtil.isEmpty(phoneNum) || StringUtil.isEmpty(message)) {
            log.error("{} miss param, phoneNum: {}, message: {}", ERROR_LOG_MSG, phoneNum, message);
            return false;
        }
        // todo: match phone pattern
        return true;
    }

    /**
     * generate param
     *
     * @param phoneNum
     * @param message
     * @return
     */
    private Map<String, String> generateSendParam(String phoneNum, String message) {
        Map<String, String> param = new HashMap<>();
        param.put(phoneNum, message);
        return param;
    }

}
