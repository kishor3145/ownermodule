package com.salonowner.ownermodule.controller;

import com.salonowner.ownermodule.Entity.ServiceMaster;
import com.salonowner.ownermodule.services.ServiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@CrossOrigin("*")
@Slf4j
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @PostMapping("/add")
    public ServiceMaster addService(
            @RequestPart("serviceName") String serviceName,
            @RequestPart("serviceType") String serviceType,
            @RequestPart("price") String price,
            @RequestPart("discount") String discount,
            @RequestPart("serviceTime") String serviceTime,
            @RequestPart("location") String location,
            @RequestPart("openingTime") String openingTime,
            @RequestPart("image") MultipartFile image
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

