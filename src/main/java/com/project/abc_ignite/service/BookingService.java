package com.project.abc_ignite.service;

import com.project.abc_ignite.model.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    public Booking createBooking(Booking booking);
    public List<Booking> searchBookings(String memberName, LocalDate startDate, LocalDate endDate);
}
