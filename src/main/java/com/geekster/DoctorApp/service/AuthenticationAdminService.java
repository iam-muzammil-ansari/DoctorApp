package com.geekster.DoctorApp.service;

import com.geekster.DoctorApp.model.AuthenticationToken;
import com.geekster.DoctorApp.repository.IAuthTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationAdminService {
    @Autowired
    IAuthTokenRepo authTokenRepo;

    public boolean authenticateAdmin(String email, String authTokenValue)
    {
        AuthenticationToken authToken = authTokenRepo.findFirstByTokenValue(authTokenValue);

        if(authToken == null)
        {
            return false;
        }


        String tokenConnectedEmail = authToken.getAdmin().getAdminEmail();
        return tokenConnectedEmail.equals(email);
    }
}
