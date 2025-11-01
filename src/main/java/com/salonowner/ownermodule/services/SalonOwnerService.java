package com.salonowner.ownermodule.services;

import com.salonowner.ownermodule.Entity.ApprovalStatus;
import com.salonowner.ownermodule.Entity.SalonOwner;
import com.salonowner.ownermodule.repository.SalonOwnerRepository;
import org.springframework.stereotype.Service;


@Service
public class SalonOwnerService {

    private final SalonOwnerRepository salonOwnerRepository;

    public SalonOwnerService(SalonOwnerRepository salonOwnerRepository) {
        this.salonOwnerRepository = salonOwnerRepository;
    }

    // Register new salon owner
    public SalonOwner registerOwner(SalonOwner owner) {
        owner.setStatus(ApprovalStatus.PENDING_APPROVAL);
        return salonOwnerRepository.save(owner);
    }

    // Upload license
    public SalonOwner updateLicense(Long ownerId, String path) {
        SalonOwner owner = salonOwnerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        owner.setLicenseImagePath(path);
        return salonOwnerRepository.save(owner);
    }

    // View profile
    public SalonOwner getOwnerProfile(Long ownerId) {
        return salonOwnerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
    }

    // Login validation
    public SalonOwner login(String email, String password) {
        SalonOwner owner = salonOwnerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!owner.getPassword().equals(password))
            throw new RuntimeException("Invalid email or password");

        if (owner.getStatus() != ApprovalStatus.APPROVED)
            throw new RuntimeException("Account not approved by admin yet");

        return owner;
    }
}
