package com.cancan.springwebsocketsimple.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.net.*;
import java.util.Date;
import java.util.Enumeration;

public final class ServerUtils {
    private static final Logger log = LoggerFactory.getLogger(ServerUtils.class);

    private ServerUtils() {
    }

    public static String getHostIp() {
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();

            while(true) {
                NetworkInterface netInterface;
                do {
                    do {
                        do {
                            if (!networkInterfaces.hasMoreElements()) {
                                return "127.0.0.1";
                            }

                            netInterface = (NetworkInterface)networkInterfaces.nextElement();
                        } while(netInterface.isLoopback());
                    } while(netInterface.isVirtual());
                } while(!netInterface.isUp());

                Enumeration inetAddresses = netInterface.getInetAddresses();

                while(inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress)inetAddresses.nextElement();
                    if (inetAddress instanceof Inet4Address) {
                        String ip = inetAddress.getHostAddress();
                        if (!ip.endsWith(".1")) {
                            return ip;
                        }
                    }
                }
            }
        } catch (SocketException var5) {
            log.error("获取IP地址异常", var5);
            return "127.0.0.1";
        }
    }
}