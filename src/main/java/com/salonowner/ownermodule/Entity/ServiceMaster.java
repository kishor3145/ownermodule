package com.salonowner.ownermodule.Entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "service_master")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;

    private String serviceName;
    private String serviceType;
    private Double price;
    private Double discount;
    private String serviceTime;
    private String location;
    private String openingTime;
    private String imageUrl;

    private Long ownerId;
}
