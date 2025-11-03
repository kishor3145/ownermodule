package com.salonowner.ownermodule.services.impl;


import com.salonowner.ownermodule.Entity.BookingRecord;
import com.salonowner.ownermodule.repository.BookingRecordRepository;
import com.salonowner.ownermodule.services.BookingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
