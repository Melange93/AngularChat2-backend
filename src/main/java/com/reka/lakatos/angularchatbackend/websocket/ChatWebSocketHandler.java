package com.reka.lakatos.angularchatbackend.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reka.lakatos.angularchatbackend.entity.AppUser;
import com.reka.lakatos.angularchatbackend.entity.ChatMessage;
import com.reka.lakatos.angularchatbackend.entity.ChatRoom;
import com.reka.lakatos.angularchatbackend.service.ChatMessageService;
import com.reka.lakatos.angularchatbackend.service.ChatRoomService;
import com.reka.lakatos.angularchatbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.URI;
import java.util.*;

@Component
@RequiredArgsConstructor
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final List<WebSocketSession> webSocketSessions = new ArrayList<>();
    private final ChatMessageService chatService;
    private final UserService userService;
    private final ChatRoomService chatRoomService;

    private Map<WebSocketSession, URI> webSocketSessionURIMap = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        webSocketSessionURIMap.put(session, session.getUri());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ChatMessage chatMessage = objectMapper.readValue(message.getPayload(), ChatMessage.class);

        String userName = chatMessage.getUser().getUserName();
        AppUser userFromDb = userService.findUserByUserName(userName);
        chatMessage.setUser(userFromDb);

        long chatRoomId = chatMessage.getChatRoom().getId();
        ChatRoom chatRoomById = chatRoomService.getChatRoomById(chatRoomId);
        chatMessage.setChatRoom(chatRoomById);

        chatService.saveChatMessage(chatMessage);

        for (WebSocketSession webSocketSession : webSocketSessions) {
            webSocketSession.sendMessage(message);
        }

        for (WebSocketSession webSocketSession : webSocketSessionURIMap.keySet()) {
            if (webSocketSessionURIMap.get(webSocketSession).equals(session.getUri())) {
                webSocketSession.sendMessage(message);
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        webSocketSessionURIMap.remove(session, session.getUri());
    }
}
