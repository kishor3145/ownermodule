package com.salonowner.ownermodule.repository;

import com.salonowner.ownermodule.Entity.BookingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRecordRepository extends JpaRepository<BookingRecord, Long> {
    List<BookingRecord> findByUserId(Long userId);
    List<BookingRecord> findBySalonId(Long salonId);
    List<BookingRecord> findByStatus(String status);
}
