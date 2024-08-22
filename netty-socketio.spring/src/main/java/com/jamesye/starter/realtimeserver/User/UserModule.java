package com.jamesye.starter.realtimeserver.User;

import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
public class UserModule {

    private static final Logger log = LoggerFactory.getLogger(UserModule.class);

    private final UserService userService;
    private ObjectMapper objectMapper ;

    private final SocketIONamespace namespace;


    @Autowired
    public UserModule(UserService userService, ObjectMapper objectMapper, SocketIOServer server) {
        this.userService = userService;
        this.objectMapper = objectMapper;
        this.namespace = server.addNamespace("/user");
        this.namespace.addConnectListener(onConnected());
        this.namespace.addDisconnectListener(onDisconnected());
        this.namespace.addEventListener("getUserId", LinkedHashMap.class, onGetUserInfo());
    }
    private DataListener<LinkedHashMap> onGetUserInfo() {
        return (client, data, ackSender) -> {
            log.debug("Client[{}] - Received user request '{}'", client.getSessionId().toString(), data);

            Integer userId = (Integer) data.get("userId");
            JsonNode requestNode = objectMapper.readTree(String.valueOf(data.get("contextInfo")));
            ContextInfo contextInfo = objectMapper.treeToValue(requestNode, ContextInfo.class);

            User user = userService.getUserInfo(userId, contextInfo);
            if (user != null) {
                client.sendEvent("sendUserInfo", user);
            } else {
                client.sendEvent("sendUserInfo", "User not found");
            }
        };
    }

    private ConnectListener onConnected() {
        return client -> {
            HandshakeData handshakeData = client.getHandshakeData();
            log.debug("Client[{}] - Connected to chat module through '{}'", client.getSessionId().toString(), handshakeData.getUrl());
        };
    }

    private DisconnectListener onDisconnected() {
        return client -> {
            log.debug("Client[{}] - Disconnected from chat module.", client.getSessionId().toString());
        };
    }



}
