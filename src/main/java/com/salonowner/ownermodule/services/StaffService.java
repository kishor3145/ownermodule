package com.salonowner.ownermodule.services;


import com.salonowner.ownermodule.Entity.Staff;

import java.util.List;

public interface StaffService {

    List<Staff> getAllStaff();

    Staff getStaffById(Long id);

    Staff createStaff(Staff staff);

    Staff updateStaff(Long id, Staff staff);

    void deleteStaff(Long id);

    List<Staff> searchByName(String name);
}
