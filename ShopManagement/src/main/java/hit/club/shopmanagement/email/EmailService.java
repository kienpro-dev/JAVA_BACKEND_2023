package hit.club.shopmanagement.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService implements EmailSender {
    @Autowired
    private JavaMailSender javaMailSender;

    MimeMessage mimeMessage = javaMailSender.createMimeMessage();

    MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, StandardCharsets.UTF_8.name());

    @Override
    public String sentPassword(String to, String message) throws MessagingException {
        messageHelper.setSubject(message);
        messageHelper.setFrom("tienkiennropro@gmail.com");
        messageHelper.setText(message, true);
        messageHelper.setTo(to);
        javaMailSender.send(mimeMessage);
        return "Sent password successfully!";
    }

    @Override
    public void sentEmailBirthday(String to, String message) throws MessagingException {
        messageHelper.setSubject(message);
        messageHelper.setFrom("tienkiennropro@gmail.com");
        messageHelper.setText(message, true);
        messageHelper.setTo(to);
        javaMailSender.send(mimeMessage);

        /*
        do something better
        */
    }
}
