package cinema.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class HashUtil {
    private static Logger logger = Logger.getLogger(HashUtil.class);

    public byte[] getSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }

    public String hashPassword(String password, byte[] salt) {
        StringBuilder hashPassword = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(salt);
            byte[] digest = messageDigest.digest(password.getBytes());

            for (byte b: digest) {
                hashPassword.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            logger.error("could not create hash");
        }

        return hashPassword.toString();
    }
}
