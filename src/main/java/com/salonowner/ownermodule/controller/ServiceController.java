package com.salonowner.ownermodule.controller;

import com.salonowner.ownermodule.Entity.ServiceMaster;
import com.salonowner.ownermodule.services.impl.ServiceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@CrossOrigin("*")
@Slf4j
public class ServiceController {

    private final ServiceServiceImpl serviceService;

    public ServiceController(ServiceServiceImpl serviceService) {
        this.serviceService = serviceService;
    }

    @PostMapping("/add")
    public ServiceMaster addService(
            @RequestParam("serviceName") String serviceName,
            @RequestParam("serviceType") String serviceType,
            @RequestParam("price") String price,
            @RequestParam("discount") String discount,
            @RequestParam("serviceTime") String serviceTime,
            @RequestParam("location") String location,
            @RequestParam("openingTime") String openingTime,
            @RequestParam("image") MultipartFile image,
            @RequestParam("ownerId") Long ownerId
    ) {
        System.out.println("✅ Backend API hit successfully!");
        System.out.println("Service Name: " + serviceName);
        System.out.println("Image received: " + image.getOriginalFilename());

        ServiceMaster service = new ServiceMaster();
        service.setServiceName(serviceName);
        service.setServiceType(serviceType);
        service.setPrice(Double.parseDouble(price));
        service.setDiscount(Double.parseDouble(discount));
        service.setServiceTime(serviceTime);
        service.setLocation(location);
        service.setOpeningTime(openingTime);
        service.setOwnerId(ownerId);

        // Save or handle image as needed
        return serviceService.addService(service);
    }


    // 📋 Get all services by owner
    @GetMapping("/owner/{ownerId}")
    public List<ServiceMaster> getServicesByOwner(@PathVariable Long ownerId) {
        return serviceService.getServicesByOwner(ownerId);
    }

    // ✏️ Update existing service
    @PutMapping("/update/{id}")
    public ServiceMaster updateService(@PathVariable Long id, @RequestBody ServiceMaster service) {
        return serviceService.updateService(id, service);
    }

    // ❌ Delete service
    @DeleteMapping("/delete/{id}")
    public String deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
        return "Service deleted successfully!";
    }

    @GetMapping("/test")
    public String testApi() {
        System.out.println("🔥 /api/services/test hit successfully!");
        return "Backend is working!";
    }

}

