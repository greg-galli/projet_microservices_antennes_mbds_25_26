package fr.antennes.mbds.account;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class AccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(AccountRepository accountRepository, CustomerClient customerClient) {
        return args -> {
            customerClient.getCustomers().forEach( customer -> {
                Account accountInstance = Account.builder()
                        .balance(Math.random()*1000)
                        .currencyType(CurrencyType.USD)
                        .customerId(customer.getId())
                        .build();
                accountRepository.save(accountInstance);
            });
        };
    }
}
