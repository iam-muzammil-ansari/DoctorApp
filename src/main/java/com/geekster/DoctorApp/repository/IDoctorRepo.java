package com.geekster.DoctorApp.repository;

import com.geekster.DoctorApp.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoctorRepo extends JpaRepository<Doctor,Long> {
}
