package com.salonowner.ownermodule.repository;
import com.salonowner.ownermodule.Entity.ServiceMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceMaster, Long> {
    List<ServiceMaster> findByOwnerId(Long ownerId);
}
