package com.salonowner.ownermodule.Entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@Entity
@Table(name = "salon_owner")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@CrossOrigin(origins = "*")
public class SalonOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ownerId;

    private String ownerName;
    private String email;
    private String password;
    private String businessId;
    private String salonName;
    private String location;
    private String licenseImagePath;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus status = ApprovalStatus.PENDING_APPROVAL;
}

