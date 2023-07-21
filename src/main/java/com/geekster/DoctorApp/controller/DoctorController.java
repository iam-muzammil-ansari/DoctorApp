package com.geekster.DoctorApp.controller;

import com.geekster.DoctorApp.model.Doctor;
import com.geekster.DoctorApp.service.AuthenticationAdminService;
import com.geekster.DoctorApp.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    AuthenticationAdminService authenticationAdminService;

    @PostMapping("addDoctor")
    public String addDoctor(String email, String authToken, @RequestBody Doctor doc) {
        if (authenticationAdminService.authenticateAdmin(email,authToken)) {
            doctorService.addDoctor(doc);
            return "Doctor is added successfully";
        }else {
            return "Only admins can add Doctors";
        }
    }

    @GetMapping("doctors")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }
}
