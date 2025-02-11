package com.nilsson.oknoboken_be;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/subscription")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/add")
    public Map<String, Object> handlePostRequest(@RequestBody String email) {
        // Process the data (you can save it to a database, etc.)

        return subscriptionService.subscribe(email);
    }

    @DeleteMapping("/remove")
    public Map<String, Object> handleDeleteRequest(@RequestBody String email) {
        // Process the data (you can save it to a database, etc.)

        return subscriptionService.unsubscribe(email);
    }
}
