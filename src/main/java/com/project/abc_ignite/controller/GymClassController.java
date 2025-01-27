package com.project.abc_ignite.controller;

import com.project.abc_ignite.exception.CustomException;
import com.project.abc_ignite.exception.ErrorResponse;
import com.project.abc_ignite.model.GymClass;
import com.project.abc_ignite.service.GymClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class GymClassController {

    private final GymClassService gymClassService;

    public GymClassController(GymClassService gymClassService) {
        this.gymClassService = gymClassService;
    }


    @PostMapping
    public ResponseEntity<?> createClass(@RequestBody GymClass gymClassDetails) {
        try {
            return ResponseEntity.ok(gymClassService.createGymClass(gymClassDetails));
        } catch (CustomException ex) {
            ErrorResponse errorResponse = new ErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST.value(),
                    ex.getErrorCode().getCode(),
                    ex.getMessage(),
                    "/api/classes"
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<GymClass>> getAllClasses() {
        return ResponseEntity.ok(gymClassService.getAllGymClasses());
    }
}
