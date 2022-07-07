package com.cancan.springwebsocketsimple.util;


import com.alibaba.fastjson.JSONObject;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * ServerEncoder为了防止在websocket向客户端发送sendObject方法的时候提示如下错误：
 * javax.websocket.EncodeException: No encoder specified for object of class [class org.ywzn.po.Messagepojo]
 */

public class ServerEncoder implements Encoder.Text<Object>  {
    //代表websocket调用sendObject方法返回客户端的时候，必须返回的是Object对象
    @Override
    public String encode(Object result) {
        //将java对象转换为json字符串
        return JSONObject.toJSONString(result);
    }
    @Override
    public void init(EndpointConfig endpointConfig) { }

    @Override
    public void destroy() { }
}