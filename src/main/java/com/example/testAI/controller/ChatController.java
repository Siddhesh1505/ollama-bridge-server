package com.example.testAI.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    // Spring Boot automatically injects the ChatClient for you
    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/ai/chat")
    public String generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }
}
