package com.project.abc_ignite.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class Booking {
    private Long id;
    private String memberName;
    private Long classId;
    private LocalDate participationDate;
    private String className;
    private LocalTime classStartTime;
}
