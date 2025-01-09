package eu.senla.notificationservice.handler.impl;

import eu.senla.common.kafka.dto.KafkaMailDto;
import eu.senla.common.kafka.dto.MailType;
import eu.senla.notificationservice.handler.MailTypeHandler;
import eu.senla.notificationservice.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class MailTypeHandlerImpl implements MailTypeHandler {

    private final EmailService emailService;

    @Override
    public void handle(KafkaMailDto message) {
        try {
            String htmlContent = String.format(
                    "<html>" +
                            "  <body style='font-family: Arial, sans-serif; font-size: 16px; line-height: 1.5; text-align: center;'>" +
                            "    <p>%s</p>" +
                            "    <div style='margin-top: 20px;'>" +
                            "      <span style='display: inline-block; padding: 20px 40px; color: #007BFF; font-size: 24px; font-weight: bold; border: 2px solid #007BFF; border-radius: 8px;'>" +
                            "        PLAHCTOH" +
                            "      </span>" +
                            "    </div>" +
                            "  </body>" +
                            "</html>",
                    message.getMailBody()
            );

            emailService.sendEmail(message.getRecipient(), message.getSubject(), htmlContent);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public abstract MailType getMailType();
}
