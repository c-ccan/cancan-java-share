package com.cancan.wsclient.controller;

import com.cancan.wsclient.websocket.WebsocketRunClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: cancan-java-share
 * @description: Websocket客户端Controller
 * @author: czchen
 * @date: 2022-06-29 14:24:07
 */
@RestController
public class WebsocketClientController {

    @Autowired
    private WebsocketRunClient websocketRunClient;

    @RequestMapping("/send")
    public String send(){
        websocketRunClient.send("我是客户端，发送数据...");
        return "发送成功";
    }

}
