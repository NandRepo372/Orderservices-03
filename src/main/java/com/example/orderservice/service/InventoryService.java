package com.example.orderservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InventoryService {

    @Value("${inventory.service.url}")
    private String inventoryUrl;

    public void upateInventory(Long eventId, Long ticketCount) {
        RestTemplate restTemplate = new RestTemplate();
        String url = inventoryUrl + "/event/" + eventId + "/capacity/" + ticketCount;
        try {
            restTemplate.put(url, null);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update inventory for event ID: " + eventId, e);
        }
    }
}
