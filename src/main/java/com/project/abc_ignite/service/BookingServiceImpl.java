package com.project.abc_ignite.service;

import com.project.abc_ignite.exception.CustomException;
import com.project.abc_ignite.exception.ErrorCode;
import com.project.abc_ignite.model.Booking;
import com.project.abc_ignite.model.GymClass;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookingServiceImpl implements BookingService{

    private final List<Booking> bookings = new ArrayList<>();
    private final AtomicLong bookingIdGenerator = new AtomicLong(1);
    private final GymClassService gymClassService;

    public BookingServiceImpl(GymClassService gymClassService) {
        this.gymClassService = gymClassService;
    }

    @Override
    public Booking createBooking(Booking booking) {
        validateBooking(booking);

        // Fetch class details
        GymClass gymClass = gymClassService.getAllGymClasses().stream()
                .filter(c -> c.getId().equals(booking.getClassId()))
                .findFirst()
                .orElseThrow(() -> new CustomException(ErrorCode.CLASS_NOT_FOUND, "Class not found with ID: " + booking.getClassId()));

        // Populate class details in the booking
        booking.setId(bookingIdGenerator.getAndIncrement());
        booking.setClassName(gymClass.getName());
        booking.setClassStartTime(gymClass.getStartTime());

        bookings.add(booking);
        return booking;
    }

    private void validateBooking(Booking booking) {
        if (booking.getParticipationDate().isBefore(LocalDate.now())) {
            throw new CustomException(ErrorCode.INVALID_DATE, "Participation date must be in the future");
        }

        GymClass gymClass = gymClassService.getAllGymClasses().stream()
                .filter(c -> c.getId().equals(booking.getClassId()))
                .findFirst()
                .orElseThrow(() -> new CustomException(ErrorCode.CLASS_NOT_FOUND, "Class not found with ID: " + booking.getClassId()));

        // Check if participation date is within the class's start and end dates
        if (booking.getParticipationDate().isBefore(gymClass.getStartDate()) ||
                booking.getParticipationDate().isAfter(gymClass.getEndDate())) {
            throw new CustomException(ErrorCode.INVALID_DATE, "Participation date must be within the class's start and end dates");
        }

        long bookingsCount = bookings.stream()
                .filter(b -> b.getClassId().equals(booking.getClassId()) &&
                        b.getParticipationDate().equals(booking.getParticipationDate()))
                .count();

        if (bookingsCount >= gymClass.getCapacity()) {
            throw new CustomException(ErrorCode.CAPACITY_EXCEEDED, "Class capacity exceeded");
        }
    }

    @Override
    public List<Booking> searchBookings(String memberName, LocalDate startDate, LocalDate endDate) {
        return bookings.stream()
                .filter(b -> (memberName == null || b.getMemberName().equals(memberName)) &&
                        (startDate == null || !b.getParticipationDate().isBefore(startDate)) &&
                        (endDate == null || !b.getParticipationDate().isAfter(endDate)))
                .toList();
    }
}
