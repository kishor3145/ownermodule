package com.salonowner.ownermodule.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int rating;

    @Column(length = 2000)
    private String comment;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    // keep time as simple string like "10:15 AM"
    private String time;

    @Column(length = 2000)
    private String response;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime respondedAt;


    @ManyToOne
    @JoinColumn(name = "owner_id")
    private SalonOwner salonOwner;

    //from customer module
    private Long customerId;

}

