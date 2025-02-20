package com.example.chatbot;

import org.springframework.web.bind.annotation.*;

@RestController
public class ChatController {

    private final NLPService nlpService;

    public ChatController() throws Exception {
        this.nlpService = new NLPService();
    }

    @PostMapping("/chat")
    public String getResponse(@RequestBody String userInput) throws Exception {
        return nlpService.classifyInput(userInput);
    }
}
