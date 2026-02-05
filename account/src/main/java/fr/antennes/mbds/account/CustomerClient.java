package fr.antennes.mbds.account;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CUSTOMER")
public interface CustomerClient {

    @GetMapping("/customers/{id}")
    @CircuitBreaker(name = "customerById", fallbackMethod = "getDefaultCustomerById")
    Customer getCustomerById(@PathVariable Long id);

    @GetMapping("/customers")
    @CircuitBreaker(name = "customers", fallbackMethod = "getDefaultCustomers")
    List<Customer> getCustomers();

    default Customer getDefaultCustomerById(Long id, Exception exception) {
        return Customer.builder()
                .id(id)
                .email("Not available")
                .firstName("Not available")
                .lastName("Not available")
                .build();
    }

    default List<Customer> getDefaultCustomers(Exception exception) {
        return List.of();
    }
}
