package com.mulecode.jwtserver.utils;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BasicAuthenticationService {

    private static final String PREFIX = "Basic";
    private static final String DELIMITER = ":";
    private static final String EMPTY_SPACE = " ";
    private String userName;
    private String password;
    private String encoded;


    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEncoded() {
        return encoded;
    }

    public void encode(String userName, String password) {

        if (StringUtils.isBlank(userName) && StringUtils.isBlank(password)) {

            throw new IllegalArgumentException("encode error - userName and password must not be null nor empty.");
        }

        var sb = new StringBuilder();
        sb.append(PREFIX);
        sb.append(EMPTY_SPACE);
        sb.append(userName);
        sb.append(DELIMITER);
        sb.append(password);

        var encodedBytes = Base64.getEncoder().encode(sb.toString().getBytes());

        this.encoded = bytesToUTF8String(encodedBytes);
    }

    public void decode(String authentication) {

        if (StringUtils.isBlank(authentication)) {

            throw new IllegalArgumentException("decode error - authentication must not be null nor empty.");
        }

        if (!authentication.toLowerCase().startsWith(PREFIX.toLowerCase())) {

            throw new IllegalArgumentException("decode error - authentication should starts with 'Basic '");
        }

        var base64Credentials = authentication.substring(PREFIX.length()).trim();

        var decodedBytes = Base64.getDecoder().decode(base64Credentials);

        var credentials = bytesToUTF8String(decodedBytes);

        String[] values = credentials.split(DELIMITER, 2);

        userName = values[0];
        password = values[1];
    }

    private String bytesToUTF8String(byte[] decodedBytes) {

        return new String(decodedBytes, StandardCharsets.UTF_8);
    }

}
