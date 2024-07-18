package uz.pdp.online.onlinepayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableScheduling
@EnableWebSecurity
@EnableAsync
public class OnlinePaymentSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlinePaymentSystemApplication.class, args);
    }

}
