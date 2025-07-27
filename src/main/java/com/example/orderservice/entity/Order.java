package com.example.orderservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total")
    private BigDecimal totalPrice;

    @Column(name = "quantity")
    private Long ticketCount;

    @CreationTimestamp
    @Column(name = "placed_at")
    private LocalDateTime placed_at;

    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "customer_id")
    private Long customerId;
}

