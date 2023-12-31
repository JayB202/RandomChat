package com.example.randomchat.chat;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.randomchat.chat.entity.ChatRoom;
import com.example.randomchat.chat.service.ChatService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {
	private final ChatService chatService;

	@PostMapping
	public ChatRoom createRoom(@RequestBody String name) {
		return chatService.createRoom(name);
	}

	@GetMapping
	public List<ChatRoom> findAllRoom() {
		return chatService.findAllRoom();
	}
}