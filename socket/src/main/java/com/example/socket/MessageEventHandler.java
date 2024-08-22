package com.example.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnEvent;
import org.springframework.stereotype.Component;

@Component
public class MessageEventHandler {

    @OnEvent("message")
    public void onMessage(SocketIOClient client, String message) {
        // Broadcast the message to all connected clients
        client.getNamespace().getBroadcastOperations().sendEvent("message", message);
    }
}
