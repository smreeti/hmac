package com.smriti.hmac.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author smriti on 20/04/20
 */
@Getter
@Setter
public class LoginRequestDTO implements Serializable {

    private String username;

    private String password;
}
