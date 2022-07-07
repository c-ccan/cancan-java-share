package com.cancan.springwebsocketsimple.util;

import lombok.extern.slf4j.Slf4j;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author By ZengPeng
 * @Description
 * @date in  2020/7/9 16:25
 * @Modified By
 */
@Slf4j
public class WebSocketUtils {
    /**
     * 将客户端标识和对应会话缓存到map
     */
    public static Map<String, Session> sessionMap = new HashMap<>();

    private static void sendObjectMessage(Session session, Object obj)  {
        if (session != null) {
            synchronized (session.toString()){
                try {
                    session.getBasicRemote().sendObject(obj);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void sendObjectMessage(String sessionId ,Object obj)  {
        Session session = sessionMap.get(sessionId);
        if(session!=null){
            sendObjectMessage(session,obj);
        }else{
            //因为多个session抢占同一个sessionId,后面的session释放了sessionId；导致sessionMap中此sessionId没有了对应的session
            //原因2:实时推送服务在分布式环境下，当前sessionId,可能在别的服务节点上
            log.info("这个sessionId的session未被释放:--------------------"+sessionId);
        }
    }
    /**
     * 实现服务器主动推送
     */
    public static void sendAllClientMessage(String message) throws IOException {
        for (String s : sessionMap.keySet()) {
           sessionMap.get(s).getBasicRemote().sendText(message);
        }
    }
    /**
     * 找到对应的客户端会话并推送消息
     */
    public static void sendMessage(String sessionId, String message) throws IOException {
        Session session = sessionMap.get(sessionId);

        if (session != null) {
            synchronized (session.toString()){
                session.getBasicRemote().sendText(message);
            }
        }
    }

}
