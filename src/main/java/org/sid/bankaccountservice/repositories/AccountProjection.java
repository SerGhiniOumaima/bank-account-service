package org.sid.bankaccountservice.repositories;

import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.enums.AccountType;
import org.springframework.data.rest.core.config.Projection;

//les projections font partie du spring data rest
// une projection sert à définir quelles sont les champs à retourner
//http://localhost:8081/bankAccounts?projection=p1
@Projection(types = BankAccount.class, name="p1")
public interface AccountProjection {
    public String getId();
    public AccountType getType();
}
