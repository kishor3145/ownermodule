package com.salonowner.ownermodule.controller;

import com.salonowner.ownermodule.Entity.ServiceMaster;
import com.salonowner.ownermodule.services.impl.ServiceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

    // ✅ Add Service (React sends FormData)
    @PostMapping(value = "/add", consumes = "multipart/form-data")
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
    ) throws IOException {

        String uploadFolder = System.getProperty("user.dir") + "/uploads/services/";
        File folder = new File(uploadFolder);

        if (!folder.exists()) folder.mkdirs();

        String filePath = uploadFolder + image.getOriginalFilename();
        image.transferTo(new File(filePath));

        ServiceMaster service = new ServiceMaster();
        service.setServiceName(serviceName);
        service.setServiceType(serviceType);
        service.setPrice(Double.parseDouble(price));
        service.setDiscount(Double.parseDouble(discount));
        service.setServiceTime(serviceTime);
        service.setLocation(location);
        service.setOpeningTime(openingTime);
        service.setOwnerId(ownerId);
        service.setImageUrl(filePath); // ✅ Save image path

        return serviceService.addService(service);
    }

    // ✅ Get services by Owner (Frontend expects `serviceId`)
    @GetMapping("/owner/{ownerId}")
    public List<ServiceMaster> getServicesByOwner(@PathVariable Long ownerId) {
        return serviceService.getServicesByOwner(ownerId);
    }

    // ✅ Update service (React sends JSON)
    @PutMapping("/update/{id}")
    public ServiceMaster updateService(@PathVariable Long id, @RequestBody ServiceMaster service) {
        return serviceService.updateService(id, service);
    }

    // ✅ Delete service (Matches frontend)
    @DeleteMapping("/delete/{id}")
    public String deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
        return "Service deleted successfully!";
    }

    // ✅ Optional: All services (if needed)
    @GetMapping
    public List<ServiceMaster> getAll() {
        return serviceService.getAllServices();
    }
}
