package com.cancan.wsclient.websocket;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

/**
 * @program: cancan-java-share
 * @description: websocket客户端
 * @author: czchen
 * @date: 2022-06-29 13:40:59
 */
@Slf4j
public class WebsocketRunClient extends WebSocketClient {

    public WebsocketRunClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        log.info("[websocket] Websocket客户端连接成功");
    }

    @Override
    public void onMessage(String msg) {
        log.info("[websocket] 收到消息：{}",msg);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.info("[websocket] Websocket客户端关闭");
        System.out.println("Connection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: " + reason);
    }

    @Override
    public void onError(Exception e) {
        log.info("[websocket] Websocket客户端出现异常, 异常原因为：{}",e.getMessage());
    }
}
