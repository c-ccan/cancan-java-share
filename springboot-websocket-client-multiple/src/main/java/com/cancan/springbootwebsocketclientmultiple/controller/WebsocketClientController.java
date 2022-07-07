package com.cancan.springbootwebsocketclientmultiple.controller;

import com.cancan.springbootwebsocketclientmultiple.properties.WebsocketClientConfiguration;
import com.cancan.springbootwebsocketclientmultiple.websocket.WebsocketRunClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @program: cancan-java-share
 * @description: Websocket客户端Controller
 * @author: czchen
 * @date: 2022-06-29 14:24:07
 */
@RestController
public class WebsocketClientController {

    @Autowired
    private Map<String, WebsocketRunClient> websocketRunClientMap;

    @RequestMapping("/send")
    public String send(){

        WebsocketRunClient websocketRunClient = websocketRunClientMap.get("ws-01");
        websocketRunClient.send("我是客户端，ws-01服务端你好啊！");

        return "发送成功";
    }

}
