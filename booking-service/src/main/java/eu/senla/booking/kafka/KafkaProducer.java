package eu.senla.booking.kafka;

import eu.senla.common.kafka.dto.KafkaMailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, KafkaMailDto> kafkaTemplate;

    public void sendUserRegistrationEvent(String topic, KafkaMailDto kafkaMailDto) {
        kafkaTemplate.send(topic, kafkaMailDto);
    }
}
