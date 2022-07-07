package com.cancan.springwebsocketsimple.websocket;

import com.cancan.springwebsocketsimple.util.ServerEncoder;
import com.cancan.springwebsocketsimple.util.WebSocketUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @program: cancan-java-share
 * @description: ws基础服务
 * @author: czchen
 * @date: 2022-04-26 10:47:56
 */
@ServerEndpoint(value = "/websocket", encoders = {ServerEncoder.class})
@Component
@Slf4j
public class WSBasicServer {

    //开启连接
    @OnOpen
    public void onOpen(Session session) {
        String sessionId = session.getId();
        //设置session空闲时间
        session.setMaxIdleTimeout(600 * 1000L);
        WebSocketUtils.sessionMap.put(sessionId, session);
        try {
            sendMessage(session, "连接成功");
        } catch (IOException e) {
            log.error("用户:" + sessionId + ",网络异常!!!!!!");
        }
    }

    @OnMessage
    public void OnMessage(Session session, String message) {
        String sessionId = session.getId();
        log.info("用户消息:" + sessionId + ",报文:" + message);
    }

    //关闭连接
    @OnClose
    public void onClose(Session session) {
        String sessionId = session.getId();
        log.info("当前session断开了连接：" + sessionId);
    }

    //发生异常的情况
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:" + session.getId() + ",原因:" + error.getMessage());
    }

    /**
     * 实现服务器主动推送 至当前session
     */
    private void sendMessage(Session session, String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }

}
