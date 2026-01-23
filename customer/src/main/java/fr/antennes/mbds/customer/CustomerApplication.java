package fr.antennes.mbds.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            List<Customer> customerList = List.of(
                    Customer.builder()
                            .firstName("Akim")
                            .lastName("Doumbia")
                            .email("akim.doumbia@etu.univ-cotedazur.fr")
                            .build(),
                    Customer.builder()
                            .firstName("Sachy")
                            .lastName("Barreau")
                            .email("sachy.barreau@etu.univ-cotedazur.fr")
                            .build(),
                    Customer.builder()
                            .firstName("Charles")
                            .lastName("Nguessan")
                            .email("charles.nguessan@etu.univ-cotedazur.fr")
                            .build()
                    );
            customerRepository.saveAll(customerList);
        };
    }
}
