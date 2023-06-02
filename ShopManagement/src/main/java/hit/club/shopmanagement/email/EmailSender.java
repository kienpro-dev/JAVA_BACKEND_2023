package hit.club.shopmanagement.email;

import javax.mail.MessagingException;

public interface EmailSender {
    String sentEmail(String to, String email, String message) throws MessagingException;
}
