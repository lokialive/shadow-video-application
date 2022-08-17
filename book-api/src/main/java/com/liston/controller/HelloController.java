package com.liston.controller;

import com.liston.grace.result.GraceJSONResult;
import com.liston.utils.SMSUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "HelloController test api")
@RestController
public class HelloController {
    @Autowired
    private SMSUtils smsUtils;

    @GetMapping("sms")
    public Object sms() throws Exception {

        String code  = "123456";
        smsUtils.sendSMS("17307484099",code);
        return GraceJSONResult.ok();
    }
}
