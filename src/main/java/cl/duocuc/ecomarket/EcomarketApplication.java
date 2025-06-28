package cl.duocuc.ecomarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class EcomarketApplication {


    public static void main(String[] args) {
        SpringApplication.run(EcomarketApplication.class, args);
    }

}
