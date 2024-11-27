package eu.senla.workingdayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WorkingDayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkingDayServiceApplication.class, args);
    }

}
