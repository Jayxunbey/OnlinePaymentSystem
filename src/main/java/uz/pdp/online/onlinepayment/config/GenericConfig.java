package uz.pdp.online.onlinepayment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class GenericConfig {

    @Bean
    public Random random(){
        return new Random();
    }

}
