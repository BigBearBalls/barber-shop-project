package eu.senla.booking.service.kafka;

import eu.senla.common.kafka.dto.KafkaMailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingKafkaProducer {
    private final KafkaTemplate<String, KafkaMailDto> kafkaTemplate;

    public void sendMailSendEvent(String topic, KafkaMailDto message) {
        kafkaTemplate.send(topic, message);
    }
}
