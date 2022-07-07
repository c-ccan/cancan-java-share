package com.cancan.wsclient.config;

import com.cancan.wsclient.properties.WebsocketClientConfiguration;
import com.cancan.wsclient.websocket.WebsocketRunClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.PortUnreachableException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @program: cancan-java-share
 * @description: 创建Websocket客户端的Bean
 * @author: czchen
 * @date: 2022-06-29 13:52:37
 */
@Slf4j
@Configuration
public class WebsocketClientBeanConfig {

    @Bean
    public WebsocketRunClient websocketRunClient(WebsocketClientConfiguration websocketClientConfiguration){
        String wsUrl = websocketClientConfiguration.getWsUrl();
        Boolean enableHeartbeat = websocketClientConfiguration.getEnableHeartbeat();
        Integer heartbeatInterval = websocketClientConfiguration.getHeartbeatInterval();
        Boolean enableReconnection = websocketClientConfiguration.getEnableReconnection();

        try {
            WebsocketRunClient websocketRunClient = new WebsocketRunClient(new URI(wsUrl));
            websocketRunClient.connect();
            websocketRunClient.setConnectionLostTimeout(0);

            new Thread(()->{
                while (true){
                    try {
                        Thread.sleep(heartbeatInterval);
                        if(enableHeartbeat){
                            websocketRunClient.send("[websocket] 心跳检测");
                            log.info("[websocket] 心跳检测");
                        }
                    } catch (Exception e) {
                        log.error("[websocket] 发生异常{}",e.getMessage());
                        try {
                            if(enableReconnection){
                                log.info("[websocket] 重新连接");
                                websocketRunClient.reconnect();
                                websocketRunClient.setConnectionLostTimeout(0);
                            }
                        }catch (Exception ex){
                            log.error("[websocket] 重连异常,{}",ex.getMessage());
                        }
                    }
                }
            }).start();

            return websocketRunClient;
        } catch (URISyntaxException ex) {
            log.error("[websocket] 连接异常,{}",ex.getMessage());
        }
        return null;
    }


}
