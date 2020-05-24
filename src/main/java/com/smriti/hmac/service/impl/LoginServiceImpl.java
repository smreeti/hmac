package com.smriti.hmac.service.impl;

import com.smriti.hmac.dto.request.LoginRequestDTO;
import com.smriti.hmac.security.hmac.HMACUtils;
import com.smriti.hmac.service.LoginService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author smriti on 20/04/20
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;

    private final HMACUtils hmacUtils;

    public LoginServiceImpl(AuthenticationManager authenticationManager,
                            HMACUtils hmacUtils) {
        this.authenticationManager = authenticationManager;
        this.hmacUtils = hmacUtils;
    }

    @Override
    public String login(LoginRequestDTO loginRequestDTO) {

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
        } catch (Exception e) {
            throw new RuntimeException("Invalid Password");
        }

        return hmacUtils.generateHMACHash(authentication);
    }
}
