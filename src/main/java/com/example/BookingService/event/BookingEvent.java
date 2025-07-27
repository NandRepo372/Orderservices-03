package com.example.BookingService.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BookingEvent {
    private Long eventId;
    private Long userId;
    private Long tikcetCount;
    private BigDecimal tikcetPrice;
}