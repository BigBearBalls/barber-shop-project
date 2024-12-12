package eu.senla.notificationservice.kafka;

import eu.senla.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final EmailService emailService;

    @KafkaListener(topics = "user-registration", groupId = "notification-group")
    public void listen(String message) {
        log.info("Received message!!!!!: " + message);

        try{
            emailService.sendEmail(
                    "evgturin@gmail.com",
                    "Welcome to Our Service",
                    "Thank you for registering!");
        } catch (MailAuthenticationException e) {
            log.error("Failed to send email: {}", e.getMessage());
        }
    }
}

