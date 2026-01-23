package fr.antennes.mbds.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/accounts")
@RestController
public class AccountController {

    private final AccountRepository accountRepository;
    private final CustomerClient customerClient;

    @Autowired
    public AccountController (AccountRepository accountRepository, CustomerClient customerClient)
    {
        this.accountRepository = accountRepository;
        this.customerClient = customerClient;
    }

    @GetMapping
    public List<Account> getAccounts()
    {
        List<Account> accountList = accountRepository.findAll();
        accountList.forEach(account -> {
            account.setCustomer(customerClient.getCustomerById(account.getCustomerId()));
        });

        return accountList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable String id)
    {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent())
        {
            Account account = accountOptional.get();
            account.setCustomer(customerClient.getCustomerById(account.getCustomerId()));
            return ResponseEntity.ok(account);
        }
        return ResponseEntity.notFound().build();
    }
}
