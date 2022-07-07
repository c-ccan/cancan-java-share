package com.cancan.springwebsocketsimple;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringWebsocketSimpleApplicationTests {

    @Test
    void contextLoads() {

    }

    public static void main(String[] args) {
        String message = "{\"command\":20021,\"guid\":\"passenger-3439c4c4-f3d7-4b9e-a45f-3a104fc82faa\",\"param\":{\"message\":\"PidsSetMessageOperation Msg\",\"result\":0},\"version\":\"v1.0\"}";
        Object parse = JSON.parse(message);
        parse.toString();
    }

}
