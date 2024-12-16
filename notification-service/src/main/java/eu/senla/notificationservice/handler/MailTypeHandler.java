package eu.senla.notificationservice.handler;

import eu.senla.common.kafka.dto.KafkaMailDto;
import eu.senla.common.kafka.dto.MailType;

public interface MailTypeHandler {
    void handle(KafkaMailDto message);
    MailType getMailType();
}
