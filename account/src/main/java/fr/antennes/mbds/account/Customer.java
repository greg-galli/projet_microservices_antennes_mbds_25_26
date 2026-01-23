package fr.antennes.mbds.account;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class Customer { // Une sorte de DTO
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
