package com.salonowner.ownermodule.services;


import com.salonowner.ownermodule.Entity.BookingRecord;

import java.util.List;

public interface BookingRecordService {
    BookingRecord createBooking(BookingRecord booking);
    BookingRecord updateBooking(Long id, BookingRecord booking);
    void deleteBooking(Long id);
    BookingRecord getBookingById(Long id);
    List<BookingRecord> getAllBookings();
    List<BookingRecord> getBookingsByUser(Long userId);
}
