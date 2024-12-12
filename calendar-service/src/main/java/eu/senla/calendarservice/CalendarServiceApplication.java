package eu.senla.calendarservice;

import eu.senla.httpconfiguration.core.configuration.HttpConfiguration;
import eu.senla.httpconfiguration.security.configuration.SecurityHttpConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({SecurityHttpConfiguration.class, HttpConfiguration.class})
public class CalendarServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalendarServiceApplication.class, args);
    }

}
