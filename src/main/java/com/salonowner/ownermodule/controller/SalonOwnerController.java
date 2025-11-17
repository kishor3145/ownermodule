package com.salonowner.ownermodule.controller;

import com.salonowner.ownermodule.Entity.ApprovalStatus;
import com.salonowner.ownermodule.Entity.SalonOwner;
import com.salonowner.ownermodule.dto.LoginRequest;
import com.salonowner.ownermodule.services.SalonOwnerService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/owner")
@CrossOrigin(origins = "http://localhost:3000")
public class SalonOwnerController {

    private final SalonOwnerService salonOwnerService;

    // Define a proper upload directory
    private final String UPLOAD_DIR = "uploads/licenses/";

    public SalonOwnerController(SalonOwnerService salonOwnerService) {
        this.salonOwnerService = salonOwnerService;
        createUploadDirectory();
    }

    // Create upload directory on startup
    private void createUploadDirectory() {
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
                System.out.println("Upload directory created: " + uploadPath.toAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("Could not create upload directory: " + e.getMessage());
        }
    }

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> registerOwner(
            @RequestParam("ownerName") String ownerName,
            @RequestParam("salonName") String salonName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("location") String location,
            @RequestParam("businessId") String businessId,
            @RequestParam("license") MultipartFile licenseFile) {

        try {
            // Validate file
            if (licenseFile.isEmpty()) {
                return ResponseEntity.badRequest().body("License file is required");
            }

            // Create unique filename
            String fileName = System.currentTimeMillis() + "_" + licenseFile.getOriginalFilename();
            String filePath = UPLOAD_DIR + fileName;

            // Save file
            Path path = Paths.get(filePath);
            Files.copy(licenseFile.getInputStream(), path);

            // Save owner details with all fields
            SalonOwner owner = SalonOwner.builder()
                    .ownerName(ownerName)
                    .salonName(salonName)
                    .email(email)
                    .password(password) // Make sure to hash this in your service
                    .phoneNumber(phoneNumber)
                    .location(location)
                    .businessId(businessId)
                    .licenseImagePath(filePath)
                    .status(ApprovalStatus.PENDING_APPROVAL)
                    .build();

            SalonOwner savedOwner = salonOwnerService.registerOwner(owner);

            return ResponseEntity.ok("Registered Successfully! Owner ID: " + savedOwner.getOwnerId());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    // ✅ Upload license separately
    @PostMapping("/upload-license/{ownerId}")
    public ResponseEntity<?> uploadLicense(
            @PathVariable Long ownerId,
            @RequestParam("file") MultipartFile file) {

        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("File is required");
            }

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String filePath = UPLOAD_DIR + fileName;

            Path path = Paths.get(filePath);
            Files.copy(file.getInputStream(), path);

            salonOwnerService.updateLicense(ownerId, filePath);

            return ResponseEntity.ok("License uploaded successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    // ✅ Owner Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            SalonOwner owner = salonOwnerService.login(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(owner);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




    // ✅ View Owner Profile
    @GetMapping("/{ownerId}/profile")
    public ResponseEntity<?> getProfile(@PathVariable Long ownerId) {
        try {
            return ResponseEntity.ok(salonOwnerService.getOwnerProfile(ownerId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}