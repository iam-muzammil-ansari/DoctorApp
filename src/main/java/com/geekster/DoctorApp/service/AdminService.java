package com.geekster.DoctorApp.service;

import com.geekster.DoctorApp.model.Admin;
import com.geekster.DoctorApp.model.AuthenticationToken;
import com.geekster.DoctorApp.model.dataToObject.SignInInput;
import com.geekster.DoctorApp.model.dataToObject.SignUpOutput;
import com.geekster.DoctorApp.repository.IAdminRepo;
import com.geekster.DoctorApp.repository.IAuthTokenRepo;
import com.geekster.DoctorApp.service.utility.emailUtility.EmailHandler;
import com.geekster.DoctorApp.service.utility.hashingUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    IAdminRepo iAdminRepo;

    @Autowired
    IAuthTokenRepo iAuthTokenRepo;

    public SignUpOutput signUpAdmin(Admin admin) {

        boolean signUpStatus = true;
        String signUpStatusMessage = null;

        String newEmail = admin.getAdminEmail();

        if (newEmail == null || !newEmail.toLowerCase().endsWith("@hospitaladmin.com")) {
            signUpStatusMessage = "Invalid email format. Email must end with @hospitaladmin.com";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus, signUpStatusMessage);
        }

        // Check if this admin email already exists
        Admin existingAdmin = iAdminRepo.findFirstByAdminEmail(newEmail);

        if (existingAdmin != null) {
            signUpStatusMessage = "Email already registered!!!";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus, signUpStatusMessage);
        }

        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(admin.getAdminPassword());

            // Save the admin with the new encrypted password
            admin.setAdminPassword(encryptedPassword);
            iAdminRepo.save(admin);

            return new SignUpOutput(signUpStatus, "Admin registered successfully!!!");
        } catch (Exception e) {
            signUpStatusMessage = "Internal error occurred during sign up";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus, signUpStatusMessage);
        }
    }


    public String signInAdmin(SignInInput signInInput) {
        String signInStatusMessage = null;

        String signInEmail = signInInput.getEmail();

        if(signInEmail == null)
        {
            signInStatusMessage = "Invalid email";
            return signInStatusMessage;


        }

        //check if this patient email already exists ??
        Admin existingAdmin = iAdminRepo.findFirstByAdminEmail(signInEmail);

        if(existingAdmin == null)
        {
            signInStatusMessage = "Email not registered!!!";
            return signInStatusMessage;

        }

        //match passwords :

        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(signInInput.getPassword());
            if(existingAdmin.getAdminPassword().equals(encryptedPassword))
            {
                //session should be created since password matched and user id is valid
                AuthenticationToken authToken  = new AuthenticationToken(existingAdmin);
                iAuthTokenRepo.save(authToken);

                EmailHandler.sendEmail(signInEmail,"email testing",authToken.getTokenValue());
                return "Token sent to your email";
            }
            else {
                signInStatusMessage = "Invalid credentials!!!";
                return signInStatusMessage;
            }
        }
        catch(Exception e)
        {
            signInStatusMessage = "Internal error occurred during sign in";
            return signInStatusMessage;
        }
    }

    public String signOutAdmin(String email) {
        Admin admin = iAdminRepo.findFirstByAdminEmail(email);
        iAuthTokenRepo.delete(iAuthTokenRepo.findFirstByAdmin(admin));
        return "admin Signed out successfully";
    }
}
