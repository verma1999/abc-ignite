package com.project.abc_ignite.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class GymClass {
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private int duration; // Duration in minutes
    private int capacity;
}
