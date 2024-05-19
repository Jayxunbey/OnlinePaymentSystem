package uz.pdp.online.onlinepayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OnlinePaymentSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlinePaymentSystemApplication.class, args);
    }

}
