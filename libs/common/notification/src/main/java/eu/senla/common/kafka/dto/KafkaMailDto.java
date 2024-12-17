package eu.senla.common.kafka.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class KafkaMailDto {

    private MailType mailType;
    private String recipient;
    private String subject;
    private String mailBody;

}
