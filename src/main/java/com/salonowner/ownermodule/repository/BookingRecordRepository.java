package com.salonowner.ownermodule.repository;

import com.salonowner.ownermodule.Entity.BookingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRecordRepository extends JpaRepository<BookingRecord, Long> {
    List<BookingRecord> findByUserId(Long userId);
    List<BookingRecord> findBySalonId(Long salonId);
    List<BookingRecord> findByStatus(String status);

    @Query("SELECT SUM(b.price) FROM BookingRecord b WHERE b.bookingDate = :today")
    Double getDailyRevenue(LocalDate today);

    @Query("SELECT SUM(b.price) FROM BookingRecord b WHERE MONTH(b.bookingDate) = MONTH(:today) AND YEAR(b.bookingDate) = YEAR(:today)")
    Double getMonthlyRevenue(LocalDate today);

    @Query("SELECT SUM(b.price) FROM BookingRecord b WHERE YEAR(b.bookingDate) = YEAR(:today)")
    Double getYearlyRevenue(LocalDate today);
}
