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

    @Scheduled(cron = "30 18 * * *")
    void setEmailSchedule() throws MessagingException {
        emailService.sentEmailBirthday("hoangphuong270703@gmail.com", "Bop co dan phong");
    }
}
