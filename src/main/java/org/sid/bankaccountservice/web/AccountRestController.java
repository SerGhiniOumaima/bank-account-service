package org.sid.bankaccountservice.web;

import org.sid.bankaccountservice.dtos.BankAccountRequestDTO;
import org.sid.bankaccountservice.dtos.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.repositories.BankAccountRepository;
import org.sid.bankaccountservice.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
//le chemin est localhosst../api/bankAccounts
@RequestMapping("/api")
public class AccountRestController {
    BankAccountRepository bankAccountRepository;
    private AccountService accountService;
    //comment c'estt une injection de dépendance
    public AccountRestController(BankAccountRepository bankAccountRepository)
    {
        this.bankAccountRepository=bankAccountRepository;
    }
    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts()
    {
        return bankAccountRepository.findAll();

    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id)
    {
        return bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Account not found"));

    }
    //méthode pour enregistrer un compte
    @PostMapping("/bankAccounts")
    //requestBody : envoyer le compte à enregistrer dans le body de la req
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO)
    {
        return accountService.addAccount(requestDTO);
    }

    //méthode pour modifier un account
    //les if testent si l user a renvoyé toutes les D ou veut modifier uniquement des champs
    //si n'a pas envoyer par exemple la balance dont la bankAccount.getBalance()=null
    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id,@RequestBody BankAccount bankAccount)
    {
        BankAccount account=bankAccountRepository.findById(id).orElseThrow();
        if(bankAccount.getBalance()!=null) account.setBalance(bankAccount.getBalance());
        if(bankAccount.getCreatedAt()!=null) account.setCreatedAt(new Date());
        if(bankAccount.getType()!=null) account.setType(bankAccount.getType());
        if(bankAccount.getCurrency()!=null) account.setCurrency(bankAccount.getCurrency());
        return bankAccountRepository.save(account);
    }

    @DeleteMapping ("/bankAccounts/{id}")
    public void DeleteAccount(@PathVariable String id)
    {
         bankAccountRepository.deleteById(id);

    }
}
