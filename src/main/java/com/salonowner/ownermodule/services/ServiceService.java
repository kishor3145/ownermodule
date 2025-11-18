package com.salonowner.ownermodule.services;


import com.salonowner.ownermodule.Entity.ServiceMaster;
import java.util.List;

public interface ServiceService {

    // ➕ Add new service
    ServiceMaster addService(ServiceMaster service);

    // 📋 Get all services by owner
    List<ServiceMaster> getServicesByOwner(Long ownerId);

    // ✏️ Update service
    ServiceMaster updateService(Long id, ServiceMaster updatedService);

    // ❌ Delete service
    void deleteService(Long id);
}

