package com.smriti.hmac.security.hmac;

import com.smriti.hmac.service.impl.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import static com.smriti.hmac.constants.HMACConstant.*;
import static com.smriti.hmac.utils.HMACKeyGenerator.generateNonce;

@Component
public class HMACUtils {

    public String generateHMACHash(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        String username  = userPrincipal.getUsername();
        String apiKey = userPrincipal.getApiKey();
        String apiSecret = userPrincipal.getApiSecret();
        final String nonce = generateNonce();

        final HMACBuilder signatureBuilder = new HMACBuilder()
                .algorithm(HMAC_ALGORITHM)
                .username(username)
                .apiKey(apiKey)
                .apiSecret(apiSecret)
                .nonce(nonce);

        final String signature = signatureBuilder.buildAsBase64String();

        String hash = HMAC_ALGORITHM +
                SPACE +
                username +
                COLON +
                apiKey +
                COLON +
                nonce +
                COLON +
                signature;

        return hash;
    }

}
