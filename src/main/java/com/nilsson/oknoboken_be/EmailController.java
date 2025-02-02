package com.nilsson.oknoboken_be;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api/v1/message")
public class EmailController {

    @PostMapping("/submit")
    public Map<String, Object> handlePostRequest(@RequestBody Email email) {
        // Process the data (you can save it to a database, etc.)
        String name = email.getName();
        String address = email.getAddress();
        String emailMessage = email.getMessage();

        System.out.println(name + ", " + address + ", " + emailMessage);

        // Response
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Data received successfully");
        response.put("name", name);
        response.put("address", address);

        return response;
    }
}
