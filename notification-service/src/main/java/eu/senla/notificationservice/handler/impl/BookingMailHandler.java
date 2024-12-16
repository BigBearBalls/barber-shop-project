package eu.senla.notificationservice.handler.impl;

import eu.senla.common.kafka.dto.KafkaMailDto;
import eu.senla.common.kafka.dto.MailType;
import eu.senla.notificationservice.handler.MailTypeHandler;
import eu.senla.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingMailHandler implements MailTypeHandler {

    private final EmailService emailService;

    @Override
    public void handle(KafkaMailDto message) {
        emailService.sendEmail(message.getRecipient(), message.getSubject(), message.getMailBody());
    }

    @Override
    public MailType getMailType() {
        return MailType.BOOKING_MAIL;
    }
}
