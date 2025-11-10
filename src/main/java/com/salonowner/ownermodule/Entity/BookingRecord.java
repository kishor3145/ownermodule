package com.salonowner.ownermodule.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "booking_records")
public class BookingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate bookingDate;

    private Double price;

    private int rating;

    private Long salonId;

    private Long serviceId;

    private String status;

    private Long userId;


}
