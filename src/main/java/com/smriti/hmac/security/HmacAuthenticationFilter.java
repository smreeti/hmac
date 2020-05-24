package com.smriti.hmac.security;

import com.smriti.hmac.security.hmac.AuthHeader;
import com.smriti.hmac.security.hmac.HMACBuilder;
import com.smriti.hmac.service.impl.UserDetailsImpl;
import com.smriti.hmac.service.impl.UserDetailsServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author smriti on 20/04/20
 */
@Component
public class HmacAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER_PATTERN = "^(\\w+) (\\S+):(\\S+):(\\S+):([\\S]+)$";

    private final UserDetailsServiceImpl userDetailsService;

    public HmacAuthenticationFilter(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final AuthHeader authHeader = getAuthHeader(request);

        if (authHeader != null) {

            final HMACBuilder signatureBuilder;

            UserDetailsImpl userDetails = userDetailsService.loadUserByUsername(authHeader.getUsername());

            signatureBuilder = new HMACBuilder()
                    .algorithm(authHeader.getAlgorithm())
                    .username(authHeader.getUsername())
                    .apiKey(authHeader.getApiKey())
                    .nonce(authHeader.getNonce())
                    .apiSecret(userDetails.getApiSecret());

            compareSignature(signatureBuilder, authHeader.getDigest());

            SecurityContextHolder.getContext().setAuthentication(getAuthenticationForCompany(authHeader.getUsername(),
                    authHeader.getUsername()));
        }

        try {
            filterChain.doFilter(request, response);
        } finally {
            SecurityContextHolder.clearContext();
        }

    }

    private AuthHeader getAuthHeader(HttpServletRequest request) {

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null) {
            return null;
        }

        final Matcher authHeaderMatcher = Pattern.compile(AUTHORIZATION_HEADER_PATTERN).matcher(authHeader);
        if (!authHeaderMatcher.matches()) {
            return null;
        }

        return new AuthHeader(authHeaderMatcher.group(1),
                authHeaderMatcher.group(2),
                authHeaderMatcher.group(3),
                authHeaderMatcher.group(4),
                DatatypeConverter.parseBase64Binary(authHeaderMatcher.group(5)));
    }

    public void compareSignature(HMACBuilder signatureBuilder, byte[] digest) {
        if (!signatureBuilder.isHashEquals(digest))
            throw new BadCredentialsException("HMAC Bad Signature");
    }

    private PreAuthenticatedAuthenticationToken getAuthenticationForCompany(String username, String email) {
        return new PreAuthenticatedAuthenticationToken(
                username,
                email,
                null);
    }
}
