package com.geekster.DoctorApp.controller;

import com.geekster.DoctorApp.model.Admin;
import com.geekster.DoctorApp.model.dataToObject.SignInInput;
import com.geekster.DoctorApp.model.dataToObject.SignUpOutput;
import com.geekster.DoctorApp.service.AdminService;
import com.geekster.DoctorApp.service.AuthenticationAdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    AuthenticationAdminService authenticationAdminService;

    @PostMapping("admin/signup")
    public SignUpOutput signUpAdmin(@RequestBody @Valid  Admin admin) {
        return adminService.signUpAdmin(admin);
    }

    @PostMapping("admin/signIn")
    public String signInAdmin(@RequestBody @Valid SignInInput signInInput) {
        return adminService.signInAdmin(signInInput);
    }

    @DeleteMapping("admin/signOut")
    public String signOutAdmin(String email, String token)
    {
        if(authenticationAdminService.authenticateAdmin(email,token)) {
            return adminService.signOutAdmin(email);
        }
        else {
            return "Sign out not allowed for non authenticated user.";
        }

    }

}
