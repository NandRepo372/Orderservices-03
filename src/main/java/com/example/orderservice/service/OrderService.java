package com.example.orderservice.service;

import com.example.BookingService.event.BookingEvent;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryService inventoryService;

    @Autowired
    public OrderService(OrderRepository orderRepository, InventoryService inventoryService) {
        this.orderRepository = orderRepository;
        this.inventoryService = inventoryService;
    }


    @KafkaListener(topics = "booking", groupId = "order-service")
    public void Listener(BookingEvent bookingEvent){
        log.info("received event : {}", bookingEvent);
        //create order in DB
        Order order = createOrder(bookingEvent);
        orderRepository.saveAndFlush(order);
       //Update Inventory
        inventoryService.upateInventory(bookingEvent.getEventId(), bookingEvent.getTikcetCount());
    }

    private Order createOrder(BookingEvent bookingEvent) {
        return Order.builder()
                .eventId(bookingEvent.getEventId())
                .customerId(bookingEvent.getUserId())
                .ticketCount(bookingEvent.getTikcetCount())
                .totalPrice(bookingEvent.getTikcetPrice())
                .build();
    }
}
