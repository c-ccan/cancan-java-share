package com.cancan.springbootwebsocketclientmultiple.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @program: cancan-java-share
 * @description: Websocket客户端配置
 * @author: czchen
 * @date: 2022-06-29 15:03:48
 */
@Configuration
@ConfigurationProperties(prefix = "cancan.websocket.client")
public class WebsocketClientConfiguration {

    private List<ServerProperties> config;

    public static class ServerProperties {
        /**
         * websocket server ws://ip:port
         */
        private String wsUrl;
        /**
         * websocket server name,用于区分不同的服务端
         */
        private String wsName;
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

        public String getWsName() {
            return wsName;
        }

        public void setWsName(String wsName) {
            this.wsName = wsName;
        }
    }

    public List<ServerProperties> getConfig() {
        return config;
    }

    public void setConfig(List<ServerProperties> config) {
        this.config = config;
    }
}
