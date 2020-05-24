package com.smriti.hmac.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class HMACConfig {

    @Value("${hmac.uri:/api/login}")
    private String uri;

    @Value("${hmac.header:Authorization}")
    private String header;
}
