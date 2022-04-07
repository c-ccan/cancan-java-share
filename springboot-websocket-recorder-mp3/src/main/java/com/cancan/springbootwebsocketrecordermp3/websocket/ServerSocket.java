package com.cancan.springbootwebsocketrecordermp3.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.*;
import java.util.Hashtable;
import java.util.Map;

@ServerEndpoint("/send/{key}")
@Component
public class ServerSocket {

    private static final Map<String, Session> connections = new Hashtable();
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    /***
     * @Description:打开连接
     * @Param: [id, 保存对方平台的资源编码
     * session]
     * @Return: void
     * @Author: Liting
     * @Date: 2019-10-10 09:22
     */
    @OnOpen
    public void onOpen(@PathParam("key") String id, Session session) {
        System.out.println(id + "连上了");
        connections.put(id, session);
    }

    /**
     * 接收消息
     */
    @OnMessage
    public void onMessage(@PathParam("key") String id, InputStream inputStream) {
        System.out.println("来自" + id);
        try {
            int rc = 0;
            byte[] buff = new byte[100];
            while ((rc = inputStream.read(buff, 0, 100)) > 0) {
                byteArrayOutputStream.write(buff, 0, rc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 异常处理
     *
     * @param throwable
     */
    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        //TODO 日志打印异常
    }

    /**
     * 关闭连接
     */
    @OnClose
    public void onClose(@PathParam("key") String id) {
        System.out.println(id + "断开");
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            file = new File("D:\\testtest.mp3");

            //输出流
            fos = new FileOutputStream(file);

            //缓冲流
            bos = new BufferedOutputStream(fos);

            //将字节数组写出
            bos.write(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        connections.remove(id);
    }
}