package com.salonowner.ownermodule.services.impl;


import com.salonowner.ownermodule.Entity.BookingRecord;
import com.salonowner.ownermodule.repository.BookingRecordRepository;
import com.salonowner.ownermodule.services.BookingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookingRecordServiceImpl implements BookingRecordService {

    @Autowired
    private BookingRecordRepository repository;

    @Override
    public BookingRecord createBooking(BookingRecord booking) {
        return repository.save(booking);
    }

    @Override
    public BookingRecord updateBooking(Long id, BookingRecord updatedBooking) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setBookingDate(updatedBooking.getBookingDate());
                    existing.setPrice(updatedBooking.getPrice());
                    existing.setRating(updatedBooking.getRating());
                    existing.setSalonId(updatedBooking.getSalonId());
                    existing.setServiceId(updatedBooking.getServiceId());
                    existing.setStatus(updatedBooking.getStatus());
                    existing.setUserId(updatedBooking.getUserId());
                    return repository.save(existing);
                }).orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    @Override
    public void deleteBooking(Long id) {
        repository.deleteById(id);
    }

    @Override
    public BookingRecord getBookingById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    @Override
    public List<BookingRecord> getAllBookings() {
        return repository.findAll();
    }

    @Override
    public List<BookingRecord> getBookingsByUser(Long userId) {
        return repository.findByUserId(userId);
    }

    public Map<String, Double> getRevenueSummary() {
        LocalDate today = LocalDate.now();

        Double daily = repository.getDailyRevenue(today);
        Double monthly = repository.getMonthlyRevenue(today);
        Double yearly = repository.getYearlyRevenue(today);

        Map<String, Double> result = new HashMap<>();
        result.put("dailyRevenue", daily != null ? daily : 0.0);
        result.put("monthlyRevenue", monthly != null ? monthly : 0.0);
        result.put("yearlyRevenue", yearly != null ? yearly : 0.0);

        return result;
    }
}
