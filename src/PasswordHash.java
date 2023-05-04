public interface PasswordHash {

    /**
     * Generates a 16 bit Salt which can be used for password salting
     * @return the 32bit Salt
     */
    String generateSalt();

    /**
     * Hashes a password using the PBKDF2 - Algorithm. The given password will be
     * concatenated with the given salt to ensure maximum security
     * @param password The password to be hashed
     * @param salt The salt used for hashing the password
     * @return The hashed password
     */
    String hashPassword(String password, String salt);
}
