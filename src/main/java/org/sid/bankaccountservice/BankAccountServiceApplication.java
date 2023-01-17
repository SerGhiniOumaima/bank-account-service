package org.sid.bankaccountservice;

import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.enums.AccountType;
import org.sid.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class BankAccountServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(BankAccountServiceApplication.class, args);
    }
    @Bean //@bean permet de créer cet objet au démarrage et la méthode start va s'exécuter et va créer un objet de type
    //commandLineRunner qui va retourner une fct
    CommandLineRunner start( BankAccountRepository bankAccountRepository){

        return args -> {
            for(int i=1;i<10;i++)
            {
                BankAccount bankAccount=BankAccount.builder()
                        .id(UUID.randomUUID().toString())
                        .type(Math.random()>0.5? AccountType.CURRENT_ACCOUNT:AccountType.SAVING_ACCOUNT)
                        .createdAt(new Date())
                        .balance(9000+Math.random()*12000)
                        .currency("MAD")
                        .build();
                bankAccountRepository.save(bankAccount);

            }
        };
    }

}
