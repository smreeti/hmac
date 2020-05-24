package com.smriti.hmac.service;

import com.smriti.hmac.dto.request.LoginRequestDTO;

/**
 * @author smriti on 20/04/20
 */
public interface LoginService {
    String login(LoginRequestDTO loginRequestDTO);
}
