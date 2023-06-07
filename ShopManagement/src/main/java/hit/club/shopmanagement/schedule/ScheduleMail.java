package hit.club.shopmanagement.schedule;

import hit.club.shopmanagement.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public class ScheduleMail {
    @Autowired
    private EmailService emailService;

    @Scheduled(fixedRate = 1)
    void setEmailSchedule(String to, String email, String message) throws MessagingException {
        emailService.sentEmail(to, email, message);
    }
}
