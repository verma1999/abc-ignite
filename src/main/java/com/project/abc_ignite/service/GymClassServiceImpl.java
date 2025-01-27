package com.project.abc_ignite.service;

import com.project.abc_ignite.exception.CustomException;
import com.project.abc_ignite.exception.ErrorCode;
import com.project.abc_ignite.model.GymClass;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GymClassServiceImpl implements GymClassService {

    private final List<GymClass> gymClasses = new ArrayList<>();
    private final AtomicLong classIdGenerator = new AtomicLong(1);

    @Override
    public GymClass createGymClass(GymClass gymClassDetails) {
        validateGymClass(gymClassDetails);
        gymClassDetails.setId(classIdGenerator.getAndIncrement());
        gymClasses.add(gymClassDetails);
        return gymClassDetails;
    }
    private void validateGymClass(GymClass gymClassDetails) {
        if (gymClassDetails.getCapacity() < 1) {
            throw new CustomException(ErrorCode.INVALID_CAPACITY, "Capacity must be at least 1");
        }
        if (gymClassDetails.getEndDate().isBefore(LocalDate.now())) {
            throw new CustomException(ErrorCode.INVALID_END_DATE, "End date must be in the future");
        }

        if (gymClassDetails.getEndDate().isBefore(gymClassDetails.getStartDate())) {
            throw new CustomException(ErrorCode.END_DATE_BEFORE_START_DATE, "End date must be after start date");
        }
    }

    @Override
    public List<GymClass> getAllGymClasses() {
        return gymClasses;
    }
}
