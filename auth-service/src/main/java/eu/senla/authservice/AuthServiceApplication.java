package eu.senla.authservice;

import eu.senla.httpconfiguration.core.configuration.HttpConfiguration;
import eu.senla.httpconfiguration.security.configuration.SecurityHttpConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableFeignClients
@Import({SecurityHttpConfiguration.class, HttpConfiguration.class})
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }
}
