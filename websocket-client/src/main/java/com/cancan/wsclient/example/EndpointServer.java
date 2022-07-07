package com.cancan.wsclient.example;

import lombok.var;
import org.java_websocket.*;
import org.java_websocket.server.*;
import org.java_websocket.client.*;
import org.java_websocket.handshake.*;
import java.util.*;
import java.net.*;

/**
 * 参考来着：https://github.com/TooTallNate/Java-WebSocket
 */
interface Endpoint {
	void onOpen(WebSocket socket);
	// add other event handlers here
}

public class EndpointServer extends WebSocketServer {
	private Map<String, Endpoint> endpoints = Collections.synchronizedMap(new HashMap<>());

//	public static void main(String... args) {
//		var server = new EndpointServer();
//		server.endpoints.put("/greeting", socket -> socket.send("Hello!"));
//		server.endpoints.put("/chat", socket -> socket.send("You have connected to chat"));
//		server.start();
//
//		var client = new Client("ws://localhost:" + server.getPort() + "/chat");
//		client.connect();
//	}

	public void onStart() {
		// ...
	}

	public void onOpen(WebSocket socket, ClientHandshake handshake) {
		String path = URI.create(handshake.getResourceDescriptor()).getPath();
		Endpoint endpoint = endpoints.get(path);
		if(endpoint != null)
			endpoint.onOpen(socket);
	}

	public void onMessage(WebSocket socket, String message) {
		// ...
	}

	public void onClose(WebSocket socket, int code, String message, boolean remote) {
		// ...
	}

	public void onError(WebSocket socket, Exception e) {
		e.printStackTrace();
	}
}


class Client extends WebSocketClient {
	public Client(String uri) {
		super(URI.create(uri));
	}
	public void onOpen(ServerHandshake handshake) {
	}

	public void onMessage(String message) {
		System.out.println(this + " received message: " + message);
	}

	public void onClose(int code, String message, boolean remote) {
	}

	public void onError(Exception e) {
		e.printStackTrace();
	}
}