package com.cancan.springwebsocketsimple.websocket;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

@Setter
@Getter
@ServerEndpoint("/{uuid}") //类似RequestMapping的地址
@Component
public class WebSocketServer {

    private Session session; //客户端会话
    //存放每个客户端的连接会话
    public static ConcurrentHashMap<String,WebSocketServer> clients = new ConcurrentHashMap<>();

    //开启连接
    @OnOpen
    public void onOpen(Session session, @PathParam( "uuid") String uuid){
        System.out.println("66666666666===>"+uuid);
        this.session = session;
        clients.put(uuid,this);
    }

    @OnMessage
    public void OnMessage(String msg, @PathParam( "uuid") String uuid){

        System.out.println("收到消息: "+msg);
    }

    //关闭连接
    @OnClose
    public void onClose(@PathParam( "uuid") String uuid){
        System.out.println("关闭socket连接"+uuid);
        clients.remove(uuid);
    }

    //发生异常的情况
    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }

}