package eu.senla.notificationservice.handler.impl;

import eu.senla.common.kafka.dto.KafkaMailDto;
import eu.senla.common.kafka.dto.MailType;
import eu.senla.notificationservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingMailHandler extends MailTypeHandlerImpl {

    @Autowired
    public BookingMailHandler(EmailService emailService) {
        super(emailService);
    }

    @Override
    public void handle(KafkaMailDto message) {
        super.handle(message);
    }

    @Override
    public MailType getMailType() {
        return MailType.BOOKING_MAIL;
    }
}
