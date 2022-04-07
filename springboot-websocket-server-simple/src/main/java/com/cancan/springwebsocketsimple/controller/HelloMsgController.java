package com.cancan.springwebsocketsimple.controller;

import com.cancan.springwebsocketsimple.websocket.WebSocketServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class HelloMsgController {

    @RequestMapping("sendMsg")
    public String sendMsg(String msg,String uuid){
        WebSocketServer webSocketServer = WebSocketServer.clients.get(uuid);
        //服务端发送消息
        try {
            webSocketServer.getSession().getBasicRemote().sendText(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "发生成功";
    }
}