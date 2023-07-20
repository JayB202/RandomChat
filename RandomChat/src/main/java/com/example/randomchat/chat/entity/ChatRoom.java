package com.example.randomchat.chat.entity;

import static java.awt.DefaultKeyboardFocusManager.*;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.WebSocketSession;

import com.example.randomchat.chat.dto.ChatMessageDto;
import com.example.randomchat.chat.service.ChatService;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatRoom {
	private String roomId;
	private String name;
	private Set<WebSocketSession> webSocketSessions = new HashSet<>();

	@Builder
	public ChatRoom(String roomId, String name){
		this.roomId = roomId;
		this.name = name;
	}

	public void handlerActions(WebSocketSession webSocketSession, ChatMessageDto chatMessageDto, ChatService chatService){
		if (chatMessageDto.getMessageType().equals(ChatMessageDto.MessageType.ENTER)){
			webSocketSessions.add(webSocketSession);
			chatMessageDto.setMessage(chatMessageDto.getSender() +"님이 입장하셨습니다.");
		}

		sendMessage(chatMessageDto, chatService);
	}

	private <T> void sendMessage(T message, ChatService chatService){
		webSocketSessions.parallelStream()
			.forEach(webSocketSession -> chatService.sendMessage(webSocketSession, message));
	}
}
