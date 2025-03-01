package com.tencent.wxcloudrun.web.wxapi.controller;


import com.tencent.wxcloudrun.utils.*;
import com.tencent.wxcloudrun.web.wxapi.entity.Code2Session;
import com.tencent.wxcloudrun.web.wxapi.entity.LoginParm;
import com.tencent.wxcloudrun.web.wxapi.entity.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/wxapi/user")
public class WxLoginController {
    @Value("${Wechat.Applets.appId}")
    private String appId;
    @Value("${Wechat.Applets.appSecret}")
    private String appSecret;

    //小程序登录
    @RequestMapping("/wxlogin")
    public ResultVo wxlogin(@RequestBody LoginParm parm) {
        //获取code
        String code = parm.getCode();
        log.info("wxlogin - code" + code);
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> params = new HashMap<>();
        params.put("appid", appId);
        params.put("secret", appSecret);
        params.put("js_code", code);
        params.put("grant_type", "authorization_code");
        log.info("params: " + params);
        try {
            //发送请求
            String wxResult = HttpClientUtil.doGet(url, params);
            //转换参数
            Code2Session userJson = FastJsonTools.getJson(wxResult, Code2Session.class);
            log.info("userJson: " + userJson);
            if (userJson == null) throw new BusinessException(500, "小程序获取open失败");
            //获取openId
            String openId = userJson.getOpenid();
            //获取sessionKey
            String sessionKey = userJson.getSession_key();
            log.info("openId: " + openId);
            log.info("sessionKey: " + sessionKey);
            //组装返回给小程序的值
            LoginVo vo = new LoginVo();
            vo.setOpenId(openId);
            vo.setSessionKey(sessionKey);
            //返回
            return ResultUtils.success("获取成功", vo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(String.format("WxLoginController.wxlogin error:%s", e.getMessage()));
        }

        return ResultUtils.error("小程序获取open失败");
    }
}
