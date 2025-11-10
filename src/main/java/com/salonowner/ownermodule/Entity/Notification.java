package com.salonowner.ownermodule.Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    private Long bookingId;
    private Long offerId;
    private Long senderId;
    private Long receiverId;

    private String message;
    private String type;           // BOOKING_CONFIRMATION, CANCELLED, RESCHEDULE, OFFER, etc.
    private String deliveryMode;   // EMAIL, SMS, IN_APP
    private Boolean readStatus = false;
//    @ManyToOne
//    @JoinColumn(name = "booking_id")   // FK column in DB
//    private BookingRecord bookingRecord;

//    @ManyToOne
//    @JoinColumn(name = "sender_id")
//    private SalonOwner salonOwner;

    private LocalDateTime sentTime = LocalDateTime.now();
}

