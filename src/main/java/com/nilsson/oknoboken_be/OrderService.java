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
public class OrderService {
    @Value("${orders.dir}")
    private String orderDirectory;
    public Map<String, Object> submitOrder(Order order) {

        System.out.println(order.toString());
        Map<String, Object> response = new HashMap<>();

        // Response
        if (createOrder(order)) {
            response.put("success", true);
            response.put("message", "Order received successfully");
            response.put("name", order.getName());
            response.put("email", order.getEmail());
        } else {
            response.put("success", false);
            response.put("message", "Order was not created successfully");
            response.put("name", order.getName());
            response.put("email", order.getEmail());
        }
        return response;
    }

    public boolean createOrder(Order order) {
        String pattern = "yyyyMMdd_HHmmss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        String filename = orderDirectory + order.getEmail() + "_" + date + ".json";
        try {
            File myObj = new File(filename);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
                    Gson gson = new Gson();
                    String json = gson.toJson(order);
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

