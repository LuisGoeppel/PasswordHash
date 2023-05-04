
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class PasswordHashImpl implements PasswordHash{

    private static final String PEPPER = "99257cbd97913ad3";
    private static final int N_ITERATIONS = 10000;
    private static final int SALT_LENGTH = 16;
    private static final int KEY_LENGTH = 256;

    @Override
    public String generateSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return getAsString(salt);
    }

    @Override
    public String hashPassword(String password, String salt) {

        String pepperedPassword = PEPPER + password;
        byte[] saltAsBytes = Base64.getDecoder().decode(salt);
        KeySpec spec = new PBEKeySpec(pepperedPassword.toCharArray(), saltAsBytes, N_ITERATIONS, KEY_LENGTH);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            byte[] hashedPassword = factory.generateSecret(spec).getEncoded();
            return getAsString(hashedPassword);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("There was an Error while hashing the password");
        }
    }

    public String getAsString(byte[] input) {
        return Base64.getEncoder().encodeToString(input);
    }
}
