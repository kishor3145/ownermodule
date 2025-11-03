package com.salonowner.ownermodule.services;


import com.salonowner.ownermodule.Entity.SalonOwner;

public interface SalonOwnerService {

    // Register new salon owner
    SalonOwner registerOwner(SalonOwner owner);

    // Upload license
    SalonOwner updateLicense(Long ownerId, String path);

    // View profile
    SalonOwner getOwnerProfile(Long ownerId);

    // Login validation
    SalonOwner login(String email, String password);
}
