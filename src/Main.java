public class Main {

    public static void main(String[] args) {

        final String PASSWORD = "password123";

        PasswordHash passwordHash = new PasswordHashImpl();
        String salt1 = passwordHash.generateSalt();
        String salt2 = passwordHash.generateSalt();

        String hashedPassword = passwordHash.hashPassword(PASSWORD, salt1);
        String hashedPassword2 = passwordHash.hashPassword(PASSWORD, salt2);

        System.out.println("ExampleSalt: " + salt1);
        System.out.println();
        System.out.println("hashed Password: " + hashedPassword);
        System.out.println("second hashedPassword: " + hashedPassword2);
    }
}