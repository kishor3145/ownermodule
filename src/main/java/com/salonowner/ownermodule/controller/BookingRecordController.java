package com.salonowner.ownermodule.controller;


import com.salonowner.ownermodule.Entity.BookingRecord;
import com.salonowner.ownermodule.services.BookingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingRecordController {

    @Autowired
    private BookingRecordService service;

    @PostMapping
    public BookingRecord createBooking(@RequestBody BookingRecord booking) {
        return service.createBooking(booking);
    }

    @GetMapping
    public List<BookingRecord> getAllBookings() {
        return service.getAllBookings();
    }

    @GetMapping("/{id}")
    public BookingRecord getBookingById(@PathVariable Long id) {
        return service.getBookingById(id);
    }

    @PutMapping("/{id}")
    public BookingRecord updateBooking(@PathVariable Long id, @RequestBody BookingRecord booking) {
        return service.updateBooking(id, booking);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        service.deleteBooking(id);
    }

    @GetMapping("/user/{userId}")
    public List<BookingRecord> getBookingsByUser(@PathVariable Long userId) {
        return service.getBookingsByUser(userId);
    }
}