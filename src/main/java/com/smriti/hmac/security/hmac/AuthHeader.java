package com.smriti.hmac.security.hmac;

import lombok.Getter;

@Getter
public class AuthHeader {

    private final String algorithm;

    private final String username;

    private final String apiKey;

    private final String nonce;

    private final byte[] digest;

    public AuthHeader(String algorithm,
                      String username,
                      String apiKey,
                      String nonce,
                      byte[] digest) {
        this.algorithm = algorithm;
        this.username = username;
        this.apiKey = apiKey;
        this.nonce = nonce;
        this.digest = digest;
    }
}
