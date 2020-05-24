package com.smriti.hmac.resource;

import com.smriti.hmac.dto.request.LoginRequestDTO;
import com.smriti.hmac.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smriti on 20/04/20
 */
@RestController
@RequestMapping("/api")
@Api("This is login resource")
public class LoginResource {

    private final LoginService loginService;

    public LoginResource(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    @ApiOperation("Login User")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, loginService.login(loginRequestDTO));
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
