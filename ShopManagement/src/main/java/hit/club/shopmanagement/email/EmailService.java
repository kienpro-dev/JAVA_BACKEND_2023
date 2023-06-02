package hit.club.shopmanagement.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

public class EmailService implements EmailSender {
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public String sentEmail(String to, String email, String message) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, StandardCharsets.UTF_8.name());
        messageHelper.setSubject(message);
        messageHelper.setFrom(email);
        messageHelper.setText(email, true);
        messageHelper.setTo(to);
        javaMailSender.send(mimeMessage);
        return "Sent password successfully!";
    }
}
