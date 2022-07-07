package com.cancan.wsclient.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @program: cancan-java-share
 * @description: websocket客户端配置
 * @author: czchen
 * @date: 2022-06-29 10:43:04
 */
@Configuration
@ConfigurationProperties(prefix="cancan.websocket.client.config")
public class WebsocketClientConfiguration {

   /**
    * websocket server ws://ip:port
    */
   private String wsUrl;
   /**
    * 是否启用心跳监测 默认开启
    */
   private Boolean enableHeartbeat;
   /**
    * 心跳监测间隔 默认20000毫秒
    */
   private Integer heartbeatInterval;
   /**
    * 是否启用重连接 默认启用
    */
   private Boolean enableReconnection;

   public String getWsUrl() {
      return wsUrl;
   }

   public void setWsUrl(String wsUrl) {
      this.wsUrl = wsUrl;
   }

   public Boolean getEnableHeartbeat() {
      return enableHeartbeat;
   }

   public void setEnableHeartbeat(Boolean enableHeartbeat) {
      this.enableHeartbeat = enableHeartbeat;
   }

   public Integer getHeartbeatInterval() {
      return heartbeatInterval;
   }

   public void setHeartbeatInterval(Integer heartbeatInterval) {
      this.heartbeatInterval = heartbeatInterval;
   }

   public Boolean getEnableReconnection() {
      return enableReconnection;
   }

   public void setEnableReconnection(Boolean enableReconnection) {
      this.enableReconnection = enableReconnection;
   }
}
