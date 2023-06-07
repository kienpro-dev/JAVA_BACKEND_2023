package hit.club.shopmanagement.email;

import javax.mail.MessagingException;

public interface EmailSender {
    String sentPassword(String to, String message) throws MessagingException;

    void sentEmailBirthday(String to, String message) throws MessagingException;
}
