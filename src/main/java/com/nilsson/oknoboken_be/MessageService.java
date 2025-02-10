package com.nilsson.oknoboken_be;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import com.google.gson.Gson;

@Service
public class MessageService {
    @Value("${message.dir}")
    private String messageDirectory;
    public Map<String, Object> submitMessage(Message message) {

        System.out.println(message.toString());
        Map<String, Object> response = new HashMap<>();

        // Response
        if (createMessage(message)) {
            response.put("success", true);
            response.put("message", "Data received successfully");
            response.put("name", message.getName());
            response.put("email", message.getEmail());
        } else {
            response.put("success", false);
            response.put("message", "Data received successfully");
            response.put("name", message.getName());
            response.put("email", message.getEmail());
        }
        return response;
    }

    public boolean createMessage(Message message) {
        String pattern = "yyyyMMdd_Hms";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        String filename = messageDirectory + message.getEmail() + "_" + date + ".json";
        try {
            File myObj = new File(filename);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
                    Gson gson = new Gson();
                    String json = gson.toJson(message);
                    writer.write(json);
                    System.out.println("Successfully wrote to the file " + filename + ".");
                    return true;
                } catch (IOException e) {
                    System.out.println("An error occurred when writing to the file " + filename + ".");
                    e.printStackTrace();
                    return false;
                }

            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred when creating the file " + filename + ".");
            e.printStackTrace();
        }
        return false;
    }

}
