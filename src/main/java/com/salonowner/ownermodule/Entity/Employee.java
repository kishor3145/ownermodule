package com.salonowner.ownermodule.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "Salon_Employees")
public class Employee {

    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    private String employeeName;
    private String contactNumber;
    private Double customerRating;
    private String employmentStatus;
    private Integer experienceYears;
    private LocalDate joiningDate;
    private Double salary;
    private Long salonId;

}