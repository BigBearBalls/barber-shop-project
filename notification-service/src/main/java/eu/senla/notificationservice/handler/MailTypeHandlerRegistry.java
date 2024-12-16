package eu.senla.notificationservice.handler;

import eu.senla.common.kafka.dto.MailType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MailTypeHandlerRegistry {

    private final Map<MailType, MailTypeHandler> handlers = new HashMap<>();

    public MailTypeHandlerRegistry(List<MailTypeHandler> handlerList) {
        for (MailTypeHandler handler : handlerList) {
            handlers.put(handler.getMailType(), handler);
        }
    }

    public MailTypeHandler getHandler(MailType mailType) {
        return handlers.get(mailType);
    }
}

