package com.project.abc_ignite.service;

import com.project.abc_ignite.model.GymClass;

import java.util.List;

public interface GymClassService {
    public GymClass createGymClass(GymClass classDetails);
    public List<GymClass> getAllGymClasses();
}
