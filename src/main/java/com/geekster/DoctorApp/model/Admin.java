package com.geekster.DoctorApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;
    private String adminName;
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@hospitaladmin\\.com$\n", message = "Your are not Admin")
    private String adminEmail;
    private String adminPassword;

}
