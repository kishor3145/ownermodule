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

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public Staff getStaffById(Long id) {
        return staffRepository.findById(id).orElse(null);
    }

    public Staff createStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public Staff updateStaff(Long id, Staff staff) {
        Staff existing = staffRepository.findById(id).orElse(null);
        if (existing != null) {
            staff.setId(id);
            return staffRepository.save(staff);
        }
        return null;
    }

    public void deleteStaff(Long id) {
        staffRepository.deleteById(id);
    }

    public List<Staff> searchByName(String name) {
        return staffRepository.findByNameContainingIgnoreCase(name);
    }
}
