package com.smriti.hmac.dto.response;

import lombok.*;

import java.io.Serializable;

/**
 * @author smriti on 23/05/20
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginErrorResponseDTO implements Serializable {

    private int status;

    private String errorMessage;

    private String token;
}
