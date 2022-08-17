package com.liston.controller;

import com.liston.grace.result.GraceJSONResult;
import com.liston.utils.IPUtil;
import com.liston.utils.SMSUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Api(tags = "PassportController 通信证接口模块")
@RequestMapping("passport")
@RestController
public class PassportController {
    private String MY_PHONE_NUM = "17307484099";
    @Autowired
    private SMSUtils smsUtils;

    @PostMapping("getSMSCode")
    public GraceJSONResult getSMSCode(@RequestParam String mobile,
                                      HttpServletRequest request) throws Exception {

        if (StringUtils.isBlank(mobile)) {
            return GraceJSONResult.ok();
        }

        //TODO 获得用户ip
        String userIp = IPUtil.getRequestIp(request);
        log.info(userIp);
        //TODO 根据用户的ip 进行限制，限制用户60s 内只能获取一次验证码

        String code = (int)((Math.random() * 9 + 1) * 100000) + "";
//        smsUtils.sendSMS(MyInfo.getMobile(), code);
        smsUtils.sendSMS(mobile, code);
        log.info(code);

        //TODO 把验证码放入到Redis 中，用于后续认证
        return GraceJSONResult.ok();
    }
}
