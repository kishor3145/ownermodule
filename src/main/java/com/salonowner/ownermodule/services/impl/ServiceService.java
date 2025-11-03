package com.salonowner.ownermodule.services.impl;

import com.salonowner.ownermodule.Entity.ServiceMaster;
import com.salonowner.ownermodule.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    // ➕ Add new service
    public ServiceMaster addService(ServiceMaster service) {
        if (service.getImageUrl() == null || service.getImageUrl().isEmpty()) {
            service.setImageUrl("https://via.placeholder.com/60");
        }
        return serviceRepository.save(service);
    }

    // 📋 Get all services by owner
    public List<ServiceMaster> getServicesByOwner(Long ownerId) {
        return serviceRepository.findByOwnerId(ownerId);
    }

    // ✏️ Update service
    public ServiceMaster updateService(Long id, ServiceMaster updatedService) {
        ServiceMaster existing = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        existing.setServiceName(updatedService.getServiceName());
        existing.setServiceType(updatedService.getServiceType());
        existing.setPrice(updatedService.getPrice());
        existing.setDiscount(updatedService.getDiscount());
        existing.setServiceTime(updatedService.getServiceTime());
        existing.setLocation(updatedService.getLocation());
        existing.setOpeningTime(updatedService.getOpeningTime());
        existing.setImageUrl(updatedService.getImageUrl());
        return serviceRepository.save(existing);
    }

    // ❌ Delete
    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }
}
