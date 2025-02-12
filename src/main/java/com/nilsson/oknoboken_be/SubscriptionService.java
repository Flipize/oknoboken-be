package com.nilsson.oknoboken_be;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SubscriptionService {
    @Value("${subscription.dir}")
    private String filename;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public Map<String, Object> subscribe(Subscription subscription) {
        String email = subscription.getEmail();
        Map<String, Object> response = new HashMap<>();
        boolean success;
        List<Subscription> subscriptions = readSubscribers();
        if (subscriberExists(subscriptions, email)) {
            System.out.println("A subscription for " + email + " already exists. Not adding again.");
            success = true;
        } else {
            String pattern = "yyyy-MM-dd H:m:s";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(new Date());
            subscription.setDate(date);
            subscriptions.add(subscription);
            success = writeSubscribers(subscriptions);
            System.out.println("A subscription for " + email + " was added.");
        }

        // Response
        if (success) {
            response.put("success", true);
            response.put("message", "User is subscribed successfully");
            response.put("email", email);
        } else {
            response.put("success", false);
            response.put("message", "User did not subscribe successfully");
            response.put("email", email);
        }
        return response;
    }

    public List<Subscription> readSubscribers() {
        try (Reader reader = new FileReader(filename)) {
            Type listType = new TypeToken<List<Subscription>>() {}.getType();
            List<Subscription> subscriptions = GSON.fromJson(reader, listType);
            if (subscriptions == null) {
                return new ArrayList<>();
            }
            return subscriptions;
        } catch (FileNotFoundException e) {
            return new ArrayList<>(); // If file doesn't exist, return an empty list
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean writeSubscribers(List<Subscription> subscriptions) {
        try (Writer writer = new FileWriter(filename)) {
            GSON.toJson(subscriptions, writer);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean subscriberExists(List<Subscription> subscriptions, String email) {
        return subscriptions.stream().anyMatch(sub -> sub.getEmail().equalsIgnoreCase(email));
    }

    public Map<String, Object> unsubscribe(String email) {
        Map<String, Object> response = new HashMap<>();
        List<Subscription> subscriptions = readSubscribers();
        boolean success;
        boolean subExists = subscriberExists(subscriptions, email);
        if (subExists) {
            removeByEmail(subscriptions, email);
            success = writeSubscribers(subscriptions);
        } else {
            System.out.println("Subscriber was not found. Nothing to do.");
            success = true;
        }

        // Response
        if (success) {
            response.put("success", true);
            response.put("message", "User was unsubscribed successfully");
            response.put("email", email);
        } else {
            response.put("success", false);
            response.put("message", "User was not unsubscribed successfully");
            response.put("email", email);
        }
        return response;
    }

    public void removeByEmail(List<Subscription> subscriptions, String name) {
        subscriptions.removeIf(sub -> sub.getEmail().equalsIgnoreCase(name));
    }

}
