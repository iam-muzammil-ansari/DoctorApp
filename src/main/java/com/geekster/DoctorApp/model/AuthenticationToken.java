package com.geekster.DoctorApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;
    private  String tokenValue;
    private LocalDateTime tokenCreationDateTime;


    //mapping
    @OneToOne
    @JoinColumn(name = "fk_patient_id")
    Patient patient;

    @OneToOne
    @JoinColumn(name = "fk_admin_id")
    Admin admin;

    //create a parameterized constructor which takes patient as an argument
    public AuthenticationToken(Patient patient)
    {
        this.patient = patient;
        this.tokenValue = UUID.randomUUID().toString();
        this.tokenCreationDateTime = LocalDateTime.now();
    }
    public AuthenticationToken(Admin admin)
    {
        this.admin = admin;
        this.tokenValue = UUID.randomUUID().toString();
        this.tokenCreationDateTime = LocalDateTime.now();
    }
}
