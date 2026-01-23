package fr.antennes.mbds.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
import java.util.Optional;

@RequestMapping("/customers")
@RestController
public class CustomerController {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id)
    {
        Optional<Customer> customerOptional = customerRepository.findById(id);
//        if (customerOptional.isPresent())
//            return ResponseEntity.ok(customerOptional.get());
//        return ResponseEntity.notFound().build();
        return customerOptional.orElse(null);
    }

    @GetMapping
    public List<Customer> getCustomers()
    {
        return customerRepository.findAll();
    }
}
