package com.salonowner.ownermodule.controller;

import com.salonowner.ownermodule.Entity.SalonOwner;
import com.salonowner.ownermodule.services.SalonOwnerService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/owner")
@CrossOrigin("*")
public class SalonOwnerController {

    private final SalonOwnerService salonOwnerService;

    public SalonOwnerController(SalonOwnerService salonOwnerService) {
        this.salonOwnerService = salonOwnerService;
    }

    // Register new owner
    @PostMapping("/register")
    public SalonOwner register(@RequestBody SalonOwner owner) {
        return salonOwnerService.registerOwner(owner);
    }

    // Upload license file
    @PostMapping("/upload-license/{ownerId}")
    public String uploadLicense(@PathVariable Long ownerId, @RequestParam("file") MultipartFile file) throws IOException {
        String uploadDir = "uploads/licenses/";
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        String filePath = uploadDir + file.getOriginalFilename();
        file.transferTo(new File(filePath));

        salonOwnerService.updateLicense(ownerId, filePath);
        return "License uploaded successfully!";
    }

    // Owner login
    @PostMapping("/login")
    public SalonOwner login(@RequestParam String email, @RequestParam String password) {
        return salonOwnerService.login(email, password);
    }

    // View profile
    @GetMapping("/{ownerId}/profile")
    public SalonOwner getProfile(@PathVariable Long ownerId) {
        return salonOwnerService.getOwnerProfile(ownerId);
    }
}
