package com.cancan.springwebsocketsimple.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationStartup implements CommandLineRunner {
	/** 应用的访问端口 */
	@Value("${server.port:8080}")
	private int port;

	/** 应用的访问路径上下文 */
	@Value("${server.servlet.context-path:}")
	private String contextPath;
	
	@Override
	public void run(String... args) {
		log.info("项目启动成功！访问地址：{}", "http://" + ServerUtils.getHostIp() + ":" + port + contextPath + "/index.html");
	}
}
