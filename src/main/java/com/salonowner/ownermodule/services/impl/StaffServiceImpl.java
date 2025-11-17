package com.salonowner.ownermodule.services.impl;

import com.salonowner.ownermodule.Entity.Staff;
import com.salonowner.ownermodule.repository.StaffRepository;
import com.salonowner.ownermodule.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    // ✅ Get all staff
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    // ✅ Get staff by owner (needed for ManageStaff page)
    public List<Staff> getStaffByOwner(Long ownerId) {
        return staffRepository.findByOwnerId(ownerId);
    }

    // ✅ Get staff by ID
    public Staff getStaffById(Long id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found"));
    }

    // ✅ Create new staff
    public Staff createStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    // ✅ Update staff safely
    public Staff updateStaff(Long id, Staff updated) {
        Staff existing = getStaffById(id);

        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setPhone(updated.getPhone());
        existing.setRole(updated.getRole());
        existing.setBranch(updated.getBranch());
        existing.setShiftStart(updated.getShiftStart());
        existing.setShiftEnd(updated.getShiftEnd());
        existing.setLeaves(updated.getLeaves());
        existing.setJoiningDate(updated.getJoiningDate());

        return staffRepository.save(existing);
    }

    // ✅ Delete staff
    public void deleteStaff(Long id) {
        staffRepository.deleteById(id);
    }

    // ✅ Search by name
    public List<Staff> searchByName(String name) {
        return staffRepository.findByNameContainingIgnoreCase(name);
    }

}
