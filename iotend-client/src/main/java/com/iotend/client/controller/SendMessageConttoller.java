package com.iotend.client.controller;

import com.iotend.client.echo.ClientConn;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 发送消息
 * @author: huang
 * @create: 2020-10-16 10:51
 */
@RestController
@RequestMapping({"/send"})
public class SendMessageConttoller {
    @RequestMapping(value = "message")
    public String selInquire(){
        ClientConn clientConn = new ClientConn();
        clientConn.NettyConnention();
        return "success.";
    }
}
