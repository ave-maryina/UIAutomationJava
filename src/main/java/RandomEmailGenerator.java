import java.security.SecureRandom;

public class RandomEmailGenerator {
    private static final SecureRandom random = new SecureRandom();
    private static final String ALPHANUMERIC = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final String[] POPULAR_DOMAINS = {
            "gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "proton.me",
            "icloud.com", "mail.com", "aol.com", "zoho.com", "yandex.ru"
    };

    public static String generateEmail() {
        StringBuilder localPart = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {
            char randomChar = ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length()));
            localPart.append(randomChar);
        }

        String domain = POPULAR_DOMAINS[random.nextInt(POPULAR_DOMAINS.length)];
        return localPart.toString() + "@" + domain;
    }
}
