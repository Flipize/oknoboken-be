package com.nilsson.oknoboken_be;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/message")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/submit")
    public Map<String, Object> handlePostRequest(@RequestBody Message message) {
        // Process the data (you can save it to a database, etc.)
        String name = message.getName();
        String email = message.getEmail();
        String content = message.getContent();

        return messageService.submitMessage(message);
    }
}
