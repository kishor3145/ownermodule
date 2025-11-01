package com.salonowner.ownermodule.repository;

import com.salonowner.ownermodule.Entity.SalonOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalonOwnerRepository extends JpaRepository<SalonOwner, Long> {
    Optional<SalonOwner> findByEmail(String email);
}

