package com.mulecode.jwtserver.password;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public class BCryptPasswordEncoder implements PasswordEncoder {

    private static final Logger LOGGER = LoggerFactory.getLogger(BCryptPasswordEncoder.class);

    private static final Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");

    @Override
    public String encode(String rawPassword) {

        return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {

        if (encodedPassword == null || encodedPassword.length() == 0) {
            LOGGER.warn("Empty encoded password");
            return false;
        }

        if (!BCRYPT_PATTERN.matcher(encodedPassword).matches()) {
            LOGGER.warn("Encoded password does not look like BCrypt");
            return false;
        }

        return BCrypt.checkpw(rawPassword, encodedPassword);
    }
}
