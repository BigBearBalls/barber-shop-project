package eu.senla.notificationservice.kafka;

import eu.senla.common.kafka.dto.KafkaMailDto;
import eu.senla.notificationservice.handler.MailTypeHandlerRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final MailTypeHandlerRegistry handlerRegistry;

    @KafkaListener(topics = "mail-sender-topic", groupId = "notification-group")
    public void listen(KafkaMailDto message) {
        log.info("Received message!!!!! " + message.toString());
        try{
            var handler = handlerRegistry.getHandler(message.getMailType());
            handler.handle(message);
        } catch (MailAuthenticationException e) {
            log.error("Failed to send email: {}", e.getMessage());
        }
    }
}

