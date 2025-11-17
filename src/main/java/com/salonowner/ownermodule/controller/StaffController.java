package com.salonowner.ownermodule.controller;

import com.salonowner.ownermodule.Entity.Staff;
import com.salonowner.ownermodule.services.impl.StaffServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/staff")
@CrossOrigin(origins = "*")
public class StaffController {

    @Autowired
    private StaffServiceImpl staffService;

    // ✅ Add Staff (matches React URL: /api/staff/add)
    @PostMapping(value = "/add", consumes = "multipart/form-data")
    public Staff createStaff(
            @RequestParam("staffName") String staffName,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("role") String role,
            @RequestParam("branch") String branch,
            @RequestParam("workingHours") String workingHours,
            @RequestParam("shiftStart") String shiftStart,
            @RequestParam("shiftEnd") String shiftEnd,
            @RequestParam("leaves") String leaves,
            @RequestParam("joiningDate") String joiningDate,
            @RequestParam("ownerId") Long ownerId,
            @RequestParam("image") MultipartFile image
    ) throws IOException {

        String uploadFolder = System.getProperty("user.dir") + "/uploads/staff/";
        File folder = new File(uploadFolder);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        String filePath = uploadFolder + image.getOriginalFilename();
        image.transferTo(new File(filePath));
        String imageUrl = "http://localhost:7071/uploads/staff/" + image.getOriginalFilename();


        Staff staff = new Staff();
        staff.setName(staffName);
        staff.setEmail(email);
        staff.setPhone(phone);
        staff.setRole(role);
        staff.setWorkingHours(workingHours);
        staff.setBranch(branch);
        staff.setShiftStart(shiftStart);
        staff.setShiftEnd(shiftEnd);
        staff.setLeaves(String.valueOf(Integer.parseInt(leaves)));
        staff.setJoiningDate(LocalDate.parse(joiningDate));
        staff.setOwnerId(ownerId);
        staff.setImageUrl(filePath);

        return staffService.createStaff(staff);
    }



    @GetMapping("/owner/{ownerId}")
    public List<Staff> getStaffByOwner(@PathVariable Long ownerId) {
        return staffService.getStaffByOwner(ownerId);
    }

    // ✅ Get single staff by ID
    @GetMapping("/{id}")
    public Staff getStaff(@PathVariable Long id) {
        return staffService.getStaffById(id);
    }

    // ✅ Update staff
    @PutMapping("/{id}")
    public Staff updateStaff(@PathVariable Long id, @RequestBody Staff staff) {
        return staffService.updateStaff(id, staff);
    }

    // ✅ Delete staff
    @DeleteMapping("/{id}")
    public String deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
        return "Staff deleted successfully!";
    }

    // ✅ Search by name
    @GetMapping("/search")
    public List<Staff> searchStaff(@RequestParam String name) {

        return staffService.searchByName(name);
    }

    @GetMapping
    public List<Staff> getAllStaff() {
        return staffService.getAllStaff();
    }

}
